package com.his.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.HosPrescription;

/**
 * 
* @ClassName: IHosPrescriptionDao  
* @Description: 住院处方
* @author Hamster
* @date 2019年8月5日  下午7:19:20
*
 */
public interface IHosPrescriptionDao extends CrudRepository<HosPrescription, String>{

	/**
	 * 
	* @Title:getAllHosPrescription
	* @Description:无分页查询所有处方
	* @param:@return
	* @return:List<HosPrescription>
	* @throws
	* @author:Hamster
	* @Date:2019年8月5日 下午7:20:50
	 */
	@Query("from HosPrescription h where h.hosDiagnosticRecord.medicalRecord.medOutTime is null")
	public List <HosPrescription> getAllHosPrescription();
	
	/**
	 * 
	* @Title:getHosPrescriptionByPage
	* @Description:分页查询所有处方
	* @param:@param page
	* @param:@return
	* @return:List<HosPrescription>
	* @throws
	* @author:Hamster
	* @Date:2019年8月5日 下午7:21:05
	 */
	@Query("from HosPrescription h where "
			+ " (h.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.medicalCard.cardName like ?1 "
			+ " or h.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.medicalCard.personId like ?1) "
			+ " and h.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.hosBed.wardRoom.ward.department.ksName like ?2 "
			+ " and h.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.hosBed.hosBid!=null")
	public List <HosPrescription> getHosPrescriptionByPage(String cardName,String ksName,Pageable page);
	
	@Query("select count(*) from HosPrescription h where "
			+ " (h.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.medicalCard.cardName like ?1 "
			+ " or h.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.medicalCard.personId like ?1) "
			+ " and h.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.hosBed.wardRoom.ward.department.ksName like ?2 "
			+ " and h.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.hosBed.hosBid!=null")
	public Long countNum1(String cardName,String ksName);
	
	
	@Query("from HosPrescription h where "
			+ " h.hosPreTime between ?1 and ?2 "
			+ " and (h.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.medicalCard.cardName like ?3 "
			+ " or h.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.medicalCard.personId like ?3) "
			+ " and h.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.hosBed.wardRoom.ward.department.ksName like ?4 "
			+ " and h.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.hosBed.hosBid!=null")
	public List <HosPrescription> getHosPrescriptionByPageandTime(Date start,Date end,String cardName,String ksName,Pageable page);
	
	@Query("select count(*) from HosPrescription h where "
			+ " h.hosPreTime between ?1 and ?2 "
			+ " and (h.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.medicalCard.cardName like ?3 "
			+ " or h.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.medicalCard.personId like ?3) "
			+ " and h.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.hosBed.wardRoom.ward.department.ksName like ?4 "
			+ " and h.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.hosBed.hosBid!=null")
	public Long countNum2(Date start,Date end,String cardName,String ksName);
	
	
	
	/**
	 * 
	* @Title:getHosPresByDiagId
	* @Description:根据诊断记录id查询处方
	* @param:@param diagId
	* @param:@return
	* @return:HosPrescription
	* @throws
	* @author:Hamster
	* @Date:2019年8月7日 下午9:37:32
	 */
	@Query(value="select * from hos_prescription h where h.hos_diag_id=?1",nativeQuery=true)
	public HosPrescription getHosPresByDiagId(String diagId);
}
