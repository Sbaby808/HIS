package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.OpeEmp;

/**  
* @ClassName: IOpeEmpDao  
* @Description: TODO(手术员工dao)  
* @author TRC
* @date 2019年7月30日  上午9:06:27
*    
*/
public interface IOpeEmpDao extends CrudRepository<OpeEmp, String>{

}
