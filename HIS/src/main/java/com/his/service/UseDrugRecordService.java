package com.his.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.his.pojo.InjectionDetail;
import com.his.pojo.InjectionDetailPK;
import com.his.pojo.Medicine;
import com.his.pojo.Prescription;
import com.his.pojo.UseDrugRecord;
import com.his.utils.ServiceException;

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
	* @Title:finish_use_drug_record
	* @Description:完成用药明细记录 改状态 改库存
	* @param:@param injId
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年9月10日 上午9:32:53
	 */
	public void finishUseDrugRecord(String injId) {
		try {
			//获取明细
			List<InjectionDetail> injectionDetail = injectionDetailDao.getAllDetialByInjId(injId);
			for (InjectionDetail indel : injectionDetail) {
				//获取数量
				BigDecimal number = indel.getPsDrugNum();
				//药房id
				String medicineId = indel.getMedicine().getMedicineId();
				//查找药房
				Medicine medicine = medicineDao.findById(medicineId).get();
				//更改数量
				medicine.setMedicineName(medicine.getMedicineName().subtract(number));
			}
			//更改用药记录状态
			UseDrugRecord useDrugRecord = useDrugRecordDao.findById(injId).get();
			useDrugRecord.setState("已取药");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("完成用药明细记录失败");
		}
	}
	
	/**
	* @Title:addAnUseDrugRecord
	* @Description:添加一个用药详情
	* @param:@param u
	* @param:@throws ServiceException
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年9月9日 下午2:02:16
	 */
	public Map addAnUseDrugRecord(UseDrugRecord u) throws ServiceException{
		Map map = new HashMap();
		try {
			String prescriptionId = u.getPrescription().getPrescriptionId();
			//先判断记录是否存在
			int count1 = useDrugRecordDao.UseDrugRecordIsHave(prescriptionId);
			int count2 = useDrugRecordDao.UseDrugRecordIsAlready(prescriptionId);
			if(count1 > 0) {
				//已经存在但未完成的
				UseDrugRecord us = useDrugRecordDao.getUseDrugRecord(prescriptionId);
				map.put("injId", us.getInjId());
				map.put("flag", "isExitsforNo");
			}else if(count2 > 0) {
				//已经存在但已经完成的
				UseDrugRecord us = useDrugRecordDao.getUseDrugRecord(prescriptionId);
				map.put("injId", us.getInjId());
				map.put("flag", "isExitsYes");
			}else {
				//不存在
				String injId = UUID.randomUUID().toString().replace("-", "");
				u.setInjId(injId);
				//查找科室id
				String ygxh = u.getEmpInformation().getYgxh();
				//根据ygxh查找对应的科室id
				List<Object[]> department = departmentDao.queryByYgxh(ygxh);
				System.out.println("----------------------------------------");
				System.out.println(department.size());
				//取出门诊或急诊药房管理员的科室
				String departmentId = "";
				for (Object[] o : department) {
					String position = o[1].toString();
					if (position.equals("门诊药房管理员") || position.equals("急诊药房管理员")) {
						departmentId = o[0].toString();
						break;
					}
				}
				Department d = departmentDao.findById(departmentId).get();
				u.setDepartment(d);
				u.setInjTime(new Date());
				u.setState("未取药");
				useDrugRecordDao.save(u);
				map.put("injId", prescriptionId);
				map.put("flag", "createNew");
			}
			
		} catch (Exception e) {
			map.put("flag", false);
			map.put("injId", "erroe");
			e.printStackTrace();
			throw new ServiceException("添加一个用药记录失败");
		}
		return map;
	}
	
	
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
