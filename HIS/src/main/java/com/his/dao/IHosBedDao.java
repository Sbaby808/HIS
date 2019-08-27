package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.HosBed;


/**
 * 住院床位
 * @author dell
 *
 */
public interface IHosBedDao extends CrudRepository<HosBed,String>{
	
	/**
	 * 
	* @Title:getAllBeds
	* @Description:分页查找所有床位
	* @param:@param page
	* @param:@return
	* @return:List<HosBed>
	* @throws
	* @author:Hamster
	* @Date:2019年8月1日 上午9:37:08
	 */
	@Query("from HosBed h where "
			+ " h.wardRoom.ward.department.ksName like ?1 "
			+ " and h.wardRoom.ward.wardName like ?2 "
			+ " and h.wardRoom.wroomName like ?3 ")
	public List <HosBed> getAllBedsByPage(String ksName,String wardName,String roomName,Pageable page);
	
	/**
	 * 
	* @Title:getAllBeds
	* @Description:无分页查询所有床位
	* @param:@return
	* @return:List<HosBed>
	* @throws
	* @author:Hamster
	* @Date:2019年8月4日 上午10:48:01
	 */
	@Query("from HosBed h")
	public List <HosBed> getAllBeds();
	
	/**
	 * 
	* @Title:getBedsByRoomid
	* @Description:根据病房id查询空床位(用于住院登记床位选择和患者转床)
	* @param:@param room_id
	* @param:@return
	* @return:List<HosBed>
	* @throws
	* @author:Hamster
	* @Date:2019年8月1日 上午9:37:22
	 */
	@Query(value="select * from hos_beds h where h.wroom_id=?1 and h.hos_bstate is null",nativeQuery=true)
	public List <HosBed> getFreeBedsByRoomid(String room_id);
	
	
	/**
	 * 
	* @Title:getBedByBid
	* @Description:根据床位id查询床位
	* @param:@param bid
	* @param:@return
	* @return:HosBed
	* @throws
	* @author:Hamster
	* @Date:2019年8月1日 下午6:35:30
	 */
	@Query("from HosBed h where h.hosBid=?1")
	public HosBed getBedByBid(String bid);
	
	
	/**
	 * 
	* @Title:getBedsByRid
	* @Description:根据病房id查询所有床位
	* @param:@param rid
	* @param:@return
	* @return:List<HosBed>
	* @throws
	* @author:Hamster
	* @Date:2019年8月5日 上午8:58:12
	 */
	@Query(value="select * from hos_beds h where h.wroom_id=?1",nativeQuery=true)
	public List <HosBed> getBedsByRid(String rid);

	@Query("from HosBed h where h.hospitalizedPatient.hospId=?1")
	public HosBed getBedByhospId(String hospId);
}
