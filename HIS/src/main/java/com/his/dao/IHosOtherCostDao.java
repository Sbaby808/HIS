package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.HosOtherCost;

/**
 * 住院其他扣费记录
 * @author dell
 *
 */
public interface IHosOtherCostDao extends CrudRepository<HosOtherCost, String>{
	
	@Query("from HosOtherCost h")
	public List <HosOtherCost> getAllOtherCostByPage(Pageable page);
	
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
