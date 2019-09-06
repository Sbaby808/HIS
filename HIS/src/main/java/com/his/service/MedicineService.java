package com.his.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IMedicineDao;
import com.his.pojo.DrugInformation;
import com.his.pojo.Medicine;
import com.his.utils.ServiceException;
import com.his.utils.SimpleTools;

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
	* @Title:searchDrugByPage
	* @Description:查找某一个部门的药房药品
	* @param:@param searchKey
	* @param:@param searchType
	* @param:@param searchSubclass
	* @param:@param searchGys
	* @param:@param searchMinorDefect
	* @param:@param minPrice
	* @param:@param maxPrice
	* @param:@param minNumber
	* @param:@param maxNumber
	* @param:@param deptId
	* @param:@param pageNum
	* @param:@param pageSize
	* @param:@return
	* @return:Map
	* @throws
	* @author:crazy_long
	* @Date:2019年9月6日 下午2:15:27
	 */
	public Map searchDrugByPage(String searchKey, String searchType, String searchSubclass, String searchGys, String searchMinorDefect,
			BigDecimal minPrice, BigDecimal maxPrice,BigDecimal minNumber,BigDecimal maxNumber, String deptId,int pageNum, int pageSize) {
		Map map = new HashMap();
		PageRequest page = PageRequest.of(pageNum - 1, pageSize);
		List<Medicine> list = medicineDao.searchDrugByPage(
				SimpleTools.addCharForSearch(searchKey), 
				"".equals(searchType) ? SimpleTools.addCharForSearch(searchType) : searchType, 
				"".equals(searchSubclass) ? SimpleTools.addCharForSearch(searchSubclass) : searchSubclass, 
				"".equals(searchGys) ? SimpleTools.addCharForSearch(searchGys) : searchGys, 
				"".equals(searchMinorDefect) ? SimpleTools.addCharForSearch(searchMinorDefect) : searchMinorDefect,
				minPrice, maxPrice,minNumber,maxNumber,deptId,
				page);
		int total = medicineDao.searchDrugCount(
				SimpleTools.addCharForSearch(searchKey), 
				"".equals(searchType) ? SimpleTools.addCharForSearch(searchType) : searchType, 
				"".equals(searchSubclass) ? SimpleTools.addCharForSearch(searchSubclass) : searchSubclass, 
				"".equals(searchGys) ? SimpleTools.addCharForSearch(searchGys) : searchGys, 
				"".equals(searchMinorDefect) ? SimpleTools.addCharForSearch(searchMinorDefect) : searchMinorDefect, 
						minPrice, maxPrice,minNumber,maxNumber,deptId);
		map.put("drugs", list);
		map.put("total", total);
		return map;
	}
	
	/**
	* @Title:updateKuCunCount
	* @Description:更改药品的库存
	* @param:@param medicineId
	* @param:@param updateNumber
	* @param:@throws ServiceException
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年9月6日 下午2:59:34
	 */
	public void updateKuCunCount(String medicineId,BigDecimal updateNumber) throws ServiceException{
		Medicine medicine = medicineDao.findById(medicineId).get();
		medicine.setMedicineName(updateNumber);
		
	}
	
	/**
	* @Title:warehouseIsHave
	* @Description:判断一个部门的这个药品的批次存不存在
	* @param:@param pckcId
	* @param:@param deptId
	* @param:@return
	* @return:boolean
	* @throws
	* @author:crazy_long
	* @Date:2019年9月6日 上午12:48:23
	 */
	public Medicine warehouseIsHave(String pckcId ,String deptId) {
		return medicineDao.warehouseIsHave(pckcId, deptId);
	}
	
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
