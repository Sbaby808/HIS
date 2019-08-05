package com.his.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.HosDiagnosticRecord;
import com.his.service.HosDiagRecordService;

@Controller
public class HosDiagRecordController {

	@Autowired
	private HosDiagRecordService hosDiagRecordService;
	
	/**
	 * 
	* @Title:getDiagRecord
	* @Description:无分页查询所有住院诊断记录
	* @param:@return
	* @return:List<HosDiagnosticRecord>
	* @throws
	* @author:Hamster
	* @Date:2019年8月5日 上午11:39:12
	 */
	@ResponseBody
	@GetMapping("/get_diag_record")
	public List <HosDiagnosticRecord> getDiagRecord(){
		return hosDiagRecordService.getDiagRecord();
	}
	
	/**
	 * 
	* @Title:getDiagRecordByPage
	* @Description:分页查询所有住院诊断记录
	* @param:@param curpage
	* @param:@param pagesize
	* @param:@return
	* @return:Map
	* @throws
	* @author:Hamster
	* @Date:2019年8月5日 上午11:39:40
	 */
	@ResponseBody
	@GetMapping("/get_diag_record_byPage")
	public Map getDiagRecordByPage(int curpage,int pagesize){
		return hosDiagRecordService.getDiagRecordByPage(curpage, pagesize);
	}
}
