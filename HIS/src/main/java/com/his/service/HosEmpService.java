package com.his.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IHosEmpDao;
import com.his.pojo.HosEmp;

@Service
@Transactional(rollbackFor=Exception.class)
public class HosEmpService {

	@Autowired
	private IHosEmpDao hosEmpDao;
	
	public void addHosEmp(HosEmp hosEmp){
		hosEmp.setHosDuty("登记员");
		hosEmpDao.save(hosEmp);
	}
}
