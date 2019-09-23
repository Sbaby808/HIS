package com.his.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.DeptDAO;
import com.his.dao.IDrugWarehouseDao;
import com.his.dao.IMedicineDao;
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
import com.his.utils.SimpleTools;

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
	@Autowired 
	private IMedicineDao medicinedao;
	@Autowired
	private IDrugWarehouseDao drugWarehouseDao;
	@Autowired
	private DeptDAO deptDao;
	
	/**
	* @Title:getOneDrugAllPutStockByPage
	* @Description:获取某一个药品的所有入库记录
	* @param:@param searchContent
	* @param:@param deptId
	* @param:@param curPage
	* @param:@param pageSize
	* @param:@return
	* @return:Map
	* @throws
	* @author:crazy_long
	* @Date:2019年9月22日 下午5:44:46
	 */
	public Map getOneDrugAllPutStockByPage(String searchContent,String deptId,int curPage,int pageSize){
		Map map = new HashMap();
		List<PhaInDetail> list = phaInDetailsDao.getOneDrugAllPutStockByPage(
				searchContent.equals("")?SimpleTools.addCharForSearch(searchContent):searchContent,
				deptId, 
				PageRequest.of(curPage - 1, pageSize));
		int total = phaInDetailsDao.getOneDrugAllPutStockCount(
				searchContent.equals("")?SimpleTools.addCharForSearch(searchContent):searchContent,
				deptId);
		map.put("list", list);
		map.put("total", total);
		return map;
	}
	
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
					String medicineId = UUID.randomUUID().toString().replace("-", "");
					m.setMedicineId(medicineId);
					//维护药库id
					DrugWarehouse drugWarehouse = drugWarehouseDao.findById(pdh.getPckcId()).get();
					m.setDrugWarehouse(drugWarehouse);
					//维护deptId
					Dept dept = deptDao.findById(pdh.getDeptId()).get();
					m.setDept(dept);
					m.setMedicineName(pdh.getPhaInNum());
					medicinedao.save(m);
					//插入明细
					PhaInDetail phaInDetail = new PhaInDetail();
					PhaInDetailPK phaInDetailPK = new PhaInDetailPK();
					phaInDetailPK.setPhaInId(pdh.getPhaInId());
					phaInDetailPK.setMedicineId(medicineId);
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
