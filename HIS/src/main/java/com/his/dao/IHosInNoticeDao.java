package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.HospitalNotice;

/**
 * 
* @ClassName: IHosInNoticeDao  
* @Description: 入院通知  
* @author Hamster
* @date 2019年8月15日  上午11:34:49
*
 */

public interface IHosInNoticeDao extends CrudRepository<HospitalNotice,String>{

	@Query("from HospitalNotice h where "
			+ " h.solveScheme.history.outpatientRegistration.medicalCard.cardName like ?1 "
			+ " and h.solveScheme.history.department.dept.deptName like ?2 "
			+ " and h.department.ksName like ?3 ")
	public List <HospitalNotice> getHosInNoticeByPage(String hospName,String sourceText,String departText,Pageable page);
	
}
