package com.his.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.HosBed;
import com.his.service.HosBedService;


/**
 * 
* @ClassName: HosBedController  
* @Description: 住院床位 
* @author Hamster
* @date 2019年7月31日  下午10:52:06
*
 */
@Controller
public class HosBedController {
	
	@Autowired
	private HosBedService hosBedService;
	
	/**
	 * 
	* @Title:getBedsByRoomid
	* @Description:根据病房id查询床位
	* @param:@param room_id
	* @param:@return
	* @return:List<HosBed>
	* @throws
	* @author:Hamster
	* @Date:2019年8月2日 上午9:47:46
	 */
	@ResponseBody
	@GetMapping("/get_bed_by_roomId")
	public List <HosBed> getBedsByRoomid(String room_id){
		return hosBedService.getFreeBedsByRoomid(room_id);
	}
	
	/**
	 * 
	* @Title:getBedByBid
	* @Description:根据床位id查询床位信息
	* @param:@param bid
	* @param:@return
	* @return:HosBed
	* @throws
	* @author:Hamster
	* @Date:2019年8月2日 上午9:48:25
	 */
	@ResponseBody
	@GetMapping("/get_bed_by_bId")
	public HosBed getBedByBid(String bid){
		return hosBedService.getBedByBid(bid);
	}
	
	/**
	 * 
	* @Title:getAllBeds
	* @Description:无分页查询所有床位信息
	* @param:@return
	* @return:List<HosBed>
	* @throws
	* @author:Hamster
	* @Date:2019年8月4日 上午10:50:43
	 */
	@ResponseBody
	@GetMapping("get_all_hos_beds")
	public List <HosBed> getAllBeds(){
		return hosBedService.getAllBeds();
	}
	
	/**
	 * 
	* @Title:getAllBedsByPage
	* @Description:分页查询所有床位信息
	* @param:@param curpage
	* @param:@param pagesize
	* @param:@return
	* @return:Map
	* @throws
	* @author:Hamster
	* @Date:2019年8月4日 下午10:08:44
	 */
	@ResponseBody
	@GetMapping("get_hos_beds_byPage")
	public Map getAllBedsByPage(String text1,String text2,String text3,int curpage,int pagesize){
		String ksName = "%"+text1+"%";
		String wardName = "%"+text2+"%";
		String roomName = "%"+text3+"%";
		return hosBedService.getAllBedsByPage(ksName,wardName,roomName,curpage, pagesize);
	}
	
	/**
	 * 
	* @Title:addHosBed
	* @Description:新增床位
	* @param:@param hosBed
	* @return:void
	* @throws
	* @author:Hamster
	* @Date:2019年8月5日 上午9:02:36
	 */
	@ResponseBody
	@PostMapping("/add_hos_bed")
	public void addHosBed(@RequestBody HosBed hosBed){
		hosBedService.addHosBed(hosBed);
	}
	
	/**
	 * 
	* @Title:delHosBed
	* @Description:删除床位
	* @param:@param hosBed
	* @return:void
	* @throws
	* @author:Hamster
	* @Date:2019年8月5日 上午9:04:42
	 */
	@ResponseBody
	@PostMapping("/del_hos_bed")
	public void delHosBed(@RequestBody HosBed hosBed){
		hosBedService.delHosBed(hosBed);
	}
	
}
