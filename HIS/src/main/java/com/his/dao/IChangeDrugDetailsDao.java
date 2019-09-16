package com.his.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.ChangeDrugDetail;
import com.his.pojo.ChangeDrugDetailPK;

/**  
* @ClassName: IChangeDrugDetailsDao  
* @Description: 调拨出库明细dao(回库)
* @author crazy_long
* @date 2019年7月30日  上午11:05:29
*    
*/
public interface IChangeDrugDetailsDao extends CrudRepository<ChangeDrugDetail,ChangeDrugDetailPK>{
	
	/**
	* @Title:getDetailByAlloId
	* @Description:根据返库id查找明细
	* @param:@param alloId
	* @param:@return
	* @return:List<ChangeDrugDetail>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月12日 下午11:12:37
	 */
	@Query("from ChangeDrugDetail c where c.allocationOutbound.alloId = ?1 ")
	public List<ChangeDrugDetail> getDetailByAlloId(String alloId);
	
}
