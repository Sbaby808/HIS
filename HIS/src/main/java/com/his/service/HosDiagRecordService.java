package com.his.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IHosDiagRecordDao;
import com.his.pojo.HosDiagnosticRecord;

@Service
@Transactional(rollbackFor=Exception.class)
public class HosDiagRecordService {

	@Autowired
	private IHosDiagRecordDao hosDiagRecordDao;
	
	/**
	 * 
	* @Title:getDiagRecord
	* @Description:无分页查询所有住院诊断记录
	* @param:@return
	* @return:List<HosDiagnosticRecord>
	* @throws
	* @author:Hamster
	* @Date:2019年8月5日 上午11:32:59
	 */
	public List <HosDiagnosticRecord> getDiagRecord(){
		return hosDiagRecordDao.getDiagRecord();
	}
	
	/**
	 * 
	* @Title:getDiagRecordByPage
	* @Description:分页查询所有住院诊断记录
	* @param:@param curpage
	* @param:@param pagesize
	* @param:@return
	* @return:Map
	* @throws
	* @author:Hamster
	* @Date:2019年8月5日 上午11:35:35
	 */
	public Map getDiagRecordByPage(int curpage,int pagesize){
		List <HosDiagnosticRecord> list = hosDiagRecordDao.getDiagRecordByPage(PageRequest.of(curpage-1, pagesize));
		long total = hosDiagRecordDao.count();
		Map map = new HashMap<>();
		map.put("list", list);
		map.put("total", total);
		return map;
	}
}
