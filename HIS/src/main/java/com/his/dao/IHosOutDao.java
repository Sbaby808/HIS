package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.OutHospitaiRecord;

public interface IHosOutDao extends CrudRepository<OutHospitaiRecord, String>{
	
	@Query("from OutHospitaiRecord o")
	public List <OutHospitaiRecord> getAllOutRecords(Pageable page);
}
