package com.his.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IHosEmpDao;
import com.his.pojo.HosEmp;
import com.his.pojo.HosEmpPK;

@Service
@Transactional(rollbackFor=Exception.class)
public class HosEmpService {

	@Autowired
	private IHosEmpDao hosEmpDao;
	
	
}
