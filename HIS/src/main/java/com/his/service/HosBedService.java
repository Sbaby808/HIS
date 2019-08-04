package com.his.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IHosBedDao;
import com.his.pojo.BedTransRecord;
import com.his.pojo.HosBed;
/**
 * 住院床位
 * @author dell
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class HosBedService {
	
	@Autowired
	private IHosBedDao hosBedDao;
	
	
	/**
	 *
	* @Title:getAllBeds
	* @Description:分页查询所有床位信息
	* @param:@param curpage
	* @param:@param pagesize
	* @param:@return
	* @return:Map
	* @throws
	* @author:Hamster
	* @Date:2019年8月4日 上午10:46:15
	 */
	public Map getAllBedsByPage(int curpage,int pagesize){
		List <HosBed> list = hosBedDao.getAllBedsByPage(PageRequest.of(curpage-1, pagesize));
		long total  = hosBedDao.count();
		Map map = new HashMap<>();
		map.put("list", list);
		map.put("total", total);
		return map;
	}
	
	/**
	 * 
	* @Title:getAllBeds
	* @Description:无分页查询所有床位
	* @param:@return
	* @return:List<HosBed>
	* @throws
	* @author:Hamster
	* @Date:2019年8月4日 上午10:49:23
	 */
	public List <HosBed> getAllBeds(){
		return hosBedDao.getAllBeds();
	}
	
	/**
	 * 
	* @Title:getBedsByRoomid
	* @Description:根据病房id查询床位
	* @param:@param room_id
	* @param:@return
	* @return:List<HosBed>
	* @throws
	* @author:Hamster
	* @Date:2019年7月31日 下午10:51:23
	 */
	public List <HosBed> getBedsByRoomid(String room_id){
		return hosBedDao.getBedsByRoomid(room_id);
	}
	
	/**
	 * 
	* @Title:getBedByBid
	* @Description:根据床位id查询床位
	* @param:@param bid
	* @param:@return
	* @return:HosBed
	* @throws
	* @author:Hamster
	* @Date:2019年8月1日 下午6:36:19
	 */
	public HosBed getBedByBid(String bid){
		return hosBedDao.getBedByBid(bid);
	}
	

}
