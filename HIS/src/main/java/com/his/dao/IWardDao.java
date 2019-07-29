package com.his.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.Ward;

public interface IWardDao extends CrudRepository<Ward, String>{
	
	@Query("from Ward w")
	public List <Ward> getAllWards(); 
}
