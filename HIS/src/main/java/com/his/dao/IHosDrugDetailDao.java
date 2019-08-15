package com.his.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.HosDrugDetail;

/**
 * 
* @ClassName: IHosDrugDetailDao  
* @Description: 住院用药明细 
* @author Hamster
* @date 2019年8月13日  上午9:42:46
*
 */
public interface IHosDrugDetailDao extends CrudRepository<HosDrugDetail, String>{

	@Query("from HosDrugDetail h")
	public List <HosDrugDetail> getHosDrugDetail();
	
	@Query(value="select count(*) from hos_drug_detail where yp_id=?1",nativeQuery=true)
	public BigDecimal count(String ypId);
	
	@Query("from HosDrugDetail h where h.hosDrugRecord.hosPrescription.hosPreId=?1")
	public List <HosDrugDetail> getHosDrugDetailBypreId(String preId);
}
