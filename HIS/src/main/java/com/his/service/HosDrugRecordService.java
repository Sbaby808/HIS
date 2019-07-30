package com.his.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IHosDrugRecordDao;
import com.his.pojo.HosDrugRecord;
/**
 * 住院用药记录
 * @author dell
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class HosDrugRecordService {

	@Autowired
	private IHosDrugRecordDao hosDrugRecordDao;
	
	public Map getAllDrugRecords(int curpage,int pagesize){
		List <HosDrugRecord> list = hosDrugRecordDao.getAllDrugRecords(PageRequest.of(curpage-1, pagesize));
		long total = hosDrugRecordDao.count();
		Map map = new HashMap<>();
		map.put("list", list);
		map.put("total", total);
		return map;
	}
}
