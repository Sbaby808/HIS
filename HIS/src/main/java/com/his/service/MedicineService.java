package com.his.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IMedicineDao;
import com.his.pojo.Medicine;
import com.his.utils.ServiceException;

/**  
* @ClassName: MedicineService  
* @Description: 药房药品信息service
* @author crazy_long
* @date 2019年7月30日  下午12:14:07
*    
*/

@Service
@Transactional(rollbackFor=Exception.class)
public class MedicineService {
	
	@Autowired
	private IMedicineDao medicineDao;
	
	/**
	* @Title:qeuryNoKuCun
	* @Description:查询没有库存的药品
	* @param:@param chooseNumber
	* @param:@param deptId
	* @param:@return
	* @param:@throws ServiceException
	* @return:List<Medicine>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月27日 下午10:00:03
	 */
	public List<Medicine> qeuryNoKuCun(int chooseNumber,String deptId) throws ServiceException{
		int minNumber = chooseNumber*5-4;
		int maxNumber = chooseNumber*5;
		List<Medicine> list = null;
		try {
			list =  medicineDao.queryNoKuCun(new BigDecimal(minNumber), new BigDecimal(maxNumber),deptId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("查找药品失败");
		}
		return list;
	}
	
	
	/**
	* @Title:queryMedicineNowNumberByPage
	* @Description:根据药房药品库存段查找对应的药品
	* @param:@param chooseNumber
	* @param:@param curPage
	* @param:@param pageSize
	* @param:@return
	* @param:@throws ServiceException
	* @return:Map
	* @throws
	* @author:crazy_long
	* @Date:2019年8月26日 下午4:48:05
	 */
	public List<Medicine> queryMedicineNowNumber(int chooseNumber,String deptId) throws ServiceException{
		int minNumber = chooseNumber*5-4;
		int maxNumber = chooseNumber*5;
		List<Medicine> list = null;
		try {
			list =  medicineDao.queryNowNumber(new BigDecimal(minNumber), new BigDecimal(maxNumber),deptId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("查找药品失败");
		}
		return list;
	}
	
	/**
	* @Title:addTestDate
	* @Description:添加药房测试数据
	* @param:
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年8月26日 下午3:56:56
	 */
	public void addTestDate(Medicine medicine) {
		medicineDao.save(medicine);
	}

}
