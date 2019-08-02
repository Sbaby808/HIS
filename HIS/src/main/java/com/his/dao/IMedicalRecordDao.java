package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.MedicalRecord;

/**
 * 住院病案
 * @author dell
 *
 */
public interface IMedicalRecordDao extends CrudRepository<MedicalRecord, String>{
	
	@Query("from MedicalRecord m")
	public List <MedicalRecord> getAllMedicalRecord(Pageable page);
	
	
}
