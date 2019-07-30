package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.HosPatientsApply;


/**
 * 住院患者申请
 * @author dell
 *
 */
public interface IHosApplyDao extends CrudRepository<HosPatientsApply, String>{

	@Query("from HosPatientsApply h")
	public List <HosPatientsApply> getAllHosApplies(Pageable page);
}
