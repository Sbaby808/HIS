package com.his.controller;


import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.TransOfficeRecord;
import com.his.service.TransOfficeService;

/**
 * 
* @ClassName: TransOfficeController  
* @Description: TODO住院转科记录 
* @author Hamster
* @date 2019年7月31日  下午8:09:50
*
 */

@Controller
public class TransOfficeController {

	@Autowired
	private TransOfficeService transOfficeService;
	
	@ResponseBody
	@PostMapping("/add_trans_office_record")
	public void addTransOffice(@RequestBody TransOfficeRecord transOfficeRecord) throws ParseException{
		transOfficeService.addTransOffice(transOfficeRecord);
	}
}
