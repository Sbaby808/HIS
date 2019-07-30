package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.HosDoctorAdvice;

/**
 * 住院医嘱
 * @author dell
 *
 */
public interface IHosAdviceDao extends CrudRepository<HosDoctorAdvice, String>{

	@Query("from HosDoctorAdvice h")
	public List <HosDoctorAdvice> getAllHosAdvices(Pageable page);
}
