package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.HosDrugCost;


/**
 * 
* @ClassName: IHosDrugCostDao  
* @Description: 住院扣费记录  
* @author Hamster
* @date 2019年8月7日  下午7:47:16
*
 */
public interface IHosDrugCostDao extends CrudRepository<HosDrugCost, String> {
	/**
	 * 
	* @Title:getAllDrugCosts
	* @Description:分页查询
	* @param:@param page
	* @param:@return
	* @return:List<HosDrugCost>
	* @throws
	* @author:Hamster
	* @Date:2019年8月7日 下午7:47:33
	 */
	@Query("from HosDrugCost h")
	public List <HosDrugCost> getAllDrugCostsByPage(Pageable page);
	
	/**
	 * 
	* @Title:getAllDrugCosts
	* @Description:无分页查询
	* @param:@return
	* @return:List<HosDrugCost>
	* @throws
	* @author:Hamster
	* @Date:2019年8月7日 下午7:48:05
	 */
	@Query("from HosDrugCost h")
	public List <HosDrugCost> getAllDrugCosts();
	
}
