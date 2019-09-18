package com.his.controller;


import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	
	/**
	 * 
	* @Title:addTransOffice
	* @Description:新增转科记录
	* @param:@param transOfficeRecord
	* @param:@throws ParseException
	* @return:void
	* @throws
	* @author:Hamster
	* @Date:2019年8月2日 下午10:47:15
	 */
	@ResponseBody
	@PostMapping("/add_trans_office_record")
	public void addTransOffice(@RequestBody TransOfficeRecord transOfficeRecord) throws ParseException{
		transOfficeService.addTransOffice(transOfficeRecord);
	}
	
	/**
	 * 
	* @Title:changeMessage
	* @Description:转科后修改床位和病房信息
	* @param:@param inBid
	* @param:@param outBid
	* @return:void
	* @throws
	* @author:Hamster
	* @Date:2019年8月2日 下午10:49:01
	 */
	@ResponseBody
	@GetMapping("/change_message")
	public void changeMessage(String inBid,String outBid){
		transOfficeService.changeMessage(inBid, outBid);
	}
	
	@ResponseBody
	@GetMapping("/get_trans_record_byPage")
	public Map getTransRecordByPage(String start,String end,String text4,int curpage,int pagesize) throws ParseException{
		String cardName = "%"+text4+"%";
		return transOfficeService.getTransRecordByPage(start, end, cardName, curpage, pagesize);
	}
}
