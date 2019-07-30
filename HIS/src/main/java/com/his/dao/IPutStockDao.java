package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.PutStock;

/**  
* @ClassName: IPutStockDao  
* @Description: 药品入库信息dao
* @author crazy_long
* @date 2019年7月30日  上午10:17:37
*    
*/
public interface IPutStockDao extends CrudRepository<PutStock, String>{

}
