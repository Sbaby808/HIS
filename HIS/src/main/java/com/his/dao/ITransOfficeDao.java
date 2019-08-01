package com.his.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.TransOfficeRecord;

public interface ITransOfficeDao extends CrudRepository<TransOfficeRecord, String>{
	
	
}
