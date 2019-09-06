package com.his.dao;

import java.util.List;

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
