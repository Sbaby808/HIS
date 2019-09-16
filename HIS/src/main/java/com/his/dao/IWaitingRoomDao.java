package com.his.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
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
	@Query(value="select w from WaitingRoom w where w.department.ksId=?1")
	public List<WaitingRoom> getRooms(String ksid);

}
