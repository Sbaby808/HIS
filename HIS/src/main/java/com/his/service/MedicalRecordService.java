package com.his.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IMedicalRecordDao;
import com.his.pojo.MedicalRecord;


/**
 * 住院病案
 * @author dell
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class MedicalRecordService {
	
	@Autowired
	private IMedicalRecordDao medicalRecordDao;
	
	public Map getAllMedicalRecord(int curpage,int pagesize){
		List <MedicalRecord> list = medicalRecordDao.getAllMedicalRecord(PageRequest.of(curpage-1, pagesize));
		long total = medicalRecordDao.count();
		Map map = new HashMap<>();
		map.put("list", list);
		map.put("total", total);
		return map;
	}
	
	public void addMedicalRecord(MedicalRecord medicalRecord){
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		medicalRecord.setMedRid(uuid.substring(0,8));
		medicalRecord.setMedInTime(new Date());
		medicalRecordDao.save(medicalRecord);
	}
}
