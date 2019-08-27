package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.Dept;
import com.his.pojo.EmpInformation;
import com.his.pojo.Role;

/**  
* @ClassName: IRoleDao  
* @Description: 职位dao
* @author crazy_long
* @date 2019年7月30日  上午9:50:20
*    
*/
public interface IRoleDao extends CrudRepository<Role, String>{
	/**
	 * 
	* @Title:findByRole_position
	* @Description:TODO根据职位名找role对象
	* @param:@param role_position
	* @param:@return
	* @return:List<Role>
	* @throws
	* @author:TRC
	* @Date:2019年8月6日 上午10:04:03
	 */
	@Query(value="from Role r where r.rolePosition=?1")
	public Role getRole(String role_position);
    
	@Query(value="select u.empInformation  from Role r,UserRole u where r.roleId=u.role.roleId and r.department.ksId=?1")
	public List<EmpInformation> getallemp(String ksid);

	
	@Query("from Role r")
	public List<Role> getAll();
	
	//通过职位id查找该职位的所有员工名字
	@Query("select e.ygName from UserRole u left outer join EmpInformation e on u.empInformation.ygxh=e.ygxh where u.role.roleId=?1")
	public List<String> findEmpInformationsbyroleid(String roleid);
	@Query("select e from UserRole u left outer join EmpInformation e on u.empInformation.ygxh=e.ygxh where u.role.roleId=?1")
	public List<EmpInformation> findempssss(String roleid);
	
	
	@Query("from Role t where t.rolePosition like ?1")
	public List<Role> findroless(String rolePosition,Pageable pageable);
	
	@Query("select count(*) from Role t where t.rolePosition like ?1")
	public long countnames(String rolePosition);
	
	@Query("select count(*) from Role t where t.rolePosition = ?1" )
	public long countnamesss(String rolePosition);
	//通过roleid找到所有该职位员工的id
	@Query("select u.empInformation.ygxh from UserRole u where u.role.roleId = ?1")
	public List<String> findempygxhs(String roleid);
}
