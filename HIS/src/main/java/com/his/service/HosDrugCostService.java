package com.his.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IHosDrugCostDao;
import com.his.pojo.HosDrugCost;
/**
 * 住院药品扣费记录
 * @author dell
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class HosDrugCostService {

	@Autowired
	private IHosDrugCostDao hosDrugCostDao;
	
	public Map getAllDrugCosts(int curpage,int pagesize){
		List <HosDrugCost> list = hosDrugCostDao.getAllDrugCosts(PageRequest.of(curpage-1, pagesize));
		long total = hosDrugCostDao.count();
		Map map = new HashMap<>();
		map.put("list", list);
		map.put("total", total);
		return map;
	}
}
