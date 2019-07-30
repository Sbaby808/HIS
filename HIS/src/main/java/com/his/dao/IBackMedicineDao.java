package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.BackMedicine;

/**  
* @ClassName: IBackMedicineDao  
* @Description: 退药dao
* @author crazy_long
* @date 2019年7月30日  上午11:07:24
*    
*/
public interface IBackMedicineDao extends CrudRepository<BackMedicine, String>{

}
