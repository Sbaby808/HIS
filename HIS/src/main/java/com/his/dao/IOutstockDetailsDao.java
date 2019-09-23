package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.OutstockDetail;
import com.his.pojo.OutstockDetailPK;

/**  
* @ClassName: IOutstockDetailsDao  
* @Description: 药库出库明细dao 
* @author crazy_long
* @date 2019年7月30日  上午10:48:25
*    
*/
public interface IOutstockDetailsDao extends CrudRepository<OutstockDetail, OutstockDetailPK>{
	
	/**
	* @Title:getOutstockDetailByReqId
	* @Description:分页查找所有出库明细
	* @param:@param reqId
	* @param:@return
	* @return:List<OutstockDetail>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月22日 下午4:44:12
	 */
	@Query("from OutstockDetail o where o.outstock.req_id = ?1 ")
	public List<OutstockDetail> getOutstockDetailForReqIdByPage(String reqId,Pageable page);
	
	/**
	* @Title:getOutstockDetailForReqIdByPage
	* @Description:TODO
	* @param:@param reqId
	* @param:@param page
	* @param:@return
	* @return:List<OutstockDetail>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月22日 下午4:45:48
	 */
	@Query("select count(*) from OutstockDetail o where o.outstock.req_id = ?1 ")
	public int getOutstockDetailForReqIdCount(String reqId);
	
	/**
	* @Title:getAllDetailByPage
	* @Description:查找出库单对应的明细
	* @param:@param ckId
	* @param:@param page
	* @param:@return
	* @return:List<OutstockDetail>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月21日 下午1:07:31
	 */
	@Query(value="select * from outstock_details o where o.ck_id = ?1 ",nativeQuery = true)
	public List<OutstockDetail> getAllDetailByPage(String ckId,Pageable page);
	
	/**
	* @Title:getAllDetailByPage
	* @Description:查找出库单对应的明细的数量
	* @param:@param ckId
	* @param:@param page
	* @param:@return
	* @return:List<OutstockDetail>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月21日 下午1:08:19
	 */
	@Query(value="select count(*) from outstock_details o where o.ck_id = ?1 ",nativeQuery = true)
	public int getAllDetailCount(String ckId);
	
	/**
	* @Title:getOutstockDetailByReqId
	* @Description:根据申领id查找对应的明细
	* @param:@param reqId
	* @param:@return
	* @return:List<OutstockDetail>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月5日 下午5:46:33
	 */
	@Query("from OutstockDetail o where o.outstock.req_id = ?1 and o.state = '已出库' ")
	public List<OutstockDetail> getOutstockDetailByReqId(String reqId);

}
