package com.his.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IHosBedDao;
import com.his.dao.IWardDao;
import com.his.dao.IWardRoomDao;
import com.his.pojo.HosBed;
import com.his.pojo.WardRoom;

/**
 * 住院病房
 * @author dell
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class WardRoomService {
	
	@Autowired
	private IWardRoomDao wardRoomDao;
	@Autowired
	private IHosBedDao hosBedDao;
	
	/**
	 * 
	* @Title:getAllWardRoom
	* @Description:查询所有病房信息
	* @param:@param curpage
	* @param:@param pagesize
	* @param:@return
	* @return:Map
	* @throws
	* @author:Hamster
	* @Date:2019年7月30日 下午4:56:39
	 */
	public Map getAllWardRoom(int curpage,int pagesize){
		List <WardRoom> list = wardRoomDao.getAllWardRoom(PageRequest.of(curpage-1, pagesize));
		long total = wardRoomDao.count();
		Map map = new HashMap<>();
		map.put("list", list);
		map.put("total", total);
		return map;
	}
	
	
	/**
	 * 
	* @Title:delWardRoom
	* @Description:删除病房以及该病房的所有床位
	* @param:@param wardRoom
	* @return:void
	* @throws
	* @author:Hamster
	* @Date:2019年7月30日 下午4:57:04
	 */
	public void delWardRoom(WardRoom wardRoom){
		List <HosBed> beds = hosBedDao.getBedsByRid(wardRoom.getWroomId());
		for(int i=0;i<beds.size();i++){
			hosBedDao.delete(beds.get(i));
		}
		wardRoomDao.delete(wardRoom);
	}
	
	/**
	 * 
	* @Title:addWardRoom
	* @Description:新增病房信息
	* @param:@param wardRoom
	* @return:void
	* @throws
	* @author:Hamster
	* @Date:2019年7月30日 下午4:57:26
	 */
	public void addWardRoom(WardRoom wardRoom){
		wardRoom.setWNum(0);
		wardRoomDao.save(wardRoom);
	}
	
	/**
	 * 
	* @Title:changeWardRoom
	* @Description:修改病房信息
	* @param:@param wardRoom
	* @return:void
	* @throws
	* @author:Hamster
	* @Date:2019年7月31日 下午10:13:10
	 */
	public void changeWardRoom(WardRoom wardRoom){
		wardRoomDao.save(wardRoom);
	}
	
	/**
	 * 
	* @Title:getWardRoomByWid
	* @Description:根据病区id获取病房
	* @param:@param wid
	* @param:@return
	* @return:List<WardRoom>
	* @throws
	* @author:Hamster
	* @Date:2019年7月31日 下午10:27:27
	 */
	public List <WardRoom> getWardRoomByWid(String wid){
		return wardRoomDao.getWardRoomByWid(wid);
	}
	
}
