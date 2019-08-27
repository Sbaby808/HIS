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
	public Map getAllMedicalRecordByPage(String cardName,String ksName,int curpage,int pagesize){
		List <MedicalRecord> list = medicalRecordDao.getAllMedicalRecordByPage(cardName,ksName,PageRequest.of(curpage-1, pagesize));
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
	
	/**
	 * 
	* @Title:closeMedicalRecord
	* @Description:病案封档
	* @param:@param record
	* @return:void
	* @throws
	* @author:Hamster
	* @Date:2019年8月5日 上午10:26:17
	 */
	public void closeMedicalRecord(MedicalRecord record){
		record.setMedOther("已封档");
		medicalRecordDao.save(record);
	}
	
	/**
	 * 
	* @Title:openMedicalRecord
	* @Description:病案解封
	* @param:@param record
	* @return:void
	* @throws
	* @author:Hamster
	* @Date:2019年8月5日 上午10:27:06
	 */
	public void openMedicalRecord(MedicalRecord record){
		record.setMedOther(null);
		medicalRecordDao.save(record);
	}
	
	/**
	 * 
	* @Title:getMedicalRecordByhospId
	* @Description:根据住院id查询病案
	* @param:@param hospId
	* @param:@return
	* @return:MedicalRecord
	* @throws
	* @author:Hamster
	* @Date:2019年8月6日 下午8:24:09
	 */
	public MedicalRecord getMedicalRecordByhospId(String hospId){
		return medicalRecordDao.getMedicalRecordByhospId(hospId);
	}
}
