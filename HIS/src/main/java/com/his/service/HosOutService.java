package com.his.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IHosOutDao;


@Service
@Transactional(rollbackFor=Exception.class)
public class HosOutService {
	
	@Autowired
	private IHosOutDao hosOutDao;
	
	/*public Map getAllOutRecords(int curpage,int pagesize){
		
	}*/
}
