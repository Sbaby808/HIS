package com.his.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IDrugInformationDao;
import com.his.dao.IEmpInformationDao;
import com.his.dao.IMedicinePayDao;
import com.his.pojo.DrugInformation;
import com.his.pojo.EmpInformation;
import com.his.pojo.MedicinePay;
import com.his.utils.SimpleTools;

/**  
* @ClassName: MedicinePayService  
* @Description: 药品划价项service
* @author Sbaby
* @date 2019年8月6日  下午5:00:50
*    
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class MedicinePayService {

	@Autowired
	private IMedicinePayDao medicinePayDao;
	@Autowired
	private DrugInformationService drugInformationService;
	@Autowired
	private IDrugInformationDao drugInformationDao;
	@Autowired
	private IEmpInformationDao empInformationDao;
	
	/**
	* @Title:getAll
	* @Description:查询所有药品划价项
	* @param:@return
	* @return:List<MedicinePay>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月6日 下午5:05:26
	 */
	public List<DrugInformation> getAll() {
//		return (List<MedicinePay>) medicinePayDao.findAll();
		return drugInformationDao.getPrice();
		
	}
	
	/**
	* @Title:getByPage
	* @Description:分页查询药品划价项
	* @param:@param pageNum
	* @param:@param pageSize
	* @param:@return
	* @return:List<MedicinePay>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月6日 下午5:13:50
	 */
	public List<DrugInformation> getByPage(int pageNum, int pageSize) {
		PageRequest page = PageRequest.of(pageNum - 1, pageSize);
//		return medicinePayDao.getByPage(page);
		return drugInformationDao.getPriceByPage(page);
		
	}
	
	/**
	* @Title:getAllCount
	* @Description:查询药品划价项总记录条数
	* @param:@return
	* @return:int
	* @throws
	* @author:Sbaby
	* @Date:2019年8月6日 下午5:34:22
	 */
	public int getAllCount() {
		return medicinePayDao.getAllCount();
	}
	
	/**
	* @Title:addMedicinePay
	* @Description:添加药品收费项
	* @param:@param medicinePay
	* @return:void
	* @throws
	* @author:Sbaby
	* @Date:2019年8月7日 上午8:44:45
	 */
	public void addMedicinePay(MedicinePay medicinePay) {
		medicinePay.setMedicinePayId(UUID.randomUUID().toString().replaceAll("-", ""));
		DrugInformation drugInformation = medicinePay.getDrugInformation();
		drugInformation.setMedicinePayId(medicinePay.getMedicinePayId());
		drugInformation.setMedicinePay(medicinePay);
		drugInformationDao.save(drugInformation);
		medicinePayDao.save(medicinePay);
	}
	
	/**
	* @Title:setForDrug
	* @Description:绑定药品收费项与药品间的关系
	* @param:@param drugId
	* @param:@param medicinePayId
	* @return:void
	* @throws
	* @author:Sbaby
	* @Date:2019年8月7日 上午11:20:22
	 */
	public void setForDrug(String drugId, String medicinePayId) {
		MedicinePay medicinePay = medicinePayDao.findByUUID(medicinePayId);
		DrugInformation drugInformation = drugInformationService.getById(drugId);
		drugInformation.setMedicinePayId(medicinePayId);
		drugInformation.setMedicinePay(medicinePay);
		medicinePay.setDrugInformation(drugInformation);
		drugInformationDao.save(drugInformation);
		medicinePayDao.save(medicinePay);
	}
	
	/**
	* @Title:searchByPage
	* @Description:分页搜索药品收费项
	* @param:@param searchKey
	* @param:@param searchType
	* @param:@param searchSubclass
	* @param:@param searchGys
	* @param:@param pageNum
	* @param:@param pageName
	* @param:@return
	* @return:List<MedicinePay>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月7日 下午5:08:59
	 */
	public List<DrugInformation> searchByPage(String searchKey, String searchType, String searchSubclass, String searchGys, String searchEmp,
			BigDecimal minPrice, BigDecimal maxPrice, int pageNum, int pageSize) {
		PageRequest page = PageRequest.of(pageNum - 1, pageSize, Direction.ASC, "ypName");
//		return medicinePayDao.searchByPage(
//				SimpleTools.addCharForSearch(searchKey), 
//				"".equals(searchType) ? SimpleTools.addCharForSearch(searchType) : searchType, 
//				"".equals(searchSubclass) ? SimpleTools.addCharForSearch(searchSubclass) : searchSubclass, 
//				"".equals(searchGys) ? SimpleTools.addCharForSearch(searchGys) : searchGys, 
//				"".equals(searchEmp) ? SimpleTools.addCharForSearch(searchEmp) : searchEmp, 
//				minPrice, maxPrice, 
//				page);
		return drugInformationDao.searchPriceByPage(
				SimpleTools.addCharForSearch(searchKey), 
				"".equals(searchType) ? SimpleTools.addCharForSearch(searchType) : searchType, 
				"".equals(searchSubclass) ? SimpleTools.addCharForSearch(searchSubclass) : searchSubclass, 
				"".equals(searchGys) ? SimpleTools.addCharForSearch(searchGys) : searchGys, 
				"".equals(searchEmp) ? SimpleTools.addCharForSearch(searchEmp) : searchEmp, 
				minPrice, maxPrice, 
				page);
	}
	
	/**
	* @Title:searchCount
	* @Description:查询符合搜素条件的记录条数
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
	* @Date:2019年8月8日 上午8:41:58
	 */
	public int searchCount(String searchKey, String searchType, String searchSubclass, String searchGys, String searchEmp,
			BigDecimal minPrice, BigDecimal maxPrice) {
//		return medicinePayDao.searchCount(
//				SimpleTools.addCharForSearch(searchKey), 
//				"".equals(searchType) ? SimpleTools.addCharForSearch(searchType) : searchType, 
//				"".equals(searchSubclass) ? SimpleTools.addCharForSearch(searchSubclass) : searchSubclass, 
//				"".equals(searchGys) ? SimpleTools.addCharForSearch(searchGys) : searchGys, 
//				"".equals(searchEmp) ? SimpleTools.addCharForSearch(searchEmp) : searchEmp, 
//				minPrice, maxPrice
//				);
		return drugInformationDao.searchPriceCount(
				SimpleTools.addCharForSearch(searchKey), 
				"".equals(searchType) ? SimpleTools.addCharForSearch(searchType) : searchType, 
				"".equals(searchSubclass) ? SimpleTools.addCharForSearch(searchSubclass) : searchSubclass, 
				"".equals(searchGys) ? SimpleTools.addCharForSearch(searchGys) : searchGys, 
				"".equals(searchEmp) ? SimpleTools.addCharForSearch(searchEmp) : searchEmp, 
				minPrice, maxPrice
				);
	}
	
	/**
	* @Title:update
	* @Description:修改药品收费项
	* @param:@param medicinePay
	* @return:void
	* @throws
	* @author:Sbaby
	* @Date:2019年8月8日 下午3:15:01
	 */
	public void update(String medicinePayId, BigDecimal price, String ygxh) {
		EmpInformation emp = empInformationDao.findById(ygxh).get();
		MedicinePay medicinePay = medicinePayDao.findById(medicinePayId).get();
		medicinePay.setMedicinePayPrice(price);
		medicinePay.setEmpInformation(emp);
		medicinePayDao.save(medicinePay);
	}
	
}
