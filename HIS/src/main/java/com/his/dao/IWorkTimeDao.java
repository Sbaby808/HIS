package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.WorkTime;

/**  
* @ClassName: IWorkTimeDao  
* @Description: 排班时间安排dao
* @author crazy_long
* @date 2019年7月30日  上午9:36:50
*    
*/
public interface IWorkTimeDao extends CrudRepository<WorkTime, String>{

}
