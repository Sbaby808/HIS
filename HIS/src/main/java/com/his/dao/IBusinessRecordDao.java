package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.BusinessRecord;

/**  
* @ClassName: BusinessRecordDao  
* @Description: 出差记录dao
* @author crazy_long
* @date 2019年7月30日  上午9:41:15
*    
*/
public interface IBusinessRecordDao extends CrudRepository<BusinessRecord, String>{

}
