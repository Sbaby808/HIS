package com.his.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.PutStock;

/**  
* @ClassName: IPutStockDao  
* @Description: 药品入库信息dao
* @author crazy_long
* @date 2019年7月30日  上午10:17:37
*    
*/
public interface IPutStockDao extends CrudRepository<PutStock, String>{
	
	/**
	* @Title:queryAllPutTime
	* @Description:获取所有入库信息
	* @param:@return
	* @return:List<PutStock>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月19日 上午10:47:03
	 */
	@Query("from PutStock p order by p.rkTime desc")
	public List<PutStock> queryAllPutTime();

}
