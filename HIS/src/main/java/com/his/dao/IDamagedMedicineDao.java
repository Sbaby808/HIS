package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.DamagedDrugDetail;

/**  
* @ClassName: IDamagedMedicine  
* @Description: 药品报损dao 
* @author crazy_long
* @date 2019年7月30日  上午10:18:58
*    
*/
public interface IDamagedMedicineDao extends CrudRepository<DamagedDrugDetail, String>{

}
