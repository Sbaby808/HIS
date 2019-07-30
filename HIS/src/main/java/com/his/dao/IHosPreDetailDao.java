package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.HosPrescriptionDetail;

/**
 * 住院处方明细
 * @author dell
 *
 */
public interface IHosPreDetailDao extends CrudRepository<HosPrescriptionDetail, String>{

	@Query("from HosPrescriptionDetail h")
	public List <HosPrescriptionDetail> getAllHosPreDetails(Pageable page);
}
