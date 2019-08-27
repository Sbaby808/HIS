package com.his.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.DrugWarehouse;

/**  
* @ClassName: IDrugWarehouseDao  
* @Description: 药库批次库存dao
* @author crazy_long
* @date 2019年7月30日  上午10:50:06
*    
*/
public interface IDrugWarehouseDao extends CrudRepository<DrugWarehouse, String>{
	
	/**
	* @Title:batchIsExist
	* @Description:List的长度判断药品批次是否存在
	* @param:@param ypid
	* @param:@param ypProduceDate
	* @param:@return
	* @return:List<DrugWarehouse>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月17日 下午3:31:15
	 */
	@Query("from DrugWarehouse d where d.drugInformation.ypId = ?1 and d.produceDate = ?2")
	public List<DrugWarehouse> batchIsExist(String ypid,Date ypProduceDate);
	
	
	/**
	 * 
	* @Title:getname
	* @Description:TODO根据批次id获得药品名和规格
	* @param:@param pcid
	* @param:@return
	* @return:String[]
	* @throws
	* @author:TRC
	* @Date:2019年8月9日 下午3:54:18
	 */
	@Query(value="select d.drugInformation.ypName,d.drugInformation.ypGuige from DrugWarehouse d where d.pckcId=?1")
	public String [] getname(String pcid);

	/**
	 * 
	* @Title:getDrugsOrderByExpireDate
	* @Description:根据到期时间查询某种药品
	* @param:@param ypId
	* @param:@return
	* @return:List<DrugWarehouse>
	* @throws
	* @author:Hamster
	* @Date:2019年8月26日 下午3:00:34
	 */
	@Query("from DrugWarehouse d where d.drugInformation.ypId = ?1 order by d.expireDate asc ")
	public List <DrugWarehouse> getDrugsOrderByExpireDate(String ypId);
}
