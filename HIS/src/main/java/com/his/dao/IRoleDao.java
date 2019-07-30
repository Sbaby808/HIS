package com.his.dao;

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

}
