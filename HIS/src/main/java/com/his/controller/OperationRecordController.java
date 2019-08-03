package com.his.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.bean.OperationRecordbean;
import com.his.pojo.OperationRecord;
import com.his.service.OperationRecordService;
/**  
* @ClassName: OperationRecordController  
* @Description: TODO(手术记录controller)  
* @author TRC
* @date 2019年7月30日  上午9:09:30
*    
*/
@Controller
public class OperationRecordController {
@Autowired
	private OperationRecordService operationRecordService;
/**
 * 
* @Title:getallOperationRecords
* @Description:TODO查询所有手术记录
* @param:@return
* @return:List<OperationRecord>
* @throws
* @author:TRC
* @Date:2019年7月31日 下午5:55:11
 */
@ResponseBody
@GetMapping("get_op_record")
    public Map getallRecordbeans(int curpage, int pagesize,String sou){

    	return operationRecordService.getallRecordbeans(curpage,pagesize,"%"+sou+"%");
    } 
/**
 * 
* @Title:addOperationRecord
* @Description:TODO编辑手术记录
* @param:@param operationRecord
* @return:void
* @throws
* @author:TRC
* @Date:2019年8月2日 上午10:20:59
 */
@ResponseBody
@PostMapping("eidt_op_record")
    public void addOperationRecord(@RequestBody OperationRecord operationRecord) {
    	operationRecordService.addOperationRecord(operationRecord);
    }
}
