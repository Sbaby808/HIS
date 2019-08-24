package com.his.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IHosDiagRecordDao;
import com.his.dao.IHosDocAdviceDao;
import com.his.dao.IHosPrescriptionDao;
import com.his.dao.IMedicalRecordDao;
import com.his.pojo.HosDiagnosticRecord;
import com.his.pojo.HosDoctorAdvice;
import com.his.pojo.HosPrescription;
import com.his.pojo.MedicalRecord;

/**
 * 
* @ClassName: HosDiagRecordService  
* @Description: 住院诊断记录 
* @author Hamster
* @date 2019年8月6日  上午8:49:33
*
 */

@Service
@Transactional(rollbackFor=Exception.class)
public class HosDiagRecordService {

	@Autowired
	private IHosDiagRecordDao hosDiagRecordDao;
	@Autowired
	private IHosDocAdviceDao hosDocAdviceDao;
	@Autowired
	private IHosPrescriptionDao hosPrescriptionDao;
	
	
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
	public Map getDiagRecordByPage(String cardName,String ksName,int curpage,int pagesize){
		List <HosDiagnosticRecord> list = hosDiagRecordDao.getDiagRecordByPage(cardName,ksName,PageRequest.of(curpage-1, pagesize));
		long total = hosDiagRecordDao.countInDiag();
		Map map = new HashMap<>();
		map.put("list", list);
		map.put("total", total);
		return map;
	}
	
	/**
	 * 
	* @Title:addHosDiagRecord
	* @Description:新增诊断记录
	* @param:@param record
	* @param:@throws ParseException
	* @return:void
	* @throws
	* @author:Hamster
	* @Date:2019年8月6日 上午8:56:35
	 */
	public void addHosDiagRecord(HosDiagnosticRecord record) throws ParseException{
		HosDoctorAdvice advice = new HosDoctorAdvice();
		advice.setHosDiagnosticRecord(record);
		hosDocAdviceDao.save(advice);
		
		HosPrescription prescription = new HosPrescription();
		prescription.setHosDiagnosticRecord(record);
		hosPrescriptionDao.save(prescription);
		
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = dateFormat.format(new Date());
		record.setHosDiagTime(dateFormat.parse(time));
		record.setHosDcoId(advice.getHosDcoId());
		record.setHosPreId(prescription.getHosPreId());
		hosDiagRecordDao.save(record);	
		
	}
	
	
	/**
	 * 
	* @Title:delHosDiagRecord
	* @Description:删除诊断记录
	* @param:@param record
	* @return:void
	* @throws
	* @author:Hamster
	* @Date:2019年8月6日 上午8:57:33
	 */
	public void delHosDiagRecord(HosDiagnosticRecord record){
		hosDiagRecordDao.delete(record);
	}
	
	public List <HosDiagnosticRecord> getDiagRecordbyMid(String medRid){
		List <HosDiagnosticRecord> list = hosDiagRecordDao.getDiagRecordbyMid(medRid);
		return list;
	}
}
