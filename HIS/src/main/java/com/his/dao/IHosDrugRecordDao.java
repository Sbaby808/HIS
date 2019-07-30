package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.HosDrugRecord;

/**
 * 住院用药记录
 * @author dell
 *
 */
public interface IHosDrugRecordDao extends CrudRepository<HosDrugRecord, String>{

	@Query("from HosDrugRecord h")
	public List <HosDrugRecord> getAllDrugRecords(Pageable page);
}
