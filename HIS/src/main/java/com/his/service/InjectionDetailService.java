package com.his.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.his.dao.IInjectionDetailDao;
import com.his.pojo.InjectionDetail;
import com.his.pojo.InjectionDetailPK;
import com.his.utils.ServiceException;

/**  
* @ClassName: InjectionDetailService  
* @Description: 用药明细service
* @author crazy_long
* @date 2019年9月9日  下午9:02:02
*    
*/
@Service
public class InjectionDetailService {

	@Autowired
	private IInjectionDetailDao injectionDetailDao ;
		
	/**
	* @Title:updateDetailNumber
	* @Description:删除一个明细
	* @param:@param injId
	* @param:@param medicineId
	* @param:@param updateNumber
	* @param:@throws ServiceException
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年9月10日 下午12:13:57
	 */
	public void delOneInjectionDetail(String injId,String medicineId) throws ServiceException{
		try {
			//创建主键
			InjectionDetailPK InjectionDetailPK = new InjectionDetailPK();
			InjectionDetailPK.setInjId(injId);
			InjectionDetailPK.setMedicineId(medicineId);
			injectionDetailDao.deleteById(InjectionDetailPK);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("修改用药明细数量失败");
		}
	}
	
	/**
	* @Title:updateDetailNumber
	* @Description:修改一个明细的数量
	* @param:@param injId
	* @param:@param medicineId
	* @param:@param updateNumber
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年9月10日 上午11:19:05
	 */
	public void updateDetailNumber(String injId,String medicineId,BigDecimal updateNumber) throws ServiceException{
		try {
			System.out.println("----------------------------");
			System.out.println(injId);
			System.out.println(medicineId);
			System.out.println(updateNumber);
			//创建主键
			InjectionDetailPK InjectionDetailPK = new InjectionDetailPK();
			InjectionDetailPK.setInjId(injId);
			InjectionDetailPK.setMedicineId(medicineId);
			InjectionDetail inj = injectionDetailDao.findById(InjectionDetailPK).get();
			inj.setPsDrugNum(updateNumber);
			injectionDetailDao.save(inj);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("修改用药明细数量失败");
		}
	}
	
	/**
	* @Title:getAlreadySendDrug
	* @Description:查找未完成用药记录的已经发药的药品
	* @param:@param injId
	* @param:@return
	* @return:List<String>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月10日 上午10:32:49
	 */
	public List<InjectionDetail> getAlreadySendDrug(String injId){
		return injectionDetailDao.getAllDetialForNo(injId);
	}
	
	/**
	* @Title:getAllYpIdByInjId
	* @Description:查找已经存在的用药明细的ypid
	* @param:@param injId
	* @param:@return
	* @return:List<String>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月9日 下午10:55:29
	 */
	public List<String> getAllYpIdByInjId(String injId){
		return injectionDetailDao.getAllYpIdByInjId(injId);
	}
	
	/**
	* @Title:addAnInjectionDetail
	* @Description:插入一个药品的用药明细
	* @param:@param injectionDetail
	* @param:@throws ServiceException
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年9月9日 下午9:07:15
	 */
	public void addAnInjectionDetail(List<InjectionDetail> injectionDetail) throws ServiceException{
		try {
			//循环插入药品
			for (InjectionDetail inj : injectionDetail) {
				//创建主键
				String injId = inj.getUseDrugRecord().getInjId();
				String medicineId = inj.getMedicine().getMedicineId();
				InjectionDetailPK injectionDetailPK = new InjectionDetailPK();
				injectionDetailPK.setInjId(injId);
				injectionDetailPK.setMedicineId(medicineId);
				InjectionDetail injd = new InjectionDetail();
				injd.setId(injectionDetailPK);
				injd.setPsDrugNum(inj.getPsDrugNum());
				injd.setDrugWay(inj.getDrugWay());
				injd.setDrugTime(new Date());
				injd.setPsDrugUnit(inj.getPsDrugUnit());
				injectionDetailDao.save(injd);
			} 
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("添加用药明细失败");
		}
	}
}
