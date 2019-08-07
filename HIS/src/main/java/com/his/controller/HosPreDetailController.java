package com.his.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.HosPrescriptionDetail;
import com.his.service.HosPreDetailService;

/**
 * 
* @ClassName: HosPreDetailController  
* @Description: 住院处方明细 
* @author Hamster
* @date 2019年8月6日  上午10:52:25
*
 */
@Controller
public class HosPreDetailController {

	@Autowired
	private HosPreDetailService hosPreDetailService;
	
	/**
	 * 
	* @Title:getHosPreDetail
	* @Description:无分页查询
	* @param:@return
	* @return:List<HosPrescriptionDetail>
	* @throws
	* @author:Hamster
	* @Date:2019年8月6日 上午10:55:03
	 */
	@ResponseBody
	@GetMapping("/get_hos_pre_detail")
	public List <HosPrescriptionDetail> getHosPreDetail(){
		return hosPreDetailService.getHosPreDetail();
	}
	
	/**
	 * 
	* @Title:getHosPreDetailByPid
	* @Description:根据处方id获取处方明细
	* @param:@param pid
	* @param:@return
	* @return:List<HosPrescriptionDetail>
	* @throws
	* @author:Hamster
	* @Date:2019年8月6日 上午11:32:28
	 */
	@ResponseBody
	@GetMapping("/get_hos_pre_detail_byPid")
	public List <HosPrescriptionDetail> getHosPreDetailByPid(String pid){
		System.out.println(pid);
		return hosPreDetailService.getHosPreDetailByPid(pid);
	}
	
}
