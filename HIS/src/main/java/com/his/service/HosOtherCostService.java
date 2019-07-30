package com.his.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IHosOtherCostDao;
import com.his.pojo.HosOtherCost;
/**
 * 住院其他扣费记录
 * @author dell
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class HosOtherCostService {

	@Autowired
	private IHosOtherCostDao hosOtherCostDao;
	
	public Map getAllOtherCosts(int curpage,int pagesize){
		List <HosOtherCost> list = hosOtherCostDao.getAllOtherCosts(PageRequest.of(curpage-1, pagesize));
		long total = hosOtherCostDao.count();
		Map map = new HashMap<>();
		map.put("list", list);
		map.put("total", total);
		return map;
	}
}
