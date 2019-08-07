package com.his.service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IHosBedDao;
import com.his.dao.IHosPatientDao;
import com.his.dao.ITransBedDao;
import com.his.dao.IWardRoomDao;
import com.his.pojo.BedTransRecord;
import com.his.pojo.HosBed;
import com.his.pojo.HospitalizedPatient;
import com.his.pojo.WardRoom;


/**
 * 住院转床记录
* @ClassName: TransBedService  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author Hamster
* @date 2019年7月31日  下午8:55:43
*
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class TransBedService {
	
	@Autowired
	private ITransBedDao transBedDao;
	@Autowired
	private IHosPatientDao hosPatientDao;
	@Autowired
	private IHosBedDao hosBedDao;
	@Autowired
	private IWardRoomDao wardRoomDao;
	
	/**
	 * 
	* @Title:addTransBed
	* @Description:新增转床记录
	* @param:@param bedTransRecord
	* @param:@throws ParseException
	* @return:void
	* @throws
	* @author:Hamster
	* @Date:2019年8月1日 上午9:36:25
	 */
	public void addTransBed(BedTransRecord bedTransRecord) throws ParseException{
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		bedTransRecord.setBrId(uuid.substring(0,8));
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = dateFormat.format(new Date());
		bedTransRecord.setBrTime(dateFormat.parse(time));
		transBedDao.save(bedTransRecord);
	}
	
}
