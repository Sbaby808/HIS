package com.his.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IWardDao;
import com.his.pojo.Ward;


/**
 * 住院病区
 * @author dell
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class WardService {

	@Autowired
	private IWardDao wardDao;
	
	/**
	 * 查询所有病区
	 * @return
	 */
	public Map getAllWards(int curpage,int pagesize){
		List <Ward> list = wardDao.getAllWards(PageRequest.of(curpage-1, pagesize));
		long total = wardDao.count();
		Map map = new HashMap<>();
		map.put("list", list);
		map.put("total", total);
		return map;
	}
	
	/**
	 * 新增病区
	 * @param ward
	 */
	public void addWard(Ward ward){
		if(ward.getWardId()!=null){
			wardDao.save(ward);
		}
		else{
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			ward.setWardId(uuid.substring(0,8));
			wardDao.save(ward);
		}	
	}
}
