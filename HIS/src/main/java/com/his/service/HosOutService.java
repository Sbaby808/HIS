package com.his.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IHosOutDao;
import com.his.pojo.OutHospitaiRecord;

/**
 * 
* @ClassName: HosOutService  
* @Description: 出院记录 
* @author Hamster
* @date 2019年8月12日  下午9:29:37
*
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class HosOutService {
	
	@Autowired
	private IHosOutDao hosOutDao;
	
	public Map getAllOutRecords(int curpage,int pagesize){
		List <OutHospitaiRecord> list = hosOutDao.getAllOutRecords(PageRequest.of(curpage-1, pagesize));
		long total = hosOutDao.count();
		Map map = new HashMap<>();
		map.put("list", list);
		map.put("total", total);
		return map;
	}
}
