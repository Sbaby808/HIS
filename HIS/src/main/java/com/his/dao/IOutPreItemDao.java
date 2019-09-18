package com.his.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.OutPreItem;
import com.his.pojo.OutPreItemPK;

/**  
* @ClassName: IOutPreItemDao  
* @Description: 门诊处方明细Dao
* @author Sbaby
* @date 2019年8月22日  上午11:02:27
*    
*/
public interface IOutPreItemDao extends CrudRepository<OutPreItem, OutPreItemPK> {
	
	/**
	* @Title:queryOutPreItemByPreId
	* @Description:根据处方id查找对应的明细
	* @param:@param prescriptionId
	* @param:@return
	* @return:List<OutPreItem>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月8日 下午11:41:49
	 */
	@Query("from OutPreItem o where o.prescription.prescriptionId = ?1 ")
	public List<OutPreItem> queryOutPreItemByPreId(String prescriptionId);

}
