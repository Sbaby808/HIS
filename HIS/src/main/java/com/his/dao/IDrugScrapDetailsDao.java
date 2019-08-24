package com.his.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.DrugScrapDetail;
import com.his.pojo.DrugScrapDetailPK;

/**  
* @ClassName: IDrugScrapDetailsDao  
* @Description: 药品报废明细dao
* @author crazy_long
* @date 2019年7月30日  上午10:38:40
*    
*/
public interface IDrugScrapDetailsDao extends CrudRepository<DrugScrapDetail, DrugScrapDetailPK>{
	
	/**
	* @Title:queryScrapDrugByPage
	* @Description:条件搜索过期的药品
	* @param:@param ygxh
	* @param:@param type
	* @param:@param firstDate
	* @param:@param lastDate
	* @param:@param minMoney
	* @param:@param maxMoney
	* @param:@param page
	* @param:@return
	* @return:List<DrugScrapDetail>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月23日 上午10:25:18
	 */
	@Query(value="select dsd.* from drug_scrap_details dsd left outer join drug_scrap ds on dsd.bf_id = ds.bf_id " + 
			"left outer join drug_warehouse dw on dw.pckc_id = dsd.pckc_id left outer join drug_information di on dw.yp_id = di.yp_id " + 
			"where ds.ygxh like ?1 " + 
			"and ds.bf_type like ?2 " + 
			"and ds.bf_date > ?3 " + 
			"and ds.bf_date < ?4 " + 
			"and ds.lost_money > ?5 " + 
			"and ds.lost_money < ?6 ",nativeQuery = true)
	public List<DrugScrapDetail> queryScrapDrugByPage(String ygxh,String type,Date firstDate,Date lastDate,BigDecimal minMoney,BigDecimal maxMoney,Pageable page);

	/**
	* @Title:queryScrapDrugByPage
	* @Description:条件搜索过期的药品的条数
	* @param:@param ygxh
	* @param:@param type
	* @param:@param firstDate
	* @param:@param lastDate
	* @param:@param minMoney
	* @param:@param maxMoney
	* @param:@param page
	* @param:@return
	* @return:List<DrugScrapDetail>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月23日 上午10:25:18
	 */
	@Query(value="select count(*) from drug_scrap_details dsd left outer join drug_scrap ds on dsd.bf_id = ds.bf_id " + 
			"left outer join drug_warehouse dw on dw.pckc_id = dsd.pckc_id left outer join drug_information di on dw.yp_id = di.yp_id " + 
			"where ds.ygxh like ?1 " + 
			"and ds.bf_type like ?2 " + 
			"and ds.bf_date > ?3 " + 
			"and ds.bf_date < ?4 " + 
			"and ds.lost_money > ?5 " + 
			"and ds.lost_money < ?6 ",nativeQuery = true)
	public int queryScrapDrugByPageCount(String ygxh,String type,Date firstDate,Date lastDate,BigDecimal minMoney,BigDecimal maxMoney);
	
}
