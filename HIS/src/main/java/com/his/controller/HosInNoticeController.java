package com.his.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.HospitalNotice;
import com.his.service.HosInNoticeService;


/**
 * 
* @ClassName: HosInNoticeController  
* @Description: TODO住院通知
* @author Hamster
* @date 2019年8月1日  上午11:09:49
*
 */
@Controller
public class HosInNoticeController {

	@Autowired
	private HosInNoticeService hosInNoticeService;
	
	
	/**
	 * 
	* @Title:getAllHosInNotice
	* @Description:查询所有入院通知单
	* @param:@return
	* @return:List<HospitalNotice>
	* @throws
	* @author:Hamster
	* @Date:2019年8月3日 上午10:51:22
	 */
	@ResponseBody
	@GetMapping("/get_hos_in_notice_byPage")
	public Map getAHosInNoticeByPage(String text1,String text2,String text3,int curpage,int pagesize){
		String hospName = "%"+text1+"%";
		String sourceText = "%"+text2+"%";
		String departText = "%"+text3+"%";
		return hosInNoticeService.getHosInNoticeByPage(hospName,sourceText,departText,curpage, pagesize);
	}
	
}
