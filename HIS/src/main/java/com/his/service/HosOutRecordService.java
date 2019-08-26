package com.his.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IHosOutRecordDao;
import com.his.pojo.OutHospitaiRecord;

/**
 * 
* @ClassName: HosOutRecordService  
* @Description: 出院记录
* @author Hamster
* @date 2019年8月23日  下午8:11:17
*
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class HosOutRecordService {

	@Autowired
	private IHosOutRecordDao hosOutRecordDao;
	
	public Map getHosOutRecord(String cardName,int curpage,int pagesize){
		List <OutHospitaiRecord> list = hosOutRecordDao.getOutRecord(cardName,PageRequest.of(curpage-1, pagesize));
		Long total = hosOutRecordDao.count();
		Map map = new HashMap<>();
		map.put("list", list);
		map.put("total", total);
		return map;
	}
}
