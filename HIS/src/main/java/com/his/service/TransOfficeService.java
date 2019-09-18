package com.his.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IHosBedDao;
import com.his.dao.IHosPatientDao;
import com.his.dao.ITransOfficeDao;
import com.his.dao.IWardRoomDao;
import com.his.pojo.HosBed;
import com.his.pojo.HospitalizedPatient;
import com.his.pojo.TransOfficeRecord;
import com.his.pojo.WardRoom;

/**
 * 
* @ClassName: TransOfficeService  
* @Description: TODO 住院转科记录
* @author Hamster
* @date 2019年7月31日  下午8:07:51
*
 */

@Service
@Transactional(rollbackFor=Exception.class)
public class TransOfficeService {

	@Autowired
	private ITransOfficeDao transOfficeDao;
	@Autowired
	private IHosPatientDao hosPatientDao;
	@Autowired
	private IHosBedDao hosBedDao;
	@Autowired
	private IWardRoomDao wardRoomDao;
	
	/**
	 * 
	* @Title:addTransOffice
	* @Description:新增转科记录
	* @param:@param transOfficeRecord
	* @param:@throws ParseException
	* @return:void
	* @throws
	* @author:Hamster
	* @Date:2019年8月2日 下午10:32:01
	 */
	public void addTransOffice(TransOfficeRecord transOfficeRecord) throws ParseException{
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		transOfficeRecord.setTransOid(uuid.substring(0,8));	
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = dateFormat.format(new Date());
		transOfficeRecord.setOutOfficeTime(dateFormat.parse(time));
		transOfficeDao.save(transOfficeRecord);
		
	}
	
	/**
	 * 
	* @Title:changeMessage
	* @Description:转科后修改床位和病房信息
	* @param:@param inBid
	* @param:@param outBid
	* @return:void
	* @throws
	* @author:Hamster
	* @Date:2019年8月3日 上午8:35:54
	 */
	public void changeMessage(String inBid,String outBid){
		
		HosBed outBed = hosBedDao.getBedByBid(outBid);
		outBed.setHospitalizedPatient(null);
		outBed.setHosBstate(null);
		hosBedDao.save(outBed);
		
		
		HospitalizedPatient patient = hosPatientDao.getPatientByBid(outBid);
		patient.setHosBid(inBid);
		hosPatientDao.save(patient);
		
		
		WardRoom outRoom = wardRoomDao.getWardRoomByRid(outBid);
		outRoom.setWNum(outRoom.getWNum()-1);
		wardRoomDao.save(outRoom);
		
		HosBed inBed = hosBedDao.getBedByBid(inBid);
		inBed.setHospitalizedPatient(patient);
		inBed.setHosBstate("已入住");
		hosBedDao.save(inBed);
		
		WardRoom inRoom = wardRoomDao.getWardRoomByRid(inBid);
		inRoom.setWNum(inRoom.getWNum()-1);
		wardRoomDao.save(inRoom);
		
	}
	
	/**
	 * 
	* @Title:getTransRecordByPage
	* @Description:查询所有住院转科转床记录
	* @param:@return
	* @return:List<TransOfficeRecord>
	 * @throws ParseException 
	* @throws
	* @author:Hamster
	* @Date:2019年9月5日 下午5:14:06
	 */
	public Map getTransRecordByPage(String start,String end,String cardName,int curpage,int pagesize) throws ParseException{
		List <TransOfficeRecord> list;
		if(start==null||end==null){
			list = transOfficeDao.getTransRecordByPage(cardName, PageRequest.of(curpage-1, pagesize));
		}
		else{
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			list = transOfficeDao.getTransRecordByPageandTime(format.parse(start), format.parse(end), cardName, PageRequest.of(curpage-1, pagesize));
		}
		long total = list.size();
		Map map = new HashMap<>();
		map.put("list", list);
		map.put("total", total);
		return map;
	}
}
