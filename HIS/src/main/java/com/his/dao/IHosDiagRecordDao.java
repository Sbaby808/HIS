package com.his.dao;

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
			+ " and h.medicalRecord.hospitalizedPatient.hosBed.wardRoom.ward.department.ksName like ?2 ")
	public List <HosDiagnosticRecord> getDiagRecordByPage(String cardName,String ksName,Pageable page);
	
	@Query("select count(*) from HosDiagnosticRecord h where h.medicalRecord.medOutTime is null")
	public Long countInDiag();
	
	@Query("from HosDiagnosticRecord h where h.medicalRecord.medRid = ?1")
	public List <HosDiagnosticRecord> getDiagRecordbyMid(String medRid);
}
