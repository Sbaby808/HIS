package com.his.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.HospitalNotice;

public interface IHosInNoticeDao extends CrudRepository<HospitalNotice,String>{

	@Query("from HospitalNotice h")
	public List <HospitalNotice> getAllHosInNotice();
}
