package com.his.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.HosEmp;

public interface IHosEmpDao extends CrudRepository<HosEmp,String>{

	
}
