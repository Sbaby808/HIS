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
