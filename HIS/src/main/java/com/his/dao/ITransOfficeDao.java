package com.his.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.TransOfficeRecord;

/**
 * 
* @ClassName: ITransOfficeDao  
* @Description: 住院转科转床记录
* @author Hamster
* @date 2019年9月5日  下午5:09:46
*
 */
public interface ITransOfficeDao extends CrudRepository<TransOfficeRecord, String>{
	
	@Query("from TransOfficeRecord t where "
			+ " t.outOfficeTime between ?1 and ?2"
			+ " and (t.hospitalizedPatient.medicalCard.cardName like ?3"
			+ " or t.hospitalizedPatient.medicalCard.personId like ?3)"
			+ " order by t.outOfficeTime desc ")
	public List <TransOfficeRecord> getTransRecordByPageandTime(Date start,Date end,String cardName,Pageable page);
	
	@Query("from TransOfficeRecord t where "
			+ " (t.hospitalizedPatient.medicalCard.cardName like ?1"
			+ " or t.hospitalizedPatient.medicalCard.personId like ?1) "
			+ " order by t.outOfficeTime desc ")
	public List <TransOfficeRecord> getTransRecordByPage(String cardName,Pageable page);
}
