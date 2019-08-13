package com.his.dao;

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

}
