package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.HosPayRecord;

/**
 * 
* @ClassName: IHosPayRecordDao  
* @Description: 住院患者缴费记录  
* @author Hamster
* @date 2019年9月18日  下午8:27:19
*
 */
public interface IHosPayRecordDao extends CrudRepository<HosPayRecord, String>{

	@Query("from HosPayRecord h where "
			+ " h.hospitalizedPatient.medicalCard.cardName like ?1 "
			+ " or h.hospitalizedPatient.medicalCard.personId like ?1")
	public List <HosPayRecord> getAllPayRecord(String cardName,Pageable page);
	
	@Query("select count(*) from HosPayRecord h where "
			+ " h.hospitalizedPatient.medicalCard.cardName like ?1 "
			+ " or h.hospitalizedPatient.medicalCard.personId like ?1")
	public Long countNum(String cardName);
	
}
