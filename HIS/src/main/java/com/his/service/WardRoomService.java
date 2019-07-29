package com.his.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IWardDao;
import com.his.dao.IWardRoomDao;
import com.his.pojo.WardRoom;

@Service
@Transactional(rollbackFor=Exception.class)
public class WardRoomService {
	
	@Autowired
	private IWardRoomDao wardRoomDao;
	
	public Map getAllWardRoom(int curpage,int pagesize){
		List <WardRoom> list = wardRoomDao.getAllWardRoom(PageRequest.of(curpage-1, pagesize));
		long total = wardRoomDao.count();
		Map map = new HashMap<>();
		map.put("list", list);
		map.put("total", total);
		return map;
	}
}
