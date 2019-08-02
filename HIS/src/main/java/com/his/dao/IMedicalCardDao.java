package com.his.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.MedicalCard;

public interface IMedicalCardDao extends CrudRepository<MedicalCard, String>{

	@Query("from MedicalCard m where m.cardId=?1")
	public MedicalCard getCardByCid(String cardId);
	
	@Query("select count(*) from MedicalCard m where m.personId = ?1")
	public Integer checkCardByPersonId(String person_id);
}
