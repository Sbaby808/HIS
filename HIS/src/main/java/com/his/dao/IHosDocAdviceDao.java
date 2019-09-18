package com.his.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.HosDoctorAdvice;

/**
 * 
* @ClassName: IHosDocAdviceDao  
* @Description: 住院医嘱  
* @author Hamster
* @date 2019年8月7日  上午8:56:59
*
 */
public interface IHosDocAdviceDao extends CrudRepository<HosDoctorAdvice, String>{

	/**
	 * 
	* @Title:getHosDocAdvice
	* @Description:查询所有住院医嘱
	* @param:@return
	* @return:List<HosDoctorAdvice>
	* @throws
	* @author:Hamster
	* @Date:2019年8月7日 上午8:58:18
	 */
	@Query("from HosDoctorAdvice h where h.hosDiagnosticRecord.medicalRecord.medOutTime is null")
	public List <HosDoctorAdvice> getHosDocAdvice();
	
	
	/**
	 *
	* @Title:getHosDocAdviceByDid
	* @Description:根据诊断记录id查询医嘱
	* @param:@param diagId
	* @param:@return
	* @return:HosDoctorAdvice
	* @throws
	* @author:Hamster
	* @Date:2019年8月7日 上午9:32:25
	 */
	@Query(value="select * from hos_doctor_advice h where h.hos_diag_id=?1",nativeQuery=true)
	public HosDoctorAdvice getHosDocAdviceByDid(String diagId);
	
	/**
	 * 
	* @Title:getDocAdviceByDocId
	* @Description:根据医嘱id查询医嘱
	* @param:@param docId
	* @param:@return
	* @return:HosDoctorAdvice
	* @throws
	* @author:Hamster
	* @Date:2019年8月7日 上午11:45:23
	 */
	@Query("from HosDoctorAdvice h where h.hosDcoId=?1")
	public HosDoctorAdvice getDocAdviceByDocId(String docId);
	
	/**
	 * 
	* @Title:getHosDocAdviceBypage
	* @Description:分页查询医嘱
	* @param:@param page
	* @param:@return
	* @return:List<HosDoctorAdvice>
	* @throws
	* @author:Hamster
	* @Date:2019年8月13日 下午9:08:28
	 */
	@Query("from HosDoctorAdvice h where "
			+ " h.hosDiagnosticRecord.medicalRecord.medOutTime is null "
			+ " and (h.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.medicalCard.cardName like ?1 "
			+ " or h.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.medicalCard.personId like ?1) "
			+ " and h.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.hosBed.wardRoom.ward.department.ksName like ?2")
	public List <HosDoctorAdvice> getHosDocAdviceBypage(String cardName,String ksName,Pageable page);
	
	@Query("select count(*) from HosDoctorAdvice h where "
			+ " h.hosDiagnosticRecord.medicalRecord.medOutTime is null "
			+ " and (h.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.medicalCard.cardName like ?1 "
			+ " or h.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.medicalCard.personId like ?1) "
			+ " and h.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.hosBed.wardRoom.ward.department.ksName like ?2")
	public Long countNum1(String cardName,String ksName);
	
	
	@Query("from HosDoctorAdvice h where "
			+ " h.hosStartTime between ?1 and ?2"
			+ " and h.hosDiagnosticRecord.medicalRecord.medOutTime is null "
			+ " and (h.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.medicalCard.cardName like ?3 "
			+ " or h.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.medicalCard.personId like ?3) "
			+ " and h.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.hosBed.wardRoom.ward.department.ksName like ?4")
	public List <HosDoctorAdvice> getHosDocAdviceBypageandTime(Date start,Date end,String cardName,String ksName,Pageable page);

	@Query("select count(*) from HosDoctorAdvice h where "
			+ " h.hosStartTime between ?1 and ?2"
			+ " and h.hosDiagnosticRecord.medicalRecord.medOutTime is null "
			+ " and (h.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.medicalCard.cardName like ?3 "
			+ " or h.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.medicalCard.personId like ?3) "
			+ " and h.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.hosBed.wardRoom.ward.department.ksName like ?4")
	public Long countNum2(Date start,Date end,String cardName,String ksName);


}
