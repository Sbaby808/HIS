package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.OutpatientRequestionMedicine;

/**  
* @ClassName: IOutpatientRequestionMedicine  
* @Description: 药品申领表
* @author crazy_long
* @date 2019年7月30日  上午10:20:13
*    
*/
public interface IOutpatientRequestionMedicineDao extends CrudRepository<OutpatientRequestionMedicine, String>{

}
