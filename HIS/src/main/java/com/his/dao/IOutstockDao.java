package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.Outstock;

/**  
* @ClassName: IOutStockDao  
* @Description: 药库出库信息表
* @author crazy_long
* @date 2019年7月30日  上午10:47:01
*    
*/
public interface IOutstockDao extends CrudRepository<Outstock, String>{

}
