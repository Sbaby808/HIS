package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.Ward;

/**
 * 住院病区
 * @author dell
 *
 */
public interface IWardDao extends CrudRepository<Ward, String>{
	
	@Query("from Ward w")
	public List <Ward> getAllWardByPage(Pageable page); 
	
	@Query("from Ward w")
	public List <Ward> getAllWard();
	
	@Query(value="select * from ward w where w.ks_id=?1",nativeQuery=true)
	public List <Ward> getWardByDid(String ks_id);
}
