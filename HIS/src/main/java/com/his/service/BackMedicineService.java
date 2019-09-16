package com.his.service;

import java.math.BigDecimal;
import java.rmi.ServerException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IBackDetailsDao;
import com.his.dao.IBackMedicineDao;
import com.his.dao.IEmpInformationDao;
import com.his.dao.IInjectionDetailDao;
import com.his.dao.IMedicineDao;
import com.his.dao.IPrescriptionDao;
import com.his.dao.IUseDrugRecordDao;
import com.his.pojo.BackDetail;
import com.his.pojo.BackDetailPK;
import com.his.pojo.BackMedicine;
import com.his.pojo.EmpInformation;
import com.his.pojo.InjectionDetail;
import com.his.pojo.Medicine;
import com.his.pojo.Prescription;
import com.his.pojo.UseDrugRecord;
import com.his.utils.ServiceException;

/**  
* @ClassName: BackMedicineService  
* @Description: 退药service
* @author crazy_long
* @date 2019年7月30日  下午12:00:03
*    
*/
@Service
@Transactional(rollbackFor=Exception.class)
public class BackMedicineService {
	
	@Autowired
	private IBackMedicineDao backMedicineDao;
	@Autowired
	private IPrescriptionDao prescriptionDao;
	@Autowired
	private IBackDetailsDao backDetailsDao;
	@Autowired
	private IEmpInformationDao empInformationDao;
	@Autowired
	private IInjectionDetailDao injectionDetailDao;
	@Autowired
	private IMedicineDao medicineDao;
	@Autowired
	private IUseDrugRecordDao useDrugRecordDao;
	
	/**
	* @Title:patientBackDrug
	* @Description:患者退药
	* @param:@param backMedicine
	* @param:@throws ServerException
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年9月11日 下午3:56:06
	 */
	public void patientBackDrug(BackMedicine backMedicine) throws ServerException{
		try {
			//处方单编号
			String prescriptionId = backMedicine.getPrescription().getPrescriptionId();
			//创建退药单
			String backId = UUID.randomUUID().toString().replace("-", "");
			BackMedicine bm = new BackMedicine();
			bm.setBackId(backId);
			Prescription pre = prescriptionDao.findById(prescriptionId).get();
			bm.setPrescription(pre);
			EmpInformation emp = empInformationDao.findById(backMedicine.getEmpInformation().getYgxh()).get();
			bm.setEmpInformation(emp);
			bm.setBackTime(new Date());
			bm.setBackReason(backMedicine.getBackReason());
			backMedicineDao.save(bm);
			//找出领取的药品明细
			List<InjectionDetail> list = injectionDetailDao.queryBackItemByPreId(prescriptionId);
			//循环对明细退药
			for (InjectionDetail injectionDetail : list) {
				//药品库存编号
				String medicineId = injectionDetail.getMedicine().getMedicineId();
				//去要数量
				BigDecimal number = injectionDetail.getPsDrugNum();
				//插入退药明细
				BackDetail backDetail = new BackDetail();
				BackDetailPK backDetailPK = new BackDetailPK();
				backDetailPK.setBackId(backId);
				backDetailPK.setMedicineId(medicineId);
				backDetail.setId(backDetailPK);
				backDetail.setBackNum(number);
				backDetailsDao.save(backDetail);
				//修改库存数量
				Medicine medicine = medicineDao.findById(medicineId).get();
				medicine.setMedicineName(medicine.getMedicineName().add(number));
			}
			//修改用药记录状态为 已退药
			UseDrugRecord us = useDrugRecordDao.getUseDrugRecord(prescriptionId);
			us.setState("已退药");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("退药失败");
		}
	}

}
