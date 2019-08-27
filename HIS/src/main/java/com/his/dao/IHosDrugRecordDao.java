package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.HosDrugRecord;

/**
 * 
* @ClassName: IHosDrugRecordDao  
* @Description: 住院用药记录 
* @author Hamster
* @date 2019年8月24日  下午4:42:04
*
 */
public interface IHosDrugRecordDao extends CrudRepository<HosDrugRecord, String>{

	@Query("from HosDrugRecord h")
	public List <HosDrugRecord> getAllDrugRecords(Pageable page);

}
