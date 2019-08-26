package com.his.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.OutHospitaiRecord;
import com.his.service.HosOutRecordService;

@Controller
public class HosOutRecordController {

	@Autowired
	private HosOutRecordService hosOutRecordService;
	
	@ResponseBody
	@GetMapping("/get_hos_out_record")
	public Map getHosOutRecord(String text1,int curpage,int pagesize){
		String cardName = "%"+text1+"%";
		return hosOutRecordService.getHosOutRecord(cardName,curpage,pagesize);
	}
}
