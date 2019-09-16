package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.ObservationBed;

public interface IObservationBedDAO extends CrudRepository<ObservationBed, String>{
	@Query(value = "select * from observation_bed where remain_id is null", nativeQuery = true)
	public List<ObservationBed> findnullbed();
	
	
}
