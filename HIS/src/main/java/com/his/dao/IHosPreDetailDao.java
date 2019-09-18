package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.HosPrescriptionDetail;

/**
 * 
* @ClassName: IHosPreDetailDao  
* @Description: 住院处方明细  
* @author Hamster
* @date 2019年8月6日  上午10:48:01
*
 */
public interface IHosPreDetailDao extends CrudRepository<HosPrescriptionDetail, String>{

	/**
	 * 
	* @Title:getHosPreDetailsByPage
	* @Description:分页查询
	* @param:@param page
	* @param:@return
	* @return:List<HosPrescriptionDetail>
	* @throws
	* @author:Hamster
	* @Date:2019年8月6日 上午10:49:01
	 */
	@Query("from HosPrescriptionDetail h where "
			+ " (h.hosPrescription.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.medicalCard.cardName like ?1"
			+ " or h.hosPrescription.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.medicalCard.personId like ?1)"
			+ " and h.hosPrescription.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.hosBed.hosBid !=null ")
	public List <HosPrescriptionDetail> getHosPreDetailsByPage(String cardName,Pageable page);
	
	@Query("select count(*) from HosPrescriptionDetail h where "
			+ " (h.hosPrescription.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.medicalCard.cardName like ?1"
			+ " or h.hosPrescription.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.medicalCard.personId like ?1)"
			+ " and h.hosPrescription.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.hosBed.hosBid !=null ")
	public Long countNum(String cardName);
	
	
	
	/**
	 * 
	* @Title:getHosPreDetail
	* @Description:无分页查询
	* @param:@return
	* @return:List<HosPrescriptionDetail>
	* @throws
	* @author:Hamster
	* @Date:2019年8月6日 上午10:49:13
	 */
	@Query("from HosPrescriptionDetail h")
	public List <HosPrescriptionDetail> getHosPreDetail();
	
	/**
	 * 
	* @Title:getHosPreDetailByPid
	* @Description:根据处方id获取处方明细
	* @param:@param pid
	* @param:@return
	* @return:List<HosPrescriptionDetail>
	* @throws
	* @author:Hamster
	* @Date:2019年8月6日 上午11:29:39
	 */
	@Query(value="select * from hos_prescription_detail h where h.hos_pre_id=?1",nativeQuery=true)
	public List <HosPrescriptionDetail> getHosPreDetailByPid(String pid);
}
