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
 * 
* @ClassName: HosPayRecordService  
* @Description:住院缴费记录 
* @author Hamster
* @date 2019年9月18日  下午8:03:16
*
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class HosPayRecordService{
	
	@Autowired
	private IHosPayRecordDao hosPayRecordDao;
	
	public Map getAllPayRecord(String cardName,int curpage,int pagesize){
		List <HosPayRecord> list = hosPayRecordDao.getAllPayRecord(cardName,PageRequest.of(curpage-1, pagesize));
		long total = hosPayRecordDao.countNum(cardName);
		Map map = new HashMap<>();
		map.put("list", list);
		map.put("total", total);
		return map;
	}
	
	
	
}
