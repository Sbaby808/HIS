package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.HosOtherCost;

/**
 * 
* @ClassName: IHosOtherCostDao  
* @Description: 住院其他扣费记录 
* @author Hamster
* @date 2019年8月20日  上午9:38:14
*
 */
public interface IHosOtherCostDao extends CrudRepository<HosOtherCost, String>{
	
	@Query("from HosOtherCost h where "
			+ " (h.medicalRecord.hospitalizedPatient.medicalCard.cardName like ?1 "
			+ " or h.medicalRecord.hospitalizedPatient.medicalCard.personId like ?1) "
			+ " and h.medicalRecord.hospitalizedPatient.hosBed.hosBid !=null ")
	public List <HosOtherCost> getAllOtherCostByPage(String cardName,Pageable page);
	
	@Query("select count(*) from HosOtherCost h where"
			+ " h.medicalRecord.hospitalizedPatient.hosBed.hosBid !=null ")
	public Long countInOtherCost();
	
	@Query("from HosOtherCost h")
	public List <HosOtherCost> getHosOtherCost();
	
	/**
	 * 
	* @Title:getAllOtherCostbyRid
	* @Description:根据病案号查询其他扣费记录
	* @param:@param recordId
	* @param:@return
	* @return:List<HosOtherCost>
	* @throws
	* @author:Hamster
	* @Date:2019年8月19日 下午6:36:50
	 */
	@Query("from HosOtherCost h where h.medicalRecord.medRid=?1")
	public List <HosOtherCost> getAllOtherCostbyRid(String recordId);
	
}
