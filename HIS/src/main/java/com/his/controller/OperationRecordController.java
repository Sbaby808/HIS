package com.his.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.his.bean.Opeempbean;
import com.his.bean.OperationRecordbean;
import com.his.pojo.EmpInformation;
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
    public Map getallRecordbeans(int curpage, int pagesize,String sou,String ygxh){

    	return operationRecordService.getallRecordbeans(curpage,pagesize,"%"+sou+"%",ygxh);
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
@GetMapping("eidt_op_record")
    public void addOperationRecord(String Str,String opeid,String opeDiagnose,String opeType,String opeJg,@DateTimeFormat(pattern = "yyyy-MM-dd") @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8") Date opetime,String opeStatus) {
    	
	operationRecordService.addOperationRecord(Str,opeid,opeDiagnose,opeType,opeJg,opetime,opeStatus);
    }
/**
 * 
* @Title:getemp
* @Description:TODO根据手术项查可以做该手术的员工
* @param:@param opename
* @param:@return
* @return:Map
* @throws
* @author:TRC
* @Date:2019年8月7日 下午4:49:12
 */
@ResponseBody
@GetMapping("get_emp")
    public Map getemp(String opename) {
    	
    	return operationRecordService.getopemp(opename);
    }

/**
 * 
* @Title:getopeempbyopeid
* @Description:TODO根据手术记录id查员工
* @param:@param opeid
* @param:@return
* @return:List<Opeempbean>
* @throws
* @author:TRC
* @Date:2019年8月7日 下午4:48:00
 */
@ResponseBody
@GetMapping("get_ope_emps")
    public List<Opeempbean> getopeempbyopeid(String opeid){
    	return operationRecordService.getopeempbyopeid(opeid);
    }
}
