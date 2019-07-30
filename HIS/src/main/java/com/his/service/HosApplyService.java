package com.his.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IHosApplyDao;
import com.his.pojo.HosPatientsApply;

/**
 * 住院患者申请
 * @author dell
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class HosApplyService {

	@Autowired
	private IHosApplyDao hosApplyDao;
	
	public Map getAllHosApplies(int curpage,int pagesize){
		List <HosPatientsApply> list = hosApplyDao.getAllHosApplies(PageRequest.of(curpage-1, pagesize));
		long total = hosApplyDao.count();
		Map map = new HashMap<>();
		map.put("list", list);
		map.put("total", total);
		return map;
	}
}
