package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.CheckPayRecord;

/**  
* @ClassName: ICheckPayRecordDao  
* @Description: TODO(检查缴费记录)  
* @author TRC
* @date 2019年7月30日  上午9:55:31
*    
*/
public interface ICheckPayRecordDao extends CrudRepository<CheckPayRecord, String>{

}
