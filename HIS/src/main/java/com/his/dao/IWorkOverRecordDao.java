package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.WorkoverRecord;

/**  
* @ClassName: IWorkOverRecordDao  
* @Description: 加班记录表dao
* @author crazy_long
* @date 2019年7月30日  上午9:39:55
*    
*/
public interface IWorkOverRecordDao extends CrudRepository<WorkoverRecord, String>{

}
