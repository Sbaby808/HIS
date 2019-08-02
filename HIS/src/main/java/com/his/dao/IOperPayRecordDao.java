package com.his.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.bean.OperationRecordbean;
import com.his.pojo.OperPayRecord;
import com.his.pojo.OperationRecord;
/**  
* @ClassName: IOperPayRecordDao  
* @Description: TODO(手术缴费记录dao)  
* @author TRC
* @date 2019年7月30日  上午9:06:27
*    
*/
public interface IOperPayRecordDao extends CrudRepository<OperPayRecord, String>{
	
}
