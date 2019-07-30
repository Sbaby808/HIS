package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.HosPayRecord;

/**
 * 住院患者缴费记录
 * @author dell
 *
 */
public interface IHosPayRecordDao extends CrudRepository<HosPayRecord, String>{

	@Query("from HosPayRecord h")
	public List <HosPayRecord> getAllPayRecord(Pageable page);
}
