package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.HosPrescription;

/**
 * 住院处方
 * @author dell
 *
 */
public interface IHosPrescriptionDao extends CrudRepository<HosPrescription, String>{

	@Query("from HosPrescription h")
	public List <HosPrescription> getAllHosPrescription(Pageable page);
}
