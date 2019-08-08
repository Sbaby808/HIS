package com.his.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IDrugInformationDao;
import com.his.pojo.DrugInformation;
import com.his.pojo.MedicinePay;
import com.his.utils.ServiceException;

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
	* @Title:addDrugInformation
	* @Description:添加单个药品
	* @param:@param drugInformation
	* @param:@throws ServiceException
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年8月8日 下午4:09:42
	 */
	public void addDrugInformation(DrugInformation drugInformation) throws ServiceException{
		try {
			drugInformationDao.save(drugInformation);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("添加药品失败");
		}
	}
	
	/**
	* @Title:addDrugInformationSByBatch
	* @Description:批量添加同类（小类）药品信息
	* @param:@param list
	* @param:@throws ServiceException
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年8月8日 下午4:14:04
	 */
	public void addDrugInformationSByBatch(List<DrugInformation> list) throws ServiceException{
		try {
			for (DrugInformation drugInformation : list) {
				drugInformation.setYpId(UUID.randomUUID().toString().replace("-", ""));
				drugInformationDao.save(drugInformation);
			} 
		} catch (Exception e) {
			throw new ServiceException("批量添加药品信息失败");
		}
	}
	
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
	
	/**
	 * 
	* @Title:getAllDrugInformation
	* @Description:查询所有药品信息
	* @param:@return
	* @return:List<DrugInformation>
	* @throws
	* @author:Hamster
	* @Date:2019年8月7日 下午9:17:51
	 */
	public List <DrugInformation> getAllDrugInformation(){
		return drugInformationDao.getAllDrugInformation();
	}
	
}
