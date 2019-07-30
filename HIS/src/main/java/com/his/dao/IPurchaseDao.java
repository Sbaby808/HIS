package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.Purchase;

/**  
* @ClassName: IPurchaseDao  
* @Description: 药品采购dao
* @author crazy_long
* @date 2019年7月30日  上午11:08:35
*    
*/
public interface IPurchaseDao extends CrudRepository<Purchase, String>{

}
