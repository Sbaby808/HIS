package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
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
	* @Title:getAllOutstockByPage
	* @Description:分页查询所有的出库记录
	* @param:@param page
	* @param:@return
	* @return:int
	* @throws
	* @author:crazy_long
	* @Date:2019年9月21日 下午12:47:16
	 */
	@Query(value="select o.* from outstock o left outer join outpatient_requestion_medicine orm on o.req_id = orm.req_id " + 
			"where orm.req_status = '已出库'  or orm.req_status= '已入库'  "
			+ " order by o.ck_time desc ",nativeQuery = true)
	public List<Outstock> getAllOutstockByPage(Pageable page);
	
	/**
	* @Title:getAllOutstockCount
	* @Description:分页查询所有的出库记录的条数
	* @param:@return
	* @return:int
	* @throws
	* @author:crazy_long
	* @Date:2019年9月21日 下午1:20:30
	 */
	@Query(value="select count(*) from outstock o left outer join outpatient_requestion_medicine orm on o.req_id = orm.req_id " + 
			"where orm.req_status = '已出库'  or orm.req_status= '已入库'  "
			+ " order by o.ck_time desc ",nativeQuery = true)
	public int getAllOutstockCount();
		
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
