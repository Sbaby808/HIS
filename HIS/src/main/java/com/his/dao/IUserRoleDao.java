package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.UserRole;

/**  
* @ClassName: IUserRoleDao  
* @Description: 职位_员工中间表
* @author crazy_long
* @date 2019年7月30日  上午9:53:29
*    
*/
public interface IUserRoleDao extends CrudRepository<UserRole, String>{

}
