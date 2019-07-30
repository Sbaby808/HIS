package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.HospitalizedPatient;

/**
 * 入院记录
 * @author dell
 *
 */
public interface IHosPatientDao extends CrudRepository<HospitalizedPatient, String>{
	
	@Query(value="select * from hospitalized_patients",nativeQuery=true)
	public List <HospitalizedPatient> getAllPatientsByPage();
}
