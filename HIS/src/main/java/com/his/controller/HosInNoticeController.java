package com.his.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@ResponseBody
	@GetMapping("/get_hos_in_notice")
	public List <HospitalNotice> getAllHosInNotice(){
		return hosInNoticeService.getAllHosInNotice();
	}
}
