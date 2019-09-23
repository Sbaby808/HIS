package com.his.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.HosDiagnosticRecord;

/**
 * 
* @ClassName: IHosDiagRecordDao  
* @Description: 住院诊断记录 
* @author Hamster
* @date 2019年8月5日  上午11:31:58
*
 */
public interface IHosDiagRecordDao extends CrudRepository<HosDiagnosticRecord, String>{

	/**
	 * 
	* @Title:getDiagRecord
	* @Description:无分页查询所有住院诊断记录
	* @param:@return
	* @return:List<HosDiagnosticRecord>
	* @throws
	* @author:Hamster
	* @Date:2019年8月5日 上午11:32:14
	 */
	@Query("from HosDiagnosticRecord h where h.medicalRecord.medOutTime is null")
	public List <HosDiagnosticRecord> getDiagRecord();
	
	/**
	 * 
	* @Title:getDiagRecordByPage
	* @Description:分页查询所有住院诊断记录
	* @param:@param page
	* @param:@return
	* @return:List<HosDiagnosticRecord>
	* @throws
	* @author:Hamster
	* @Date:2019年8月5日 上午11:32:33
	 */
	@Query("from HosDiagnosticRecord h where "
			+ " h.medicalRecord.medOutTime is null "
			+ " and (h.medicalRecord.hospitalizedPatient.medicalCard.cardName like ?1"
			+ " or h.medicalRecord.hospitalizedPatient.medicalCard.personId like ?1) "
			+ " and h.medicalRecord.hospitalizedPatient.hosBed.wardRoom.ward.department.ksName like ?2 "
			+ " and h.medicalRecord.hospitalizedPatient.hosBed.wardRoom.wroomName like ?3 "
			+ " order by h.hosDiagTime desc ")
	public List <HosDiagnosticRecord> getDiagRecordByPage(String cardName,String ksName,String roomName,Pageable page);
	
	@Query("select count(*) from HosDiagnosticRecord h where "
			+ " h.medicalRecord.medOutTime is null "
			+ " and (h.medicalRecord.hospitalizedPatient.medicalCard.cardName like ?1"
			+ " or h.medicalRecord.hospitalizedPatient.medicalCard.personId like ?1) "
			+ " and h.medicalRecord.hospitalizedPatient.hosBed.wardRoom.ward.department.ksName like ?2 "
			+ " and h.medicalRecord.hospitalizedPatient.hosBed.wardRoom.wroomName like ?3 "
			+ " order by h.hosDiagTime desc ")
	public Long countNum1(String cardName,String ksName,String roomName);
	
	
	@Query("from HosDiagnosticRecord h where "
			+ " h.medicalRecord.medOutTime is null "
			+ " and h.hosDiagTime between ?1 and ?2 "
			+ " and (h.medicalRecord.hospitalizedPatient.medicalCard.cardName like ?3"
			+ " or h.medicalRecord.hospitalizedPatient.medicalCard.personId like ?3) "
			+ " and h.medicalRecord.hospitalizedPatient.hosBed.wardRoom.ward.department.ksName like ?4 "
			+ " and h.medicalRecord.hospitalizedPatient.hosBed.wardRoom.wroomName like ?5 "
			+ " order by h.hosDiagTime desc ")
	public List <HosDiagnosticRecord> getDiagRecordByPageandTime(Date start,Date end,String cardName,String ksName,String roomName,Pageable page);
	
	@Query("select count(*) from HosDiagnosticRecord h where "
			+ " h.medicalRecord.medOutTime is null "
			+ " and h.hosDiagTime between ?1 and ?2 "
			+ " and (h.medicalRecord.hospitalizedPatient.medicalCard.cardName like ?3"
			+ " or h.medicalRecord.hospitalizedPatient.medicalCard.personId like ?3) "
			+ " and h.medicalRecord.hospitalizedPatient.hosBed.wardRoom.ward.department.ksName like ?4 "
			+ " and h.medicalRecord.hospitalizedPatient.hosBed.wardRoom.wroomName like ?5 "
			+ " order by h.hosDiagTime desc ")
	public Long countNum2(Date start,Date end,String cardName,String ksName,String roomName);
	
	
	
	@Query("from HosDiagnosticRecord h where h.medicalRecord.medRid = ?1")
	public List <HosDiagnosticRecord> getDiagRecordbyMid(String medRid);
}
