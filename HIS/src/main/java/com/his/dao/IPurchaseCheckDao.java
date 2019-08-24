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
	* @Title:getOneCheckAndDetial
	* @Description:获取个未入库的采购验收
	* @param:@return
	* @return:PurchaseCheck
	* @throws
	* @author:crazy_long
	* @Date:2019年8月17日 上午9:26:28
	 */
	@Query("from PurchaseCheck p where p.state='否' order by p.purChaTime asc")
	public List<PurchaseCheck> getAllForNoPut();
	
}
