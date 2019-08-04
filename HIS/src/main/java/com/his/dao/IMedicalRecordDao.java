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
	
	@Query("from MedicalRecord m")
	public List <MedicalRecord> getAllMedicalRecordByPage(Pageable page);
	
	
}
