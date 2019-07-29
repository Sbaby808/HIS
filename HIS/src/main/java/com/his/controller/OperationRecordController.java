package com.his.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.OperationRecord;
import com.his.service.OperationRecordService;
@Controller
public class OperationRecordController {
@Autowired
	private OperationRecordService operationRecordService;
@ResponseBody
@GetMapping("get_op_record")
public List<OperationRecord> getallOperationRecords(){
    List<OperationRecord> list=operationRecordService.getallOperationRecords();
    for (OperationRecord operationRecord : list) {
		System.out.println(operationRecord.getOpeType());
	}
	return operationRecordService.getallOperationRecords();
}
}
