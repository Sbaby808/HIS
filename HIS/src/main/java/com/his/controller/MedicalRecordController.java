package com.his.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.MedicalRecord;
import com.his.service.MedicalRecordService;

/**
 * 
* @ClassName: MedicalRecordController  
* @Description: TODO住院病案 
* @author Hamster
* @date 2019年8月1日  下午9:46:26
*
 */
@Controller
public class MedicalRecordController {

	@Autowired
	private MedicalRecordService medicalRecordService;
	
	@ResponseBody
	@GetMapping("/get_all_medical_record")
	public Map getAllMedicalRecord(int curpage,int pagesize){
		return  medicalRecordService.getAllMedicalRecord(curpage, pagesize);
	}
	
}
