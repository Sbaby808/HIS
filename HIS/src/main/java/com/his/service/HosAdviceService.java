package com.his.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IHosAdviceDao;
import com.his.pojo.HosDoctorAdvice;
/**
 * 住院医嘱
 * @author dell
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class HosAdviceService {

	@Autowired
	private IHosAdviceDao hosAdviceDao;
	
	public Map getAllHosAdvices(int curpage,int pagesize){
		List <HosDoctorAdvice> list = hosAdviceDao.getAllHosAdvices(PageRequest.of(curpage-1, pagesize));
		long total = hosAdviceDao.count();
		Map map = new HashMap<>();
		map.put("list", list);
		map.put("total", total);
		return map;
	}
}
