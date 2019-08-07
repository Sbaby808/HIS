package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.WaitingRoom;

/**  
* @ClassName: IWaitingRoomDao  
* @Description: 候诊厅Dao 
* @author crazy_long
* @date 2019年8月3日  上午12:16:23
*    
*/
public interface IWaitingRoomDao extends CrudRepository<WaitingRoom, String>{

}
