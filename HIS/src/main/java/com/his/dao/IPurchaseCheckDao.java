package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.Purchase;
import com.his.pojo.PurchaseCheck;

/**  
* @ClassName: IPurchaseCheckDao  
* @Description: 采购验收表  
* @author crazy_long
* @date 2019年7月30日  上午11:11:32
*    
*/
public interface IPurchaseCheckDao extends CrudRepository<PurchaseCheck, String>{

}
