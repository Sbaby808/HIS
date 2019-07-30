package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.CheckWork;

/**  
* @ClassName: ICheckWorkDao  
* @Description: 考勤记录dao
* @author crazy_long
* @date 2019年7月30日  上午9:45:58
*    
*/
public interface ICheckWorkDao extends CrudRepository<CheckWork, String>{

}
