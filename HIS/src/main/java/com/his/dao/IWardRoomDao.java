package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.WardRoom;

/**
 * 住院病房
 * @author dell
 *
 */
public interface IWardRoomDao extends CrudRepository<WardRoom, String>{
	/**
	 * 
	* @Title:getAllWardRoom
	* @Description:查询所有病房信息
	* @param:@param page
	* @param:@return
	* @return:List<WardRoom>
	* @throws
	* @author:Hamster
	* @Date:2019年8月2日 下午9:33:37
	 */
	@Query("from WardRoom w")
	public List <WardRoom> getAllWardRoom(Pageable page);

	/**
	 * 
	* @Title:getWardRoomByWid
	* @Description:根据病区id查询病房信息
	* @param:@param wid
	* @param:@return
	* @return:List<WardRoom>
	* @throws
	* @author:Hamster
	* @Date:2019年8月2日 下午9:33:53
	 */
	@Query(value="select * from ward_room w where w.ward_id=?1",nativeQuery=true)
	public List <WardRoom> getWardRoomByWid(String wid);
	
	/**
	 * 
	* @Title:getWardRoomByRid
	* @Description:根据病房id查询病房信息
	* @param:@param roomId
	* @param:@return
	* @return:WardRoom
	* @throws
	* @author:Hamster
	* @Date:2019年8月4日 下午10:18:13
	 */
	@Query("from WardRoom w where w.wroomId = ?1 and w.wNum<4")
	public WardRoom getWardRoomByRid(String roomId);
	
}
