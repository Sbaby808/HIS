package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.DrugWarehouse;

/**  
* @ClassName: IDrugWarehouseDao  
* @Description: 药库批次库存dao
* @author crazy_long
* @date 2019年7月30日  上午10:50:06
*    
*/
public interface IDrugWarehouseDao extends CrudRepository<DrugWarehouse, String>{

}
