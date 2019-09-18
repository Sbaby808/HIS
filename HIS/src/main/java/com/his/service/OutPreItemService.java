package com.his.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.his.dao.IOutPreItemDao;
import com.his.pojo.OutPreItem;

/**  
* @ClassName: OutPreItemService  
* @Description: 处方明细service
* @author crazy_long
* @date 2019年9月8日  下午11:36:47
*    
*/
@Service
public class OutPreItemService {
	
	@Autowired
	private IOutPreItemDao outPreItemDao;
	
	/**
	* @Title:getPrescriptionDetailToBack
	* @Description:查找处方明细
	* @param:@param prescriptionId
	* @param:@return
	* @return:Map
	* @throws
	* @author:crazy_long
	* @Date:2019年9月10日 下午4:49:53
	 */
	public 	List<OutPreItem> getPreDetailByPreID(String prescriptionId){
		List<OutPreItem> list = outPreItemDao.queryOutPreItemByPreId(prescriptionId);
		return list;
	} 
	
	/**
	* @Title:queryOutPreItemByPreId
	* @Description:根据处方id查找对应的明细
	* @param:@param prescriptionId
	* @param:@return
	* @return:List<OutPreItem>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月8日 下午11:43:13
	 */
	public List<OutPreItem> queryOutPreItemByPreId(String prescriptionId){
		return outPreItemDao.queryOutPreItemByPreId(prescriptionId);
	}

}
