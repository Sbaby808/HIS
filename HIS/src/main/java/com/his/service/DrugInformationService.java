package com.his.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IDrugInformationDao;
import com.his.pojo.DrugInformation;
import com.his.pojo.MedicinePay;

/**  
* @ClassName: DrugInformationService  
* @Description: 药品信息service
* @author crazy_long
* @date 2019年7月30日  下午12:07:00
*    
*/

@Service
@Transactional(rollbackFor=Exception.class)
public class DrugInformationService {

	@Autowired
	private IDrugInformationDao drugInformationDao;
	
	/**
	* @Title:getNoPrice
	* @Description:查询未划价的药品
	* @param:@return
	* @return:List<DrugInformation>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月7日 上午9:23:18
	 */
	public List<DrugInformation> getNoPrice() {
		return drugInformationDao.getNoPrice();
	}
	
	/**
	* @Title:getById
	* @Description:根据id查询药品信息
	* @param:@param id
	* @param:@return
	* @return:DrugInformation
	* @throws
	* @author:Sbaby
	* @Date:2019年8月7日 上午9:57:06
	 */
	public DrugInformation getById(String id) {
		return drugInformationDao.findById(id).get();
	}
	
	
	
}
