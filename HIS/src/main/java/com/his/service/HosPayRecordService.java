package com.his.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IHosPayRecordDao;
import com.his.pojo.HosPayRecord;

/**
 * 住院患者缴费记录
 * @author dell
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class HosPayRecordService{
	
	@Autowired
	private IHosPayRecordDao hosPayRecordDao;
	
	private Map getAllPayRecord(int curpage,int pagesize){
		List <HosPayRecord> list = hosPayRecordDao.getAllPayRecord(PageRequest.of(curpage-1, pagesize));
		long total = hosPayRecordDao.count();
		Map map = new HashMap<>();
		map.put("list", list);
		map.put("total", total);
		return map;
	}
	
}
