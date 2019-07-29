package com.his.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.dao.IHosPatientDao;
import com.his.pojo.HospitalizedPatient;

@Service
@Transactional(rollbackFor = Exception.class)
public class HosPatientsService {
	
	@Autowired
	private IHosPatientDao hosPatientDao;
	
	/*public Map getHosPatientsByPage(int curpage,int pagesize){
		List <HospitalizedPatient> patients = hosPatientDao.getAllPatientsByPage(PageRequest.of(curpage-1,pagesize));
		long total = hosPatientDao.count();
		Map map = new HashMap<>();
		map.put("list", patients);
		map.put("total", total);
		return map;
	}*/
	
	public List getHosPatients(){
		return hosPatientDao.getAllPatientsByPage();
	}
}
