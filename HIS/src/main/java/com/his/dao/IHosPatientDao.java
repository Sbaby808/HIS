package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.HospitalizedPatient;

/**
 * 入院记录
 * @author dell
 *
 */
public interface IHosPatientDao extends CrudRepository<HospitalizedPatient, String>{
	
	/*@Query(value="select m.card_name,b.hos_bname,r.wroom_name,w.ward_name,d.ks_name, "
			+ " h.register_time,h.hosp_state,h.hos_source from hospitalized_patients h, "
			+ " hos_beds b ,medical_card m,ward_room r,ward w,department d "
			+ " where h.hos_bid = b.hos_bid and h.card_id = m.card_id and "
			+ " b.wroom_id=r.wroom_id and r.ward_id=w.ward_id and w.ks_id=d.ks_id ",nativeQuery=true)*/
	@Query("from HospitalizedPatient h")
	public List<HospitalizedPatient> getAllPatientsByPage();
	
	@Query(value="select * from hospitalized_patients h where h.wroom_id=?1",nativeQuery=true)
	public List <HospitalizedPatient> getPatientsByWroomId(String id);
}
