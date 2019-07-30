package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.HosOtherCost;

/**
 * 住院其他扣费记录
 * @author dell
 *
 */
public interface IHosOtherCostDao extends CrudRepository<HosOtherCost, String>{
	
	@Query("from HosOtherCost h")
	public List <HosOtherCost> getAllOtherCosts(Pageable page);
}
