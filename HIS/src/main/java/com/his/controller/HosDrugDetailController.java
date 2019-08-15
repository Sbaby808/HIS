package com.his.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.HosDrugDetail;
import com.his.service.HosDrugDetailService;

@Controller
public class HosDrugDetailController {

	@Autowired
	private HosDrugDetailService hosDrugDetailService;
	
	@ResponseBody
	@GetMapping("/get_hos_drug_detail")
	public List <HosDrugDetail> getHosDrugDetail(){
		return hosDrugDetailService.getHosDrugDetail();
	}
}
