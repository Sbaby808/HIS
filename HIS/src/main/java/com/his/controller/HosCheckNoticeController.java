package com.his.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.HosCheckNotice;
import com.his.service.HosCheckNoticeService;

/**
 * 
* @ClassName: HosCheckNoticeController  
* @Description: 住院检查通知单  
* @author Hamster
* @date 2019年9月3日  下午1:55:53
*
 */
@Controller
public class HosCheckNoticeController {
	
	@Autowired
	private HosCheckNoticeService hosCheckNoticeService;
	
	/**
	 * 
	* @Title:getHosCheckNoticeBydiagId
	* @Description:根据诊断记录id查询住院检查通知单
	* @param:@param diagId
	* @param:@return
	* @return:List<HosCheckNotice>
	* @throws
	* @author:Hamster
	* @Date:2019年9月3日 下午2:09:21
	 */
	@ResponseBody
	@GetMapping("/get_hos_check_notice_by_diagId")
	public List <HosCheckNotice> getHosCheckNoticeBydiagId(String diagId){
		return hosCheckNoticeService.getHosCheckNoticeBydiagId(diagId);
	}
	
}
