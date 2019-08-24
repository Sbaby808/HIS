package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.Prescription;

/**  
* @ClassName: IPrescriptionDao  
* @Description: 门诊处方Dao
* @author Sbaby
* @date 2019年8月22日  上午11:01:15
*    
*/
public interface IPrescriptionDao extends CrudRepository<Prescription, String> {

}
