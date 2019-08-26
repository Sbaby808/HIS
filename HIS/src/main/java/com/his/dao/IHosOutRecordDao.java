package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.OutHospitaiRecord;

/**
 * 
* @ClassName: IHosOutRecordDao  
* @Description: 住院出院记录 
* @author Hamster
* @date 2019年8月20日  上午10:20:17
*
 */
public interface IHosOutRecordDao extends CrudRepository<OutHospitaiRecord, String>{

	@Query("from OutHospitaiRecord o where "
			+ " o.hospitalizedPatient.medicalCard.cardName like ?1 "
			+ " or o.hospitalizedPatient.medicalCard.personId like ?1")
	public List <OutHospitaiRecord> getOutRecord(String cardName,Pageable page);
}
