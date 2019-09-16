package com.his.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.EmpInformation;
import com.his.pojo.UserRole;

/**  
* @ClassName: IUserRoleDao  
* @Description: 职位_员工中间表
* @author crazy_long
* @date 2019年7月30日  上午9:53:29
*    
*/
public interface IUserRoleDao extends CrudRepository<UserRole, String>{

	@Query("from UserRole ur where ur.id.ygxh = ?1")
	public List<UserRole> queryAllRoleByYgxh(String ygxh);
	//通过员工序号查询员工的所有职位序号
	@Query("select ur.role.roleId from UserRole ur where ur.empInformation.ygxh=?1")
	public Set<String> findroleidsStrings(String ygxh);
	/**
	 * 
	* @Title:findempsbyrole
	* @Description:通过职位名字找到所有这个职位的员工
	* @param:@param rolename
	* @param:@return
	* @return:List<EmpInformation>
	* @throws
	* @author:jack
	* @Date:2019年8月26日 上午11:23:13
	 */
	@Query("select ur.empInformation from UserRole ur where ur.role.rolePosition=?1")
	public List<EmpInformation> findempsbyrole(String rolename);
	
	/**
	* @Title:checkLoginGrant
	* @Description:检查是否有登录权限
	* @param:@param ygxh
	* @param:@return
	* @return:int
	* @throws
	* @author:Sbaby
	* @Date:2019年9月13日 下午2:00:41
	 */
	@Query(value = "select count(*) from user_role ur right outer join role_func rf on ur.role_id = rf.role_id where ur.ygxh = ?1 ", nativeQuery = true)
	public int checkLoginGrant(String ygxh);
	
}
