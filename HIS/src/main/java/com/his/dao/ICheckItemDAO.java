package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.CheckItem;

/**  
* @ClassName: ICheckItemDAO  
* @Description: TODO(检查小项dao)  
* @author TRC
* @date 2019年7月30日  上午9:36:30
*    
*/
public interface ICheckItemDAO extends CrudRepository<CheckItem, String>{

}
