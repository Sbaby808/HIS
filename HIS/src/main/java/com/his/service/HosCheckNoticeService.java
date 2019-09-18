package com.his.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IHosCheckNoticeDao;
import com.his.dao.IHosDiagRecordDao;
import com.his.pojo.HosCheckNotice;
import com.his.pojo.HosDiagnosticRecord;

/**
 * 
* @ClassName: HosCheckNoticeService  
* @Description: 住院检查通知单 
* @author Hamster
* @date 2019年9月3日  下午2:06:15
*
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class HosCheckNoticeService {

	@Autowired
	private IHosCheckNoticeDao hosCheckNoticeDao;
	@Autowired
	private IHosDiagRecordDao hosDiagRecordDao;
	
	/**
	 * 
	* @Title:getHosCheckNoticeBydiagId
	* @Description:根据诊断记录id查询住院检查通知单
	* @param:@param diagId
	* @param:@return
	* @return:List<HosCheckNotice>
	* @throws
	* @author:Hamster
	* @Date:2019年9月3日 下午2:07:18
	 */
	public List<HosCheckNotice> getHosCheckNoticeBydiagId(String diagId) {
		HosDiagnosticRecord record = hosDiagRecordDao.findById(diagId).get();
		List <HosCheckNotice> list = record.getHosDoctorAdvice().getHosCheckNotices();
		return list;
	}
	
	
}
