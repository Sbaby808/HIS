package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.OutstockDetail;
import com.his.pojo.OutstockDetailPK;

/**  
* @ClassName: IOutstockDetailsDao  
* @Description: 药库出库明细dao 
* @author crazy_long
* @date 2019年7月30日  上午10:48:25
*    
*/
public interface IOutstockDetailsDao extends CrudRepository<OutstockDetail, OutstockDetailPK>{

}
