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
			+ " (o.hospitalizedPatient.medicalCard.cardName like ?1 "
			+ " or o.hospitalizedPatient.medicalCard.personId like ?1)"
			+ " and o.outDepart like ?2")
	public List <OutHospitaiRecord> getOutRecord(String cardName,String ksName,Pageable page);

	/**
	 * 
	* @Title:outRecordForCharts
	* @Description:出院记录图表统计
	* @param:@return
	* @return:List
	* @throws
	* @author:Hamster
	* @Date:2019年9月1日 下午8:03:49
	 */
	@Query(value="WITH T1 AS (SELECT ADD_MONTHS(DATE '2018-12-01', LEVEL) AS COL1 FROM DUAL CONNECT BY LEVEL <= 12) "
			+ " SELECT TO_CHAR(t.COL1, 'YYYY-MM'), NVL(count(o.out_rec_time),0) FROM T1 t  "
			+ " LEFT JOIN out_hospitai_record o ON TO_CHAR(t.COL1, 'YYYY-MM') = TO_CHAR(o.out_rec_time, 'YYYY-MM') "
			+ " GROUP BY TO_CHAR(t.COL1, 'YYYY-MM') order by 1",nativeQuery=true)
	public List outRecordForCharts();


}
