package com.his.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IOutpatientPayDao;
import com.his.pojo.OutpatientPay;

/**  
* @ClassName: OutpatientPayService  
* @Description: 门诊其他缴费记录Service
* @author Sbaby
* @date 2019年8月3日  上午10:05:40
*    
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class OutpatientPayService {

	@Autowired
	private IOutpatientPayDao outpatientPayDao;
	
	public void addOutPatientPay(OutpatientPay outpatientPay) {
		outpatientPay.setPayId(UUID.randomUUID().toString().replaceAll("-", ""));
		outpatientPayDao.save(outpatientPay);
	}
}
