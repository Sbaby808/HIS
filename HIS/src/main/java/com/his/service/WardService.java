package com.his.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IWardDao;
import com.his.pojo.Ward;

@Service
@Transactional(rollbackFor=Exception.class)
public class WardService {

	@Autowired
	private IWardDao wardDao;
	
	public List <Ward> getAllWards(){
		return wardDao.getAllWards();
	}
	
}
