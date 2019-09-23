package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.PurchaseCheck;

/**  
* @ClassName: IPurchaseCheckDao  
* @Description: 采购验收表  
* @author crazy_long
* @date 2019年7月30日  上午11:11:32
*    
*/
public interface IPurchaseCheckDao extends CrudRepository<PurchaseCheck, String>{
	
	/**
	* @Title:getAllPurchaseCheckByState
	* @Description:查看某一个状态的采购验收
	* @param:@param state
	* @param:@param page
	* @param:@return
	* @return:List<PurchaseCheck>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月21日 上午12:59:48
	 */
	@Query("from PurchaseCheck p where p.state= ?1 order by p.purChaTime asc")
	public List<PurchaseCheck> getAllPurchaseCheckByState(String state,Pageable page);
	
	/**
	* @Title:getAllPurchaseCheckByState
	* @Description:查看某一个状态的采购验收的数量
	* @param:@param state
	* @param:@return
	* @return:int
	* @throws
	* @author:crazy_long
	* @Date:2019年9月21日 上午1:00:22
	 */
	@Query("select count(*) from PurchaseCheck p where p.state= ?1 order by p.purChaTime asc")
	public int getAllPurchaseCheckByStateCount(String state);
	
	/**
	* @Title:getOneCheckAndDetial
	* @Description:获取个未入库的采购验收
	* @param:@return
	* @return:PurchaseCheck
	* @throws
	* @author:crazy_long
	* @Date:2019年8月17日 上午9:26:28
	 */
	@Query("from PurchaseCheck p where p.state='否'  order by p.purChaTime asc")
	public List<PurchaseCheck> getAllForNoPut();
	
}
