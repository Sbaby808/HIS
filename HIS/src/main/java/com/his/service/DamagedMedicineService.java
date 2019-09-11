package com.his.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IDamagedMedicineDao;
import com.his.pojo.DamagedMedicine;
import com.his.utils.ServiceException;

/**  
* @ClassName: DamagedMedicineService  
* @Description: 药品报损service
* @author crazy_long
* @date 2019年7月30日  下午12:05:40
*    
*/

@Service
@Transactional(rollbackFor=Exception.class)
public class DamagedMedicineService {
	
	@Autowired
	private IDamagedMedicineDao damagedMedicineDao;
	
	/**
	* @Title:updateDamegeForYesById
	* @Description:修改报损单位已完成
	* @param:@param damagedId
	* @param:@throws ServiceException
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年9月6日 下午11:49:40
	 */
	public void updateDamegeForYesById(String damagedId) throws ServiceException{
		try {
			DamagedMedicine dm = damagedMedicineDao.findById(damagedId).get();
			dm.setState("已完成");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("修改报损单状态失败");
		}
	}
	
	/**
	* @Title:delOneDamageAndDetail
	* @Description:级联删除报损单及其明细
	* @param:@param damagedId
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年9月6日 下午11:43:59
	 */
	public void delOneDamageAndDetail(String damagedId) throws ServiceException{
		try {
			damagedMedicineDao.deleteById(damagedId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("创建报损单失败");
		}
	}
	
	/**
	* @Title:getDamagedMedicineForNo
	* @Description:获取所有未完成的申领单
	* @param:@return
	* @return:List<DamagedMedicine>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月6日 下午4:52:45
	 */
	public List<DamagedMedicine> getDamagedMedicineForNo(){
		return damagedMedicineDao.getDamagedMedicineForNo();
	}
	
	/**
	* @Title:addDamagedMedicine
	* @Description:创建一个报损单
	* @param:@param damagedMedicine
	* @param:@throws ServiceException
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年9月6日 下午4:39:00
	 */
	public void addDamagedMedicine(DamagedMedicine damagedMedicine) throws ServiceException{
		try {
			String damagedId = UUID.randomUUID().toString().replace("-","");
			damagedMedicine.setDamagedId(damagedId);
			damagedMedicine.setState("未完成");
			damagedMedicineDao.save(damagedMedicine);

		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("创建报损单失败");
		}
	}

}
