package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.DrugScrapDetail;
import com.his.pojo.DrugScrapDetailPK;

/**  
* @ClassName: IDrugScrapDetailsDao  
* @Description: 药品报废明细dao
* @author crazy_long
* @date 2019年7月30日  上午10:38:40
*    
*/
public interface IDrugScrapDetailsDao extends CrudRepository<DrugScrapDetail, DrugScrapDetailPK>{

}
