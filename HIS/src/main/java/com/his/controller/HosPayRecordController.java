package com.his.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.service.HosPayRecordService;

@Controller
public class HosPayRecordController {

	@Autowired
	private HosPayRecordService hosPayRecordService;
	
	@ResponseBody
	@GetMapping("/get_hos_pay_record")
	public Map getHosPayRecords(String text3,int curpage,int pagesize){
		String cardName = "%"+text3+"%";
		return hosPayRecordService.getAllPayRecord(cardName, curpage, pagesize);
	}
}
