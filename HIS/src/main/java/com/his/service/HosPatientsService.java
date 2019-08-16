package com.his.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.his.dao.IHosEmpDao;
import com.his.dao.IHosOutDao;
import com.his.dao.IHosPatientDao;
import com.his.dao.IMedicalRecordDao;
import com.his.dao.IWardRoomDao;
import com.his.pojo.HosBed;
import com.his.pojo.HosEmp;
import com.his.pojo.HosEmpPK;
import com.his.pojo.HospitalizedPatient;
import com.his.pojo.MedicalRecord;
import com.his.pojo.OutHospitaiRecord;
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
	@Autowired
	private IHosOutDao hosOutDao;
	@Autowired
	private IHosEmpDao hosEmpDao;
	
	/**
	 * 
	* @Title:getHosPatientsByPage
	* @Description:分页查询住院登记
	* @param:@param curpage
	* @param:@param pagesize
	* @param:@return
	* @return:Map
	* @throws
	* @author:Hamster
	* @Date:2019年8月9日 下午6:32:44
	 */
	public Map getHosPatientsByPage(String hospName,String ksName,String wardName,String roomName,int curpage,int pagesize){
		List <HospitalizedPatient> patients = hosPatientDao.getAllPatientsByPage(hospName,ksName,wardName,roomName,PageRequest.of(curpage-1,pagesize));
		long total = hosPatientDao.count();
		Map map = new HashMap<>();
		map.put("list", patients);
		map.put("total", total);
		return map;
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
		patient.setHosBid(patient.getHosBed().getHosBid());
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
		
		List <HosEmp> emps = patient.getHosEmps();
		for(int i=0;i<emps.size();i++){
			HosEmpPK pk = new HosEmpPK();
			pk.setHospId(patient.getHospId());
			pk.setYgxh(emps.get(i).getEmpInformation().getYgxh());
			emps.get(i).setId(pk);
			hosEmpDao.save(emps.get(i));
		}
			
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
	
	/**
	 * 
	* @Title:outHosPatient
	* @Description:出院记录
	* @param:@param hospId
	* @param:@throws ParseException
	* @return:void
	* @throws
	* @author:Hamster
	* @Date:2019年8月12日 下午10:28:48
	 */
	public void outHosPatient(String hospId) throws ParseException{
		HospitalizedPatient patient = hosPatientDao.findById(hospId).get();
		
		OutHospitaiRecord outRecord = new OutHospitaiRecord();
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = dateFormat.format(new Date());
		outRecord.setOutRecTime(dateFormat.parse(time));
		outRecord.setHospitalizedPatient(patient);
		hosOutDao.save(outRecord);
		
		MedicalRecord record = patient.getMedicalRecord();
		record.setMedOutDept(patient.getHosBed().getWardRoom().getWard().getDepartment().getKsName());
		record.setMedOutTime(dateFormat.parse(time));
		medicalRecordDao.save(record);
		
		HosBed bed = patient.getHosBed();
		bed.setHosBstate(null);
		bed.setHospitalizedPatient(null);
		hosBedDao.save(bed);
		
		WardRoom room = patient.getHosBed().getWardRoom();
		room.setWNum(room.getWNum()-1);
		wardRoomDao.save(room);
			
		patient.setHosBid(null);
		patient.setOutRid(outRecord.getOutRid());
		patient.setHosBed(null);
		patient.setOutHospitaiRecord(outRecord);
		patient.setHospState("已出院");
		hosPatientDao.save(patient);
		
		
	}
}
