package com.his.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.his.dao.IOperationRecordDao;
import com.his.pojo.OperationRecord;

@Service
public class OperationRecordService {

	@Autowired
	private IOperationRecordDao iOperationRecordDao;
	
	public List<OperationRecord> getallOperationRecords(){
		return (List<OperationRecord>) iOperationRecordDao.findAll();
	}
}
