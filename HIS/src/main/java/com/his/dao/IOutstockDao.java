package com.his.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.DrugWarehouse;
import com.his.pojo.Outstock;

/**  
* @ClassName: IOutStockDao  
* @Description: 药库出库信息表
* @author crazy_long
* @date 2019年7月30日  上午10:47:01
*    
*/
public interface IOutstockDao extends CrudRepository<Outstock, String>{
	
	/**
	* @Title:getAllWarehouse
	* @Description:查询药品申领单对应的出库单是否已经存在
	* @param:@param req_id
	* @param:@return
	* @return:int
	* @throws
	* @author:crazy_long
	* @Date:2019年9月1日 下午11:14:29
	 */
	@Query("select count(*) from Outstock o where o.req_id = ?1 ")
	public int getOutstockCount(String req_id);
	
	/**
	* @Title:getOutstockId
	* @Description:根据申领单查询对应的出库单
	* @param:@param req_id
	* @param:@return
	* @return:Outstock
	* @throws
	* @author:crazy_long
	* @Date:2019年9月3日 上午10:13:56
	 */
	@Query("from Outstock o where o.req_id = ?1 ")
	public Outstock getOutstockId(String req_id);
}
