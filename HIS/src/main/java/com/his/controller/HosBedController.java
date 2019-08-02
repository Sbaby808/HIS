package com.his.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@ResponseBody
	@GetMapping("/get_bed_by_roomId")
	public List <HosBed> getBedsByRoomid(String room_id){
		return hosBedService.getBedsByRoomid(room_id);
	}
	
	@ResponseBody
	@GetMapping("/get_bed_by_bId")
	public HosBed getBedByBid(String bid){
		return hosBedService.getBedByBid(bid);
	}
}
