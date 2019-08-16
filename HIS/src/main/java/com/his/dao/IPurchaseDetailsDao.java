package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.PurchaseDetail;
import com.his.pojo.PurchaseDetailPK;

/**  
* @ClassName: IPurchaseDetailsDao  
* @Description: 药品采购明细 dao
* @author crazy_long
* @date 2019年7月30日  上午10:45:45
*    
*/
public interface IPurchaseDetailsDao extends CrudRepository<PurchaseDetail, PurchaseDetailPK>{

	/**
	* @Title:getForCgid
	* @Description:根据采购id查找对应的明细
	* @param:@param cgid
	* @param:@return
	* @return:List<PurchaseDetail>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月14日 下午3:30:09
	 */
	@Query("from PurchaseDetail p where p.purchase.cgId = ?1")
	public List<PurchaseDetail> getForCgid(String cgid);
	
	/**
	* @Title:getForCgidByPage
	* @Description:根据采购id分页查询对应的明细记录
	* @param:@param cgid
	* @param:@param page
	* @param:@return
	* @return:List<PurchaseDetail>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月15日 下午5:33:41
	 */
	@Query("from PurchaseDetail p where p.purchase.cgId = ?1")
	public List<PurchaseDetail> getForCgidByPage(String cgid,Pageable page);
	
	/**
	* @Title:getForCgidByPageCount
	* @Description:根据采购id分页查询对应的明细记录的条数
	* @param:@param cgid
	* @param:@return
	* @return:List<PurchaseDetail>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月15日 下午5:35:32
	 */
	@Query("select count(*) from PurchaseDetail p where p.purchase.cgId = ?1")
	public int getForCgidByPageCount(String cgid);
}
