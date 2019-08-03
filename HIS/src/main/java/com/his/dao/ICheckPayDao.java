package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.CheckPay;

/**  
* @ClassName: ICheckPayDao  
* @Description: TODO(检查划价dao)  
* @author TRC
* @date 2019年7月30日  上午9:41:05
*    
*/
public interface ICheckPayDao extends CrudRepository<CheckPay, String>{

}
