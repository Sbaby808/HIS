package com.his.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.WaitingRoom;
import com.his.service.WaitingRoomService;

/**  
* @ClassName: WaitingRoomController  
* @Description: 候诊听 service
* @author crazy_long
* @date 2019年8月3日  上午10:04:06
*    
*/
@Controller
public class WaitingRoomController {
	
	@Autowired
	private WaitingRoomService waitingroomservice;

	/**
	 * 
	* @Title:get_all_waitingroom
	* @Description:获取所有候诊厅信息
	* @param:@return
	* @return:List<WaitingRoom>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月3日 上午10:20:35
	 */
	@ResponseBody
	@GetMapping("get_all_waitingroom")
	public List<WaitingRoom> get_all_waitingroom(){
		return waitingroomservice.queryAllWaitingroom();
	}
	
}
