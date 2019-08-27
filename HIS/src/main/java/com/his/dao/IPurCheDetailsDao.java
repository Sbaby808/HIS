package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.PurCheDetail;
import com.his.pojo.PurCheDetailPK;

/**  
* @ClassName: IPurCheDetailsDao  
* @Description: 采购验收明细dao
* @author crazy_long
* @date 2019年7月30日  上午11:09:44
*    
*/
public interface IPurCheDetailsDao extends CrudRepository<PurCheDetail, PurCheDetailPK>{
	
	/**
	* @Title:getOneCheckAndDetial
	* @Description:分页获取一个未入库的采购验收明细
	* @param:@return
	* @return:PurchaseCheck
	* @throws
	* @author:crazy_long
	* @Date:2019年8月17日 上午9:26:28
	 */
	@Query("from PurCheDetail p where p.purCheDetdeal = '入库' and p.purchaseCheck.purChaId = ?1")
	public List<PurCheDetail> getOneCheckAndDetial(String purChaId,Pageable page);
	
	/**
	* @Title:getOneCheckAndDetial
	* @Description:分页获取一个未入库的采购验收及其明细的条数
	* @param:@return
	* @return:PurchaseCheck
	* @throws
	* @author:crazy_long
	* @Date:2019年8月17日 上午9:26:28
	 */
	@Query("select count(*) from PurCheDetail p where p.purCheDetdeal = '入库' and p.purchaseCheck.purChaId = ?1")
	public int getOneCheckNoputCount(String purChaId);

}
