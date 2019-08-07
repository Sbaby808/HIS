package com.his.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IMedicinePayDao;
import com.his.pojo.MedicinePay;

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
	
	/**
	* @Title:getAll
	* @Description:查询所有药品划价项
	* @param:@return
	* @return:List<MedicinePay>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月6日 下午5:05:26
	 */
	public List<MedicinePay> getAll() {
		return (List<MedicinePay>) medicinePayDao.findAll();
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
	public List<MedicinePay> getByPage(int pageNum, int pageSize) {
		PageRequest page = PageRequest.of(pageNum - 1, pageSize, Direction.ASC, "medicinePayName");
		return medicinePayDao.getByPage(page);
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
		medicinePayDao.save(medicinePay);
	}
	
}
