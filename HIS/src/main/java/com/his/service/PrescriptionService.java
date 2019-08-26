package com.his.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IEmpInformationDao;
import com.his.dao.IOutPreItemDao;
import com.his.dao.IOutPrePayDao;
import com.his.dao.IPrescriptionDao;
import com.his.pojo.EmpInformation;
import com.his.pojo.OutPrePay;
import com.his.pojo.Prescription;

/**  
* @ClassName: PrescriptionService  
* @Description: 门诊处方Service
* @author Sbaby
* @date 2019年8月22日  上午11:03:47
*    
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class PrescriptionService {

	@Autowired
	private IPrescriptionDao prescriptionDao;
	@Autowired
	private IOutPreItemDao outPreItemDao;
	@Autowired
	private IEmpInformationDao empInformationDao;
	@Autowired
	private IOutPrePayDao outPrePayDao;
	
	/**
	* @Title:addPrePay
	* @Description:添加处方缴费项
	* @param:@param prescriptionId
	* @return:void
	* @throws
	* @author:Sbaby
	* @Date:2019年8月26日 下午4:26:50
	 */
	public void addPrePay(String prescriptionId, String ygxh) {
		Prescription prescription = prescriptionDao.findById(prescriptionId).get();
		EmpInformation empInformation = empInformationDao.findById(ygxh).get();
		OutPrePay outPrePay = new OutPrePay();
		outPrePay.setOutPrePayId(UUID.randomUUID().toString().replaceAll("-", ""));
		outPrePay.setEmpInformation(empInformation);
		outPrePay.setOutPrePayTime(new Date());
		outPrePay.setPrescription(prescription);
		outPrePayDao.save(outPrePay);
		prescription.setOutPrePay(outPrePay);
		prescriptionDao.save(prescription);
	}
	
}
