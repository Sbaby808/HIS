package com.his.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IDepartmentDao;
import com.his.dao.IEmpInformationDao;
import com.his.dao.IInjectionDetailDao;
import com.his.dao.IMedicineDao;
import com.his.dao.IPrescriptionDao;
import com.his.dao.IUseDrugRecordDao;
import com.his.pojo.Department;
import com.his.pojo.EmpInformation;
import com.his.pojo.History;
import com.his.pojo.InjectionDetail;
import com.his.pojo.InjectionDetailPK;
import com.his.pojo.Medicine;
import com.his.pojo.Prescription;
import com.his.pojo.UseDrugRecord;

/**  
* @ClassName: UseDrugRecordService  
* @Description: 门诊用药记录Service
* @author Sbaby
* @date 2019年9月3日  下午4:30:35
*    
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class UseDrugRecordService {

	@Autowired
	private IUseDrugRecordDao useDrugRecordDao;
	@Autowired
	private IInjectionDetailDao injectionDetailDao;
	@Autowired
	private IPrescriptionDao prescriptionDao;
	@Autowired
	private IEmpInformationDao empInformationDao;
	@Autowired
	private IDepartmentDao departmentDao;
	@Autowired
	private IMedicineDao medicineDao;
	@Autowired
	private PrescriptionService prescriptionService;
	
	/**
	* @Title:addUseDrug
	* @Description:添加门诊用药记录
	* @param:@param preId
	* @param:@param empId
	* @param:@param ksId
	* @param:@param ypId
	* @param:@param ypNum
	* @param:@param ypUnit
	* @param:@param way
	* @return:void
	* @throws
	* @author:Sbaby
	* @Date:2019年9月3日 下午5:13:03
	 */
	public void addUseDrug(String preId, String empId, String ypId, BigDecimal ypNum, String ypUnit, String way) {
		Prescription prescription = prescriptionDao.findById(preId).get();
		EmpInformation empInformation = empInformationDao.findById(empId).get();
		Department department = prescription.getHistory().getOutpatientRegistration().getDepartment();
		UseDrugRecord useDrugRecord = null;
		if(prescription.getUseDrugRecord() == null) {
			// 初始化用药记录
			useDrugRecord = new UseDrugRecord();
			useDrugRecord.setDepartment(department);
			useDrugRecord.setEmpInformation(empInformation);
			useDrugRecord.setInjectionDetails(new ArrayList<InjectionDetail>());
			useDrugRecord.setInjId(UUID.randomUUID().toString().replaceAll("-", ""));
			useDrugRecord.setInjTime(new Date());
			useDrugRecord.setPrescription(prescription);
			useDrugRecordDao.save(useDrugRecord);
			prescription.setUseDrugRecord(useDrugRecord);
			prescriptionDao.save(prescription);
		} else {
			useDrugRecord = prescription.getUseDrugRecord();
		}
		Medicine medicine = medicineDao.checkMedicineNum(ypId, "outpatient");
		// 添加用药记录明细
		List<InjectionDetail> injectionDetails = useDrugRecord.getInjectionDetails();
		InjectionDetail injectionDetail = new InjectionDetail();
		InjectionDetailPK pk = new InjectionDetailPK();
		pk.setInjId(useDrugRecord.getInjId());
		pk.setMedicineId(medicine.getMedicineId());
		injectionDetail.setDrugTime(new Date());
		injectionDetail.setDrugWay(way);
		injectionDetail.setId(pk);
		injectionDetail.setMedicine(medicine);
		injectionDetail.setPsDrugNum(ypNum);
		injectionDetail.setPsDrugUnit(ypUnit);
		injectionDetail.setUseDrugRecord(useDrugRecord);
		injectionDetailDao.save(injectionDetail);
		injectionDetails.add(injectionDetail);
		useDrugRecordDao.save(useDrugRecord);
		// 修改用药数量
		prescriptionService.updateOutPreItem(prescription, ypId, ypNum.intValue());
	}
	
	/**
	* @Title:checkMedicineNum
	* @Description:检查门诊药房库存是否足够
	* @param:@param ypId
	* @param:@param num
	* @param:@return
	* @return:boolean
	* @throws
	* @author:Sbaby
	* @Date:2019年9月3日 下午9:34:51
	 */
	public boolean checkMedicineNum(String ypId, BigDecimal num) {
		Medicine medicine = medicineDao.checkMedicineNum(ypId, "outpatient");
		if(medicine == null) {
			return false;
		} else {
			return medicine.getMedicineName().compareTo(num) > 0 ? true : false;
		}
	}
	
}
