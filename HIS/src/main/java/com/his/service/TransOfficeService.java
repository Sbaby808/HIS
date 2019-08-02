package com.his.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IHosPatientDao;
import com.his.dao.ITransOfficeDao;
import com.his.pojo.HospitalizedPatient;
import com.his.pojo.TransOfficeRecord;

/**
 * 
* @ClassName: TransOfficeService  
* @Description: TODO 住院转科记录
* @author Hamster
* @date 2019年7月31日  下午8:07:51
*
 */

@Service
@Transactional(rollbackFor=Exception.class)
public class TransOfficeService {

	@Autowired
	private ITransOfficeDao transOfficeDao;
	
	
	public void addTransOffice(TransOfficeRecord transOfficeRecord) throws ParseException{
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		transOfficeRecord.setTransOid(uuid.substring(0,8));	
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = dateFormat.format(new Date());
		transOfficeRecord.setOutOfficeTime(dateFormat.parse(time));
		transOfficeDao.save(transOfficeRecord);
		//HospitalizedPatient patient = transOfficeRecord.getHospitalizedPatient();
		
	}
}
