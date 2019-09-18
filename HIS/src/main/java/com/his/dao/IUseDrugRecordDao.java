package com.his.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.UseDrugRecord;

/**  
* @ClassName: IUseDrugRecordDao  
* @Description: 用药记录Dao
* @author Sbaby
* @date 2019年9月3日  下午4:28:12
*    
*/
public interface IUseDrugRecordDao extends CrudRepository<UseDrugRecord, String> {

	/**
	* @Title:UseDrugRecordIsHave
	* @Description:查找未完成用药记录是否存在
	* @param:@param prescriptionId
	* @param:@return
	* @return:int
	* @throws
	* @author:crazy_long
	* @Date:2019年9月9日 下午9:27:07
	 */
	@Query("select count(*) from UseDrugRecord u where u.prescription.prescriptionId = ?1 and u.state = '未取药' ")
	public int UseDrugRecordIsHave(String prescriptionId);
	
	/**
	* @Title:UseDrugRecordIsHave
	* @Description:查找已完成用药记录是否存在
	* @param:@param prescriptionId
	* @param:@return
	* @return:int
	* @throws
	* @author:crazy_long
	* @Date:2019年9月9日 下午9:27:07
	 */
	@Query("select count(*) from UseDrugRecord u where u.prescription.prescriptionId = ?1 and u.state = '已取药' ")
	public int UseDrugRecordIsAlready(String prescriptionId);
	
	/**
	* @Title:getUseDrugRecord
	* @Description:根据处方id获取用药记录
	* @param:@param prescriptionId
	* @param:@return
	* @return:UseDrugRecord
	* @throws
	* @author:crazy_long
	* @Date:2019年9月9日 下午9:36:55
	 */
	@Query("from UseDrugRecord u where u.prescription.prescriptionId = ?1 ")
	public UseDrugRecord getUseDrugRecord(String prescriptionId);
	
}
