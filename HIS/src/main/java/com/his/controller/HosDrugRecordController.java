package com.his.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.HosDrugRecord;
import com.his.pojo.JsonResult;
import com.his.service.HosDrugRecordService;

import oracle.jdbc.proxy.annotation.Post;

/**
 * 
* @ClassName: HosDrugRecordController  
* @Description: 住院用药记录  
* @author Hamster
* @date 2019年8月13日  下午9:24:11
*
 */
@Controller
public class HosDrugRecordController {

	@Autowired
	private HosDrugRecordService hosDrugRecordService;
	
	/**
	 * 
	* @Title:addHosDrugRecord
	* @Description:新增住院用药记录和用药明细
	* @param:@param record
	* @param:@throws ParseException
	* @return:void
	* @throws
	* @author:Hamster
	* @Date:2019年8月13日 下午9:24:28
	 */
	@ResponseBody
	@PostMapping("/add_hos_drug_record")
	public JsonResult addHosDrugRecord(@RequestBody HosDrugRecord record) throws ParseException{
		JsonResult result = new JsonResult();
		try{
			result.setResult(hosDrugRecordService.addHosDrugRecord(record));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	
}
