package com.his.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.dao.IHosBedDao;
import com.his.dao.IHosPatientDao;
import com.his.dao.IMedicalRecordDao;
import com.his.dao.IWardRoomDao;
import com.his.pojo.HosBed;
import com.his.pojo.HospitalizedPatient;
import com.his.pojo.MedicalRecord;
import com.his.pojo.WardRoom;
/**
 * 住院登记
 * @author dell
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class HosPatientsService {
	
	@Autowired
	private IHosPatientDao hosPatientDao;
	@Autowired
	private IHosBedDao hosBedDao;
	@Autowired
	private IWardRoomDao wardRoomDao;
	@Autowired
	private IMedicalRecordDao medicalRecordDao;

	
	/*public Map getHosPatientsByPage(int curpage,int pagesize){
		List <HospitalizedPatient> patients = hosPatientDao.getAllPatientsByPage(PageRequest.of(curpage-1,pagesize));
		long total = hosPatientDao.count();
		Map map = new HashMap<>();
		map.put("list", patients);
		map.put("total", total);
		return map;
	}*/
	/**
	 * 
	* @Title:getHosPatients
	* @Description:查询所有住院患者
	* @param:@return
	* @return:List<HospitalizedPatient>
	* @throws
	* @author:Hamster
	* @Date:2019年8月1日 下午8:01:33
	 */
	public List<HospitalizedPatient> getHosPatients(){
		return hosPatientDao.getAllPatientsByPage();
	}
	
	/**
	 * 
	* @Title:getPatientsByWroomId
	* @Description:根据病房id查询住院患者
	* @param:@param id
	* @param:@return
	* @return:List<HospitalizedPatient>
	* @throws
	* @author:Hamster
	* @Date:2019年8月1日 下午8:01:53
	 */
	public List <HospitalizedPatient> getPatientsByWroomId(String id){
		return hosPatientDao.getPatientsByWroomId(id);
	}
	
	/**
	 * 
	* @Title:addHosPatient
	* @Description:新增住院患者
	* @param:@param patient
	* @return:void
	* @throws
	* @author:Hamster
	* @Date:2019年8月1日 下午8:03:05
	 */
	public void addHosPatient(HospitalizedPatient patient){
		
		MedicalRecord record = new MedicalRecord();
		record.setMedInDept(patient.getDepartment().getKsName());
		record.setMedInTime(new Date());
		record.setHospitalizedPatient(patient);
		medicalRecordDao.save(record);
		
		patient.setHospState("在院");
		patient.setMedRid(record.getMedRid());
		patient.setHosBid(patient.getHosBid());
		patient.setRegisterTime(new Date());
		patient.setBalance(patient.getDepositMoney());
		hosPatientDao.save(patient);
		
		HosBed hosBed = patient.getHosBed();
		hosBed.setHosBstate("已入住");
		hosBed.setHospitalizedPatient(patient);
		hosBedDao.save(hosBed);
			
		WardRoom room = patient.getHosBed().getWardRoom();
		room.setWNum(room.getWNum()+1);
		wardRoomDao.save(room);
			
	}
	
	/**
	 * 
	* @Title:getPatientByBid
	* @Description:根据床位号查询登记信息
	* @param:@param bedId
	* @param:@return
	* @return:HospitalizedPatient
	* @throws
	* @author:Hamster
	* @Date:2019年8月4日 下午8:52:02
	 */
	public HospitalizedPatient getPatientByBid(String bedId){
		return hosPatientDao.getPatientByBid(bedId);
	}
}
