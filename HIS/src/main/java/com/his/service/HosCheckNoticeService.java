package com.his.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IHosCheckNoticeDao;

@Service
@Transactional(rollbackFor=Exception.class)
public class HosCheckNoticeService {

	@Autowired
	private IHosCheckNoticeDao hosCheckNoticeDao;
	
	
}
