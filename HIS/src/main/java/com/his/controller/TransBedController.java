package com.his.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.BedTransRecord;
import com.his.service.TransBedService;

import oracle.jdbc.proxy.annotation.Post;


/**
 * 
* @ClassName: TransBedController  
* @Description: TODO住院转床记录 
* @author Hamster
* @date 2019年7月31日  下午8:58:25
*
 */
@Controller
public class TransBedController {
	
	@Autowired
	private TransBedService transBedService;
	
	@ResponseBody
	@PostMapping("/add_trans_bed_record")
	public void addTransBed(@RequestBody BedTransRecord bedTransRecord) throws ParseException{
		transBedService.addTransBed(bedTransRecord);
	}
}
