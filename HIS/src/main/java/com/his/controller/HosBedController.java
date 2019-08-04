package com.his.controller;

import java.util.List;

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
* @Description: TODO住院床位 
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
		return hosBedService.getBedsByRoomid(room_id);
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
	
}