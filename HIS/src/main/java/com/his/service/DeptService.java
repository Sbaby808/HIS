package com.his.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IDeptDao;
import com.his.pojo.Dept;

@Service
@Transactional(rollbackFor = Exception.class)
public class DeptService {
	@Autowired
	private IDeptDao deptDao;
	
	public List <Dept> getAll(){
		return deptDao.getDepts();
	}
}
