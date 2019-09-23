package com.his.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.HosDrugDetail;

/**
 * 
* @ClassName: IHosDrugDetailDao  
* @Description: 住院用药明细 
* @author Hamster
* @date 2019年8月13日  上午9:42:46
*
 */
public interface IHosDrugDetailDao extends CrudRepository<HosDrugDetail, String>{

	@Query("from HosDrugDetail h where "
			+ " h.hosDrugRecord.hosPrescription.hosDiagnosticRecord.medicalRecord.medOutTime is null "
			+ " and( h.hosDrugRecord.hosPrescription.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.medicalCard.cardName like ?1 "
			+ " or h.hosDrugRecord.hosPrescription.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.medicalCard.personId like ?1) "
			+ " and h.hosDrugRecord.hosPrescription.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.hosBed.wardRoom.ward.department.ksName like ?2 "
			+ " and h.hosDrugRecord.hosPrescription.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.hosBed.wardRoom.wroomName like ?3 "
			+ " order by h.hosDrugRecord.hosDrugTime desc ")
	public List <HosDrugDetail> getHosDrugDetailbyPage(String cardName,String ksName,String roomName,Pageable page);
	
	@Query("select count(*) from HosDrugDetail h where "
			+ " h.hosDrugRecord.hosPrescription.hosDiagnosticRecord.medicalRecord.medOutTime is null "
			+ " and( h.hosDrugRecord.hosPrescription.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.medicalCard.cardName like ?1 "
			+ " or h.hosDrugRecord.hosPrescription.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.medicalCard.personId like ?1) "
			+ " and h.hosDrugRecord.hosPrescription.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.hosBed.wardRoom.ward.department.ksName like ?2 "
			+ " and h.hosDrugRecord.hosPrescription.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.hosBed.wardRoom.wroomName like ?3 "
			+ " order by h.hosDrugRecord.hosDrugTime desc ")
	public Long countNum1(String cardName,String ksName,String roomName);
	
	
	@Query("from HosDrugDetail h where "
			+ " h.hosDrugRecord.hosDrugTime between ?1 and ?2"
			+ " and h.hosDrugRecord.hosPrescription.hosDiagnosticRecord.medicalRecord.medOutTime is null "
			+ " and( h.hosDrugRecord.hosPrescription.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.medicalCard.cardName like ?3 "
			+ " or h.hosDrugRecord.hosPrescription.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.medicalCard.personId like ?3) "
			+ " and h.hosDrugRecord.hosPrescription.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.hosBed.wardRoom.ward.department.ksName like ?4 "
			+ " and h.hosDrugRecord.hosPrescription.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.hosBed.wardRoom.wroomName like ?5 "
			+ " order by h.hosDrugRecord.hosDrugTime desc ")
	public List <HosDrugDetail> getHosDrugDetailbyPageandTime(Date start,Date end,String cardName,String ksName,String roomName,Pageable page);
	
	@Query("select count(*) from HosDrugDetail h where "
			+ " h.hosDrugRecord.hosDrugTime between ?1 and ?2"
			+ " and h.hosDrugRecord.hosPrescription.hosDiagnosticRecord.medicalRecord.medOutTime is null "
			+ " and( h.hosDrugRecord.hosPrescription.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.medicalCard.cardName like ?3 "
			+ " or h.hosDrugRecord.hosPrescription.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.medicalCard.personId like ?3) "
			+ " and h.hosDrugRecord.hosPrescription.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.hosBed.wardRoom.ward.department.ksName like ?4 "
			+ " and h.hosDrugRecord.hosPrescription.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.hosBed.wardRoom.wroomName like ?5 "
			+ " order by h.hosDrugRecord.hosDrugTime desc ")
	public Long countNum2(Date start,Date end,String cardName,String ksName,String roomName);
	
	
	
	@Query(value="select count(*) from hos_drug_detail where yp_id=?1",nativeQuery=true)
	public BigDecimal count(String ypId);
	
	/**
	 * 
	* @Title:getHosDrugDetailBypreId
	* @Description:根据处方id查询用药明细
	* @param:@param preId
	* @param:@return
	* @return:List<HosDrugDetail>
	* @throws
	* @author:Hamster
	* @Date:2019年8月21日 上午9:30:39
	 */
	@Query("from HosDrugDetail h where h.hosDrugRecord.hosPrescription.hosPreId=?1")
	public List <HosDrugDetail> getHosDrugDetailBypreId(String preId);
	
	/**
	 * 
	* @Title:getHosDrugDetailbyDiagId
	* @Description:根据诊断记录id查询用药明细
	* @param:@param diagId
	* @param:@return
	* @return:List<HosDrugDetail>
	* @throws
	* @author:Hamster
	* @Date:2019年8月24日 下午4:49:19
	 */
	@Query("from HosDrugDetail h where h.hosDrugRecord.hosPrescription.hosDiagnosticRecord.hosDiagId=?1")
	public List <HosDrugDetail> getHosDrugDetailbyDiagId(String diagId);
}
