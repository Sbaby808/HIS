package com.his.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.his.dao.IHosPrescriptionDao;
import com.his.pojo.HosPrescription;

/**
 * 住院处方
 * @author dell
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class HosPrescriptionService {
	
	@Autowired
	private IHosPrescriptionDao hosPrescriptionDao;
	
	public Map getAllHosPrescription(int curpage,int pagesize){
		List <HosPrescription> list =hosPrescriptionDao.getAllHosPrescription(PageRequest.of(curpage-1, pagesize));
		long total = hosPrescriptionDao.count();
		Map map = new HashMap<>();
		map.put("list", list);
		map.put("total", total);
		return map;
	}
}
