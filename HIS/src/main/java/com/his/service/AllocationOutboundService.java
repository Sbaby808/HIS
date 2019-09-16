package com.his.service;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IAllocationOutboundDao;
import com.his.dao.IChangeDrugDetailsDao;
import com.his.dao.IDrugWarehouseDao;
import com.his.dao.IEmpInformationDao;
import com.his.dao.IMedicineDao;
import com.his.pojo.AllocationOutbound;
import com.his.pojo.ChangeDrugDetail;
import com.his.pojo.DrugWarehouse;
import com.his.pojo.EmpInformation;
import com.his.pojo.Medicine;
import com.his.utils.ServiceException;

/**  
* @ClassName: AllocationOutboundService  
* @Description: 药品调拨出库service   当作回库使用
* @author crazy_long
* @date 2019年7月30日  上午11:52:07
*    
*/

@Service
@Transactional(rollbackFor=Exception.class)
public class AllocationOutboundService {
	
	@Autowired
	private IAllocationOutboundDao allocationOutboundDao;
	@Autowired
	private IChangeDrugDetailsDao changeDrugDetailsDao;
	@Autowired
	private IEmpInformationDao empInformationDao;
	@Autowired
	private IMedicineDao medicineDao;
	@Autowired
	private IDrugWarehouseDao drugWarehouseDao;
	
	/**
	* @Title:finishBack
	* @Description:完成回库
	* @param:@param alloId
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年9月13日 下午10:01:36
	 */
	public void finishBack(String alloId) throws ServiceException{
		try {
			//先获取到明细
			List<ChangeDrugDetail> list = changeDrugDetailsDao.getDetailByAlloId(alloId);
			for (ChangeDrugDetail cg : list) {
				//取出数量
				BigDecimal backNumber = cg.getChangeDrugNum();
				//取出库存id
				String medicineId = cg.getMedicine().getMedicineId();
				//找出药库药品的id
				Medicine medicine = medicineDao.findById(medicineId).get();
				String pckcId = medicine.getDrugWarehouse().getPckcId();
				//更改库存
				DrugWarehouse dw = drugWarehouseDao.findById(pckcId).get();
				dw.setNowNumber(dw.getNowNumber().add(backNumber));
			}
			//修改回库状态为 “已回库”
			AllocationOutbound al = allocationOutboundDao.findById(alloId).get();
			al.setAlloStatus("已回库");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("完成回库失败");
		}
	}
	
	/**
	* @Title:upadateType
	* @Description:改变回库单状态
	* @param:@param alloId
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年9月12日 下午4:58:29
	 */
	public void upadateStatus(String alloId,String newStatus)  throws ServiceException{
		try {
			AllocationOutbound allo = allocationOutboundDao.findById(alloId).get();
			allo.setAlloStatus(newStatus);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("改变回库单状态失败");
		}
	}
	
	/**
	* @Title:getForNoPassTimeSubmitCount
	* @Description:获取所有已提交的正常回库单的条数
	* @param:@return
	* @return:int
	* @throws
	* @author:crazy_long
	* @Date:2019年9月13日 下午12:38:11
	 */
	public int getForNoPassTimeSubmitCount(){
		return this.getForNoPassTimeSubmit().size();
	}
	
	/**
	* @Title:getForPassTimeSubmitCount
	* @Description:获取所有已提交的过期的条数
	* @param:@return
	* @return:int
	* @throws
	* @author:crazy_long
	* @Date:2019年9月13日 下午12:41:36
	 */
	public int getForPassTimeSubmitCount(){
		return this.getForPassTimeSubmit().size();
	}
	
	/**
	* @Title:getForNoPassTimeSubmit
	* @Description:获取所有已提交的正常回库单
	* @param:@return
	* @return:List<AllocationOutbound>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月13日 下午12:35:56
	 */
	public List<AllocationOutbound> getForNoPassTimeSubmit(){
		return allocationOutboundDao.getForNoPassTimeSubmit();
	}
	
	/**
	* @Title:getForPassTimeSubmit
	* @Description:获取已提交的过期回库单
	* @param:@return
	* @return:List<AllocationOutbound>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月13日 下午12:36:47
	 */
	public List<AllocationOutbound> getForPassTimeSubmit(){
		return allocationOutboundDao.getForPassTimeSubmit();
	}
	
	
	/**
	* @Title:getForNoPassTime
	* @Description:获取所有未提交的回库单
	* @param:@return
	* @return:List<AllocationOutbound>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月12日 下午4:50:14
	 */
	public List<AllocationOutbound> getAllNoDo(){
		return allocationOutboundDao.getAllNoDo();
	};
	
	/**
	* @Title:getForNoPassTime
	* @Description:获取未提交的正常回库单
	* @param:@return
	* @return:List<AllocationOutbound>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月12日 下午4:50:14
	 */
	public List<AllocationOutbound> getForNoPassTime(){
		return allocationOutboundDao.getForNoPassTime();
	};
	
	/**
	* @Title:getForPassTime
	* @Description:获取未提交的过期回库单
	* @param:@return
	* @return:List<AllocationOutbound>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月12日 下午4:50:39
	 */
	public List<AllocationOutbound> getForPassTime(){
		return allocationOutboundDao.getForPassTime();
	};
	
	/**
	* @Title:addAnAllocationOutbound
	* @Description:创建一个回库单
	* @param:@param allocationOutbound
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年9月12日 下午4:22:48
	 */
	public void addAnAllocationOutbound(AllocationOutbound allocationOutbound) throws ServiceException{
		try {
			String alloId = UUID.randomUUID().toString().replace("-", "");
			AllocationOutbound alo = new AllocationOutbound();
			alo.setAlloId(alloId);
			EmpInformation emp = empInformationDao.findById(allocationOutbound.getEmpInformation().getYgxh()).get();
			alo.setEmpInformation(emp);
			alo.setAlloTime(new Date());
			alo.setAlloStatus("未回库");
			alo.setBackType(allocationOutbound.getBackType());
			allocationOutboundDao.save(alo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("创建回库单失败");
		}
	}

}
