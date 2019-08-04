package com.his.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IMedicalRecordDao;
import com.his.pojo.MedicalRecord;


/**
 * 
* @ClassName: MedicalRecordService  
* @Description: 住院病案  
* @author Hamster
* @date 2019年8月4日  下午5:20:08
*
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class MedicalRecordService {
	
	@Autowired
	private IMedicalRecordDao medicalRecordDao;
	
	/**
	 * 
	* @Title:getAllMedicalRecord
	* @Description:分页查询所有住院病案
	* @param:@param curpage
	* @param:@param pagesize
	* @param:@return
	* @return:Map
	* @throws
	* @author:Hamster
	* @Date:2019年8月4日 下午5:20:55
	 */
	public Map getAllMedicalRecordByPage(int curpage,int pagesize){
		List <MedicalRecord> list = medicalRecordDao.getAllMedicalRecordByPage(PageRequest.of(curpage-1, pagesize));
		long total = medicalRecordDao.count();
		Map map = new HashMap<>();
		map.put("list", list);
		map.put("total", total);
		return map;
	}
	
	/**
	 * 
	* @Title:getAllMedicalRecord
	* @Description:无分页查询所有住院病案
	* @param:@return
	* @return:List<MedicalRecord>
	* @throws
	* @author:Hamster
	* @Date:2019年8月4日 下午5:22:56
	 */
	public List <MedicalRecord> getAllMedicalRecord(){
		return medicalRecordDao.getAllMedicalRecord();
	}
}
