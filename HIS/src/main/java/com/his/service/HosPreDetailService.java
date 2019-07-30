package com.his.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IHosPreDetailDao;
import com.his.pojo.HosPrescriptionDetail;

/**
 * 住院处方明细
 * @author dell
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class HosPreDetailService {
	
	@Autowired
	private IHosPreDetailDao hosPreDetailDao;
	
	public Map getAllHosPreDetails(int curpage,int pagesize){
		List <HosPrescriptionDetail> list = hosPreDetailDao.getAllHosPreDetails(PageRequest.of(curpage-1, pagesize));
		long total = hosPreDetailDao.count();
		Map map = new HashMap<>();
		map.put("list", list);
		map.put("total", total);
		return map;
	}
}
