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
* @ClassName: ${type_name}  
* @Description: ${todo} 住院病房
* @author Hamster
* @date ${date}  ${time}
*  
* ${tags}  
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
}
