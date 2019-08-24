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
* @Description: 住院病房
* @author Hamster
* @date 2019年8月1日  下午6:08:32
*
 */
@Controller
public class WardRoomController {

	@Autowired
	private WardRoomService wardRoomService;
	
	/**
	 * 
	* @Title:getAllWardRoom
	* @Description:分页查询所有病房信息
	* @param:@param curpage
	* @param:@param pagesize
	* @param:@return
	* @return:Map
	* @throws
	* @author:Hamster
	* @Date:2019年8月5日 上午9:06:38
	 */
	@ResponseBody
	@GetMapping("/get_all_ward_room")
	public Map getAllWardRoom(String text1,String text2,int curpage,int pagesize){
		String roomName = "%"+text1+"%";
		String ksName = "%"+text2+"%";
		return wardRoomService.getAllWardRoom(roomName,ksName,curpage, pagesize);
	}
	
	@ResponseBody
	@GetMapping("/get_ward_rooms")
	public List <WardRoom> getWardRooms(){
		return wardRoomService.getWardRooms();
	}
	
	/**
	 * 
	* @Title:delWardRoom
	* @Description:删除病房以及该病房下的所有床位
	* @param:@param wardRoom
	* @return:void
	* @throws
	* @author:Hamster
	* @Date:2019年8月5日 上午9:07:39
	 */
	@ResponseBody
	@PostMapping("/del_ward_room")
	public void delWardRoom(@RequestBody WardRoom wardRoom){
		wardRoomService.delWardRoom(wardRoom);
	}
	
	/**
	 * 
	* @Title:addWardRoom
	* @Description:新增病房
	* @param:@param wardRoom
	* @return:void
	* @throws
	* @author:Hamster
	* @Date:2019年8月5日 上午9:08:15
	 */
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
	
	/**
	 * 
	* @Title:getWardRoomByWid
	* @Description:查询一个病区下的所有病房
	* @param:@param ward_id
	* @param:@return
	* @return:List<WardRoom>
	* @throws
	* @author:Hamster
	* @Date:2019年8月5日 上午9:08:32
	 */
	@ResponseBody
	@GetMapping("/get_ward_room_byWid")
	public List <WardRoom> getWardRoomByWid(String ward_id){
		return wardRoomService.getWardRoomByWid(ward_id);
	}
}
