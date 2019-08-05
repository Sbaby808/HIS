package com.his.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.HosPrescription;

/**
 * 
* @ClassName: HosPrescriptonService  
* @Description: 住院处方  
* @author Hamster
* @date 2019年8月5日  下午7:23:53
*
 */
@Controller
public class HosPrescriptonService {

	@Autowired
	private HosPrescriptonService hosPrescriptonService;
	
	/**
	 * 
	* @Title:getAllHosPrescription
	* @Description:无分页查询所有住院处方
	* @param:@return
	* @return:List<HosPrescription>
	* @throws
	* @author:Hamster
	* @Date:2019年8月5日 下午7:25:33
	 */
	@ResponseBody
	@GetMapping("/get_hos_prescription")
	public List <HosPrescription> getAllHosPrescription(){
		return hosPrescriptonService.getAllHosPrescription();
	}
}
