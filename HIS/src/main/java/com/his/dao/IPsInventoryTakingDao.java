package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.PsInventoryTaking;

/**  
* @ClassName: IPsInventoryTakingDao  
* @Description:药库盘存信息dao
* @author crazy_long
* @date 2019年7月30日  上午10:51:32
*    
*/
public interface IPsInventoryTakingDao extends CrudRepository<PsInventoryTaking, String>{

}
