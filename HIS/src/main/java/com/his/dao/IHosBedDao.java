package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.HosBed;

public interface IHosBedDao extends CrudRepository<HosBed,String>{
	
	@Query("from HosBed h")
	public List <HosBed> getAllBeds(Pageable page);
}
