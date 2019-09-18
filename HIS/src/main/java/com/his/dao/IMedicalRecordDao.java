package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.MedicalRecord;

/**
 * 
* @ClassName: IMedicalRecordDao  
* @Description: 住院病案  
* @author Hamster
* @date 2019年8月4日  下午5:18:16
*
 */
public interface IMedicalRecordDao extends CrudRepository<MedicalRecord, String>{
	
	/**
	 * 
	* @Title:getAllMedicalRecord
	* @Description:无分页查询所有病案
	* @param:@param page
	* @param:@return
	* @return:List<MedicalRecord>
	* @throws
	* @author:Hamster
	* @Date:2019年8月4日 下午5:18:37
	 */
	@Query("from MedicalRecord m")
	public List <MedicalRecord> getAllMedicalRecord();
	
	@Query("from MedicalRecord m where "
			+ " (m.hospitalizedPatient.medicalCard.cardName like ?1 "
			+ " or m.hospitalizedPatient.medicalCard.personId like ?1)"
			+ " and m.medInDept like ?2 ")
	public List <MedicalRecord> getAllMedicalRecordByPage(String cardName,String ksName,Pageable page);
	
	@Query("select count(*) from MedicalRecord m where "
			+ " (m.hospitalizedPatient.medicalCard.cardName like ?1 "
			+ " or m.hospitalizedPatient.medicalCard.personId like ?1)"
			+ " and m.medInDept like ?2 ")
	public Long countNum(String cardName,String ksName);
	
	
	
	/**
	 * 
	* @Title:getClosedMedicalRecord
	* @Description:已封档病案
	* @param:@param cardName
	* @param:@param ksName
	* @param:@param page
	* @param:@return
	* @return:List<MedicalRecord>
	* @throws
	* @author:Hamster
	* @Date:2019年9月6日 下午2:34:10
	 */
	@Query("from MedicalRecord m where "
			+ " (m.hospitalizedPatient.medicalCard.cardName like ?1 "
			+ " or m.hospitalizedPatient.medicalCard.personId like ?1)"
			+ " and m.medInDept like ?2 "
			+ " and m.medOther is not null ")
	public List <MedicalRecord> getClosedMedicalRecord(String cardName,String ksName,Pageable page);
	
	@Query("select count(*) from MedicalRecord m where "
			+ " (m.hospitalizedPatient.medicalCard.cardName like ?1 "
			+ " or m.hospitalizedPatient.medicalCard.personId like ?1)"
			+ " and m.medInDept like ?2 "
			+ " and m.medOther is not null ")
	public Long countNum2(String cardName,String ksName);
	
	/**
	 * 
	* @Title:getOpenMedicalRecord
	* @Description:未封档病案
	* @param:@param cardName
	* @param:@param ksName
	* @param:@param page
	* @param:@return
	* @return:List<MedicalRecord>
	* @throws
	* @author:Hamster
	* @Date:2019年9月6日 下午2:35:23
	 */
	@Query("from MedicalRecord m where "
			+ " (m.hospitalizedPatient.medicalCard.cardName like ?1 "
			+ " or m.hospitalizedPatient.medicalCard.personId like ?1)"
			+ " and m.medInDept like ?2 "
			+ " and m.medOther is null ")
	public List <MedicalRecord> getOpenMedicalRecord(String cardName,String ksName,Pageable page);
	
	@Query("select count(*) from MedicalRecord m where "
			+ " (m.hospitalizedPatient.medicalCard.cardName like ?1 "
			+ " or m.hospitalizedPatient.medicalCard.personId like ?1)"
			+ " and m.medInDept like ?2 "
			+ " and m.medOther is null ")
	public Long countNum1(String cardName,String ksName);
	
	/**
	 * 
	* @Title:getMedicalRecordByhospId
	* @Description:根据住院id查询病案
	* @param:@param hospId
	* @param:@return
	* @return:MedicalRecord
	* @throws
	* @author:Hamster
	* @Date:2019年8月6日 下午8:18:55
	 */
	@Query(value="select * from medical_record m where m.hosp_id=?1",nativeQuery=true)
	public MedicalRecord getMedicalRecordByhospId(String hospId);


}
