package com.his.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

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
    
	@Query("from Role r")
	public List<Role> getAll();
}
