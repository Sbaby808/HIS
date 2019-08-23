package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.ObservationNotice;

/**  
* @ClassName: IObservationNoticeDao  
* @Description: 留观通知单Dao
* @author Sbaby
* @date 2019年8月23日  下午2:40:58
*    
*/
public interface IObservationNoticeDao extends CrudRepository<ObservationNotice, String> {

}
