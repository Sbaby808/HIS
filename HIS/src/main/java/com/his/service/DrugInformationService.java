package com.his.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IDrugInformationDao;
import com.his.pojo.DrugInformation;
import com.his.utils.ServiceException;
import com.his.utils.SimpleTools;

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
			drugInformation.setYpId(UUID.randomUUID().toString().replace("-", ""));
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
	* @param pageSize 
	 * @param pageNum 
	 * @Title:getNoPrice
	* @Description:查询未划价的药品
	* @param:@return
	* @return:List<DrugInformation>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月7日 上午9:23:18
	 */
	public List<DrugInformation> getNoPrice(int pageNum, int pageSize) {
		PageRequest page = PageRequest.of(pageNum - 1, pageSize);
		return drugInformationDao.getNoPrice(page);
	}
	
	/**
	* @Title:searchAllInformationByPage
	* @Description:查询药品的所有信息
	* @param:@param searchKey
	* @param:@param searchType
	* @param:@param searchSubclass
	* @param:@param searchGys
	* @param:@param searchMinorDefect
	* @param:@param minPrice
	* @param:@param maxPrice
	* @param:@param pageNum
	* @param:@param pageSize
	* @param:@return
	* @return:List<DrugInformation>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月9日 下午11:19:29
	 */
	public Map searchAllInformationByPage(String searchKey, String searchType, String searchSubclass, String searchGys, String searchMinorDefect,
			BigDecimal minPrice, BigDecimal maxPrice, int pageNum, int pageSize) {
		Map map = new HashMap();
		PageRequest page = PageRequest.of(pageNum - 1, pageSize, Direction.ASC, "ypName");
		List<DrugInformation> list = drugInformationDao.searchAllInformationByPage(
				SimpleTools.addCharForSearch(searchKey), 
				"".equals(searchType) ? SimpleTools.addCharForSearch(searchType) : searchType, 
				"".equals(searchSubclass) ? SimpleTools.addCharForSearch(searchSubclass) : searchSubclass, 
				"".equals(searchGys) ? SimpleTools.addCharForSearch(searchGys) : searchGys, 
				"".equals(searchMinorDefect) ? SimpleTools.addCharForSearch(searchMinorDefect) : searchMinorDefect, 
				minPrice, maxPrice, 
				page);
		int total = drugInformationDao.searchAllInformationByPageCount(
				SimpleTools.addCharForSearch(searchKey), 
				"".equals(searchType) ? SimpleTools.addCharForSearch(searchType) : searchType, 
				"".equals(searchSubclass) ? SimpleTools.addCharForSearch(searchSubclass) : searchSubclass, 
				"".equals(searchGys) ? SimpleTools.addCharForSearch(searchGys) : searchGys, 
				"".equals(searchMinorDefect) ? SimpleTools.addCharForSearch(searchMinorDefect) : searchMinorDefect, 
				minPrice, maxPrice);
		map.put("drugs", list);
		map.put("total", total);
		return map;
	}
	
	/**
	* @Title:updataDrugInformation
	* @Description:修改药品信息
	* @param:@param editDrugInfo
	* @param:@throws ServiceException
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年8月10日 下午2:52:17
	 */
	public void updataDrugInformation(DrugInformation editDrugInfo) throws ServiceException {
		try {
			drugInformationDao.save(editDrugInfo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("修改药品失败");
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
	* @Title:searchNoPriceCount
	* @Description:模糊查询未划价药品的总记录条数
	* @param:@param searchKey
	* @param:@param searchType
	* @param:@param searchSubclass
	* @param:@param searchGys
	* @param:@param searchEmp
	* @param:@param minPrice
	* @param:@param maxPrice
	* @param:@return
	* @return:int
	* @throws
	* @author:Sbaby
	* @Date:2019年8月9日 上午11:07:37
	 */
	public int searchNoPriceCount(String searchKey, String searchType, String searchSubclass, String searchGys,
			BigDecimal minPrice, BigDecimal maxPrice) {
		return drugInformationDao.searchNoPriceCount(
				SimpleTools.addCharForSearch(searchKey), 
				"".equals(searchType) ? SimpleTools.addCharForSearch(searchType) : searchType, 
				"".equals(searchSubclass) ? SimpleTools.addCharForSearch(searchSubclass) : searchSubclass, 
				"".equals(searchGys) ? SimpleTools.addCharForSearch(searchGys) : searchGys, 
				minPrice, maxPrice
				);
	}
	
	/**
	* @Title:searchPriceCount
	* @Description:搜索已划价的药品数量
	* @param:@param searchKey
	* @param:@param searchType
	* @param:@param searchSubclass
	* @param:@param searchGys
	* @param:@return
	* @return:int
	* @throws
	* @author:Sbaby
	* @Date:2019年8月21日 下午5:14:37
	 */
	public int searchPriceCount(String searchKey, String searchType, String searchSubclass, String searchGys) {
		return drugInformationDao.searchPriceCount(
				SimpleTools.addCharForSearch(searchKey), 
				"".equals(searchType) ? SimpleTools.addCharForSearch(searchType) : searchType, 
				"".equals(searchSubclass) ? SimpleTools.addCharForSearch(searchSubclass) : searchSubclass, 
				"".equals(searchGys) ? SimpleTools.addCharForSearch(searchGys) : searchGys
				);
	}
	
	/**
	* @Title:searchNoPrice
	* @Description:模糊查询未划价的药品信息
	* @param:@param searchKey
	* @param:@param searchType
	* @param:@param searchSubclass
	* @param:@param searchGys
	* @param:@param minPrice
	* @param:@param maxPrice
	* @param:@return
	* @return:List<DrugInformation>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月9日 上午11:55:18
	 */
	public List<DrugInformation> searchNoPrice(String searchKey, String searchType, String searchSubclass, String searchGys,
			BigDecimal minPrice, BigDecimal maxPrice, int pageNum, int pageSize) {
		PageRequest page = PageRequest.of(pageNum - 1, pageSize);
		return drugInformationDao.searchNoPrice(
				SimpleTools.addCharForSearch(searchKey), 
				"".equals(searchType) ? SimpleTools.addCharForSearch(searchType) : searchType, 
				"".equals(searchSubclass) ? SimpleTools.addCharForSearch(searchSubclass) : searchSubclass, 
				"".equals(searchGys) ? SimpleTools.addCharForSearch(searchGys) : searchGys, 
				minPrice, maxPrice,
				page);
	}
	
	/**
	* @Title:searchPrice
	* @Description: 分页搜索
	* @param:@param searchKey
	* @param:@param searchType
	* @param:@param searchSubclass
	* @param:@param searchGys
	* @param:@param pageNum
	* @param:@param pageSize
	* @param:@return
	* @return:List<DrugInformation>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月21日 下午5:18:41
	 */
	public List<DrugInformation> searchPrice(String searchKey, String searchType, String searchSubclass, String searchGys,
			int pageNum, int pageSize) {
		PageRequest page = PageRequest.of(pageNum - 1, pageSize);
		return drugInformationDao.searchPrice(
				SimpleTools.addCharForSearch(searchKey), 
				"".equals(searchType) ? SimpleTools.addCharForSearch(searchType) : searchType, 
				"".equals(searchSubclass) ? SimpleTools.addCharForSearch(searchSubclass) : searchSubclass, 
				"".equals(searchGys) ? SimpleTools.addCharForSearch(searchGys) : searchGys, 
				page);
	}
	
	/**
	* @Title:getNoPriceCount
	* @Description:查询未划价药品数量
	* @param:@return
	* @return:int
	* @throws
	* @author:Sbaby
	* @Date:2019年8月10日 下午4:36:12
	 */
	public int getNoPriceCount() {
		return drugInformationDao.getNoPriceCount();
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
	public List <DrugInformation> getAllDrugInformation(String ypName){
		return drugInformationDao.getAllDrugInformation(ypName);
	}
	
	/**
	* @Title:getAllDrugInformationType
	* @Description:获取所有药品大类名称
	* @param:@return
	* @return:List<String>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月10日 上午12:22:03
	 */
	public List<String> getAllDrugInformationType(){
		return drugInformationDao.getAllYpType();
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
	public List <DrugInformation> getDrugInformation(String ypName){
		return drugInformationDao.getDrugInformation(ypName);
	}
	
	/**
	* @Title:getAllDrugBySubclassId
	* @Description:根据小类id查询对应的所有药品
	* @param:@param subclassId
	* @param:@return
	* @return:List<DrugInformation>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月12日 下午12:12:07
	 */
	public List <DrugInformation> getAllDrugBySubclassId(String subclassId) {
		return drugInformationDao.queryAllBySubclassId(subclassId);
	}
	
}
