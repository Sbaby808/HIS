package com.his.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.OperPayRecord;
import com.his.service.OperPayRecordService;
/**  
* @ClassName: OperPayRecordController  
* @Description: TODO(手术缴费记录controller)  
* @author TRC
* @date 2019年7月30日  上午9:09:30
*    
*/
@Controller
public class OperPayRecordController {

	@Autowired
	private OperPayRecordService operPayRecordService;
	
	@ResponseBody
	@GetMapping("get_OPpay_re")
	public List<OperPayRecord> getallOperPayRecords(){
		return operPayRecordService.getallOperPayRecords();
	}
	@ResponseBody
	@GetMapping("add_OPpay_re")
	public void addrecord() {
		OperPayRecord operPayRecord=new OperPayRecord();
		operPayRecord.setOperJfId("1");
		operPayRecordService.addrecord(operPayRecord);
	}
}
