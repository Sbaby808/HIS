package com.his.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.WardRoom;
import com.his.service.WardRoomService;

/**
 * 
* @ClassName: WardRoomController  
* @Description: TODO住院病房
* @author Hamster
* @date 2019年8月1日  下午6:08:32
*
 */
@Controller
public class WardRoomController {

	@Autowired
	private WardRoomService wardRoomService;
	
	@ResponseBody
	@GetMapping("/get_all_ward_room")
	public Map getAllWardRoom(int curpage,int pagesize){
		return wardRoomService.getAllWardRoom(curpage, pagesize);
	}
	
	@ResponseBody
	@PostMapping("/del_ward_room")
	public void delWardRoom(@RequestBody WardRoom wardRoom){
		wardRoomService.delWardRoom(wardRoom);
	}
	
	@ResponseBody
	@PostMapping("/add_ward_room")
	public void addWardRoom(@RequestBody WardRoom wardRoom){
		wardRoomService.addWardRoom(wardRoom);
	}
	
	@ResponseBody
	@PostMapping("/change_ward_room")
	public void changeWardRoom(@RequestBody WardRoom wardRoom){
		wardRoomService.changeWardRoom(wardRoom);
	}
	
	@ResponseBody
	@GetMapping("/get_ward_room_byWid")
	public List <WardRoom> getWardRoomByWid(String ward_id){
		return wardRoomService.getWardRoomByWid(ward_id);
	}
}
