package com.his.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.Dept;

public interface IDeptDao extends CrudRepository<Dept, String>{
	@Query("from Dept d")
	public List<Dept> getDepts();
}
