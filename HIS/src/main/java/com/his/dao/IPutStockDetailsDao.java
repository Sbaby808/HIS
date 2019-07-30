package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.PutStockDetail;
import com.his.pojo.PutStockDetailPK;

/**  
* @ClassName: IPutStockDetailsDao  
* @Description: 药品入库明细dao
* @author crazy_long
* @date 2019年7月30日  上午10:32:39
*    
*/
public interface IPutStockDetailsDao extends CrudRepository<PutStockDetail, PutStockDetailPK>{

}
