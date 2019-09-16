package com.his.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IDrugWarehouseDao;
import com.his.dao.IEmpInformationDao;
import com.his.dao.IPsBackDetailsDao;
import com.his.dao.IPutstockBackDao;
import com.his.dao.ISupplierDao;
import com.his.pojo.DrugWarehouse;
import com.his.pojo.EmpInformation;
import com.his.pojo.PsBackDetail;
import com.his.pojo.PsBackDetailPK;
import com.his.pojo.PutstockBack;
import com.his.pojo.Supplier;
import com.his.utils.ServiceException;

/**  
* @ClassName: PsBackDetailsService  
* @Description: 药库退药明细service
* @author crazy_long
* @date 2019年7月30日  下午12:19:12
*    
*/

@Service
@Transactional(rollbackFor=Exception.class)
public class PsBackDetailsService {
	
	@Autowired
	private IPutstockBackDao putstockBackDao;
	@Autowired
	private IDrugWarehouseDao drugWarehouseDao;
	@Autowired
	private IPsBackDetailsDao psBackDetailsDao;
	@Autowired
	private IEmpInformationDao empInformationDao;
	@Autowired
	private ISupplierDao supplierDao;
	
	
	/**
	* @Title:addDrugBackAndDetail
	* @Description:添加退药信息和退药明细
	* @param:@param psBackDetail
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年8月25日 下午11:17:34
	 */
	public void addDrugBackAndDetail(List<PsBackDetail> psBackDetail) throws ServiceException{
		System.out.println("-------------------------------");
		System.out.println(psBackDetail.size());
		System.out.println(psBackDetail.get(0).getPutstockBack().getPsBackReason());
		try {
			//先维护退药信息
			PutstockBack putStockBack = psBackDetail.get(0).getPutstockBack();
			String ygxh = putStockBack.getEmpInformation().getYgxh();
			String supplierId = putStockBack.getSupplier1().getGysId();
			EmpInformation emp = empInformationDao.findById(ygxh).get();
			Supplier supplier = supplierDao.findById(supplierId).get();
			String psBackId = UUID.randomUUID().toString().replace("-", "");
			
			PutstockBack put = new PutstockBack();
			put.setPsBackId(psBackId);
			put.setPsBackReason(putStockBack.getPsBackReason());
			put.setEmpInformation(emp);
			put.setSupplier1(supplier);
			put.setPsBackTime(new Date());
			putstockBackDao.save(put);
			//循环插入明细 数据从第二个开始
			for(int i=1;i<psBackDetail.size();i++) {
				PsBackDetail pbd = psBackDetail.get(i);
				//维护主键
				PsBackDetailPK psBackDetailPK = new PsBackDetailPK();
				psBackDetailPK.setPsBackId(psBackId);
				psBackDetailPK.setPckcId(pbd.getDrugWarehouse().getPckcId());
				pbd.setId(psBackDetailPK);
				psBackDetailsDao.save(pbd);
				//更改库存
				DrugWarehouse drugWarehouse = drugWarehouseDao.findById(pbd.getDrugWarehouse().getPckcId()).get();
				BigDecimal NowNumber = drugWarehouse.getNowNumber().subtract(pbd.getPsDrugNum());
				drugWarehouse.setNowNumber(NowNumber);
				drugWarehouseDao.save(drugWarehouse);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("添加退药信息和明细失败");
		}
	}

}
