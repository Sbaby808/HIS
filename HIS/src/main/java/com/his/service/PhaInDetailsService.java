package com.his.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IOutstockDetailsDao;
import com.his.dao.IPhaInDetailsDao;
import com.his.pojo.Dept;
import com.his.pojo.DrugWarehouse;
import com.his.pojo.Medicine;
import com.his.pojo.OutstockDetail;
import com.his.pojo.OutstockDetailPK;
import com.his.pojo.PhaInDetail;
import com.his.pojo.PhaInDetailHelp;
import com.his.pojo.PhaInDetailPK;
import com.his.utils.ServiceException;

/**  
* @ClassName: PhaInDetailsService  
* @Description: 药房入库明细service
* @author crazy_long
* @date 2019年7月30日  下午2:27:38
*    
*/
@Service
@Transactional(rollbackFor=Exception.class)
public class PhaInDetailsService {
	
	@Autowired 
	private IPhaInDetailsDao phaInDetailsDao;
	@Autowired 
	private MedicineService medicineService;
	@Autowired 
	private IOutstockDetailsDao outstockDetailsDao;
	
	/**
	* @Title:addPhaInDetailByBatch
	* @Description:批量添加药房入库明细
	* @param:@param phaInDetailHelp
	* @param:@throws ServiceException
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年9月5日 下午11:54:02
	 */
	public void addPhaInDetailByBatch(List<PhaInDetailHelp> phaInDetailHelp) throws ServiceException{
		try {
			for (PhaInDetailHelp pdh : phaInDetailHelp) {
				//先判断该药品批次存在不存在    判断依据 ： 药房批次库存id + 部门id
				Medicine medicine = medicineService.warehouseIsHave(pdh.getPckcId(), pdh.getDeptId());
				if (medicine != null) {
					//批次已经存在  直接修改库存数量
					medicine.setMedicineName(medicine.getMedicineName().add(pdh.getPhaInNum()));
					//插入明细
					PhaInDetail phaInDetail = new PhaInDetail();
					PhaInDetailPK phaInDetailPK = new PhaInDetailPK();
					phaInDetailPK.setPhaInId(pdh.getPhaInId());
					phaInDetailPK.setMedicineId(medicine.getMedicineId());
					phaInDetail.setId(phaInDetailPK);
					phaInDetail.setPhaInNum(pdh.getPhaInNum());
					//插入明细
					phaInDetailsDao.save(phaInDetail);
					//修改出库单的状态
					OutstockDetailPK outstockDetailPK = new OutstockDetailPK();
					outstockDetailPK.setPckcId(pdh.getPckcId());
					outstockDetailPK.setCkId(pdh.getCkId());
					OutstockDetail outstockDetail = outstockDetailsDao.findById(outstockDetailPK).get();
					outstockDetail.setState("已入库");
				} else {
					//批次不存在  创建批次
					Medicine m = new Medicine();
					m.setMedicineId(UUID.randomUUID().toString().replace("-", ""));
					DrugWarehouse drugWarehouse = new DrugWarehouse();
					drugWarehouse.setPckcId(pdh.getPckcId());
					m.setDrugWarehouse(drugWarehouse);
					Dept dept = new Dept();
					dept.setDeptId(pdh.getDeptId());
					m.setDept(dept);
					m.setMedicineName(pdh.getPhaInNum());
					//插入明细
					PhaInDetail phaInDetail = new PhaInDetail();
					PhaInDetailPK phaInDetailPK = new PhaInDetailPK();
					phaInDetailPK.setPhaInId(pdh.getPhaInId());
					phaInDetailPK.setMedicineId(medicine.getMedicineId());
					phaInDetail.setId(phaInDetailPK);
					phaInDetail.setPhaInNum(pdh.getPhaInNum());
					//插入明细
					phaInDetailsDao.save(phaInDetail);
					//修改出库单的状态
					OutstockDetailPK outstockDetailPK = new OutstockDetailPK();
					outstockDetailPK.setPckcId(pdh.getPckcId());
					outstockDetailPK.setCkId(pdh.getCkId());
					OutstockDetail outstockDetail = outstockDetailsDao.findById(outstockDetailPK).get();
					outstockDetail.setState("已入库");
				}
			} 
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("药房入库失败");
		}
	}

}
