package com.his.service;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IOutPreItemDao;
import com.his.dao.IPrescriptionDao;
import com.his.pojo.OutPreItem;
import com.his.pojo.OutPreItemPK;
import com.his.pojo.Prescription;

import oracle.net.aso.e;

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
	
	/**
	* @Title:addPrescription
	* @Description:创建处方单，添加处方明细
	* @param:@param prescription
	* @return:void
	* @throws
	* @author:Sbaby
	* @Date:2019年8月22日 上午11:08:25
	 */
	public void addPrescription(Prescription prescription) {
//		prescription.setPrescriptionId(UUID.randomUUID().toString().replaceAll("-", ""));
//		BigDecimal totalMoney = new BigDecimal(0);
//		for (OutPreItem	item : prescription.getOutPreItems()) {
//			totalMoney.add(item.getOutItemPay());
//			OutPreItemPK pk = new OutPreItemPK();
//		}
		
	}
	
}
