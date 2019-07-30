package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.DamagedDrugDetail;
import com.his.pojo.DamagedDrugDetailPK;

/**  
* @ClassName: IDamagedDrugDetailsDao  
* @Description: 药品报损明细dao
* @author crazy_long
* @date 2019年7月30日  上午10:41:39
*    
*/
public interface IDamagedDrugDetailsDao extends CrudRepository<DamagedDrugDetail, DamagedDrugDetailPK>{

}
