package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.HosDrugCost;


/**
 * 住院药品扣费记录
 * @author dell
 *
 */
public interface IHosDrugCostDao extends CrudRepository<HosDrugCost, String> {

	@Query("from HosDrugCost h")
	public List <HosDrugCost> getAllDrugCosts(Pageable page);
}
