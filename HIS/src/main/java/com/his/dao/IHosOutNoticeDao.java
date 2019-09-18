package com.his.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.HosOutNotice;


/**
 * 
* @ClassName: IHosOutNotice  
* @Description: 出院通知单  
* @author Hamster
* @date 2019年8月3日  上午11:29:53
*
 */
public interface IHosOutNoticeDao extends CrudRepository<HosOutNotice, String>{
	
	/**
	 * 
	* @Title:getAllHosOutNotic
	* @Description:分页查询所有出院通知单
	* @param:@return
	* @return:List<HosOutNotice>
	* @throws
	* @author:Hamster
	* @Date:2019年8月3日 上午11:30:15
	 */
	@Query("from HosOutNotice h where"
			+ " (h.hosDoctorAdvice.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.medicalCard.cardName like ?1"
			+ " or h.hosDoctorAdvice.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.medicalCard.personId like ?1 )"
			+ " and h.hosDoctorAdvice.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.hosBed.wardRoom.ward.department.ksName like ?2 "
			+ " and h.hosDoctorAdvice.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.hosBed.wardRoom.wroomName like ?3 ")
	public List <HosOutNotice> getHosOutNoticeByPage(String cardName,String ksName,String roomName,Pageable page);
	
	@Query("select count(*) from HosOutNotice h where"
			+ " (h.hosDoctorAdvice.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.medicalCard.cardName like ?1"
			+ " or h.hosDoctorAdvice.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.medicalCard.personId like ?1 )"
			+ " and h.hosDoctorAdvice.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.hosBed.wardRoom.ward.department.ksName like ?2 "
			+ " and h.hosDoctorAdvice.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.hosBed.wardRoom.wroomName like ?3 ")
	public Long countNum1(String cardName,String ksName,String roomName);
	
	
	@Query("from HosOutNotice h where"
			+ " h.hosOutTime between ?1 and ?2"
			+ " and (h.hosDoctorAdvice.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.medicalCard.cardName like ?3"
			+ " or h.hosDoctorAdvice.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.medicalCard.personId like ?3 )"
			+ " and h.hosDoctorAdvice.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.hosBed.wardRoom.ward.department.ksName like ?4 "
			+ " and h.hosDoctorAdvice.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.hosBed.wardRoom.wroomName like ?5 ")
	public List <HosOutNotice> getHosOutNoticeByPageandTime(Date start,Date end,String cardName,String ksName,String roomName,Pageable page);
	
	@Query("select count(*) from HosOutNotice h where"
			+ " h.hosOutTime between ?1 and ?2"
			+ " and (h.hosDoctorAdvice.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.medicalCard.cardName like ?3"
			+ " or h.hosDoctorAdvice.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.medicalCard.personId like ?3 )"
			+ " and h.hosDoctorAdvice.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.hosBed.wardRoom.ward.department.ksName like ?4 "
			+ " and h.hosDoctorAdvice.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.hosBed.wardRoom.wroomName like ?5 ")
	public Long countNum2(Date start,Date end,String cardName,String ksName,String roomName);
	
}
