package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.AskleaveRecord;

/**  
* @ClassName: IAskleaveRecordDao  
* @Description: 请假记录dao 
* @author crazy_long
* @date 2019年7月30日  上午9:43:12
*    
*/
public interface IAskleaveRecordDao extends CrudRepository<AskleaveRecord, String>{

}
