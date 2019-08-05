package com.his.controller;

import java.util.List;
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
* @Description: 住院病案 
* @author Hamster
* @date 2019年8月1日  下午9:46:26
*
 */
@Controller
public class MedicalRecordController {

	@Autowired
	private MedicalRecordService medicalRecordService;
	
	/**
	 * 
	* @Title:getAllMedicalRecordByPage
	* @Description:分页查询所有病案
	* @param:@param curpage
	* @param:@param pagesize
	* @param:@return
	* @return:Map
	* @throws
	* @author:Hamster
	* @Date:2019年8月4日 下午5:23:54
	 */
	@ResponseBody
	@GetMapping("/get_medical_record_byPage")
	public Map getAllMedicalRecordByPage(int curpage,int pagesize){
		return  medicalRecordService.getAllMedicalRecordByPage(curpage, pagesize);
	}
	
	
	@ResponseBody
	@GetMapping("/get_all_medical_record")
	public List <MedicalRecord> getAllMedicalRecord(){
		return medicalRecordService.getAllMedicalRecord();
	}
	
	/**
	 * 
	* @Title:closeMedicalRecord
	* @Description:病案封档
	* @param:@param record
	* @return:void
	* @throws
	* @author:Hamster
	* @Date:2019年8月5日 上午10:29:44
	 */
	@ResponseBody
	@PostMapping("/close_medical_record")
	public void closeMedicalRecord(@RequestBody MedicalRecord record){
		System.out.println(record.getMedRid());
		medicalRecordService.closeMedicalRecord(record);
	}
	
	/**
	 * 
	* @Title:openMedicalRecord
	* @Description:病案解封
	* @param:@param record
	* @return:void
	* @throws
	* @author:Hamster
	* @Date:2019年8月5日 上午10:30:39
	 */
	@ResponseBody
	@PostMapping("/open_medical_record")
	public void openMedicalRecord(@RequestBody MedicalRecord record){
		medicalRecordService.openMedicalRecord(record);
	}
}
