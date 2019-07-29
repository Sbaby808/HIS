package com.his.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IHosBedDao;
import com.his.pojo.HosBed;

@Service
@Transactional(rollbackFor=Exception.class)
public class HosBedService {
	
	@Autowired
	private IHosBedDao hosBedDao;
	
	public Map getAllBeds(int curpage,int pagesize){
		List <HosBed> list = hosBedDao.getAllBeds(PageRequest.of(curpage-1, pagesize));
		long total  = hosBedDao.count();
		Map map = new HashMap<>();
		map.put("list", list);
		map.put("total", total);
		return map;
	}
}
