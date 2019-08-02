package com.his.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IMedicalCardDao;
import com.his.pojo.MedicalCard;

/**
 * 
* @ClassName: MedicalCardService  
* @Description: TODO就诊卡 
* @author Hamster
* @date 2019年8月1日  下午8:37:13
*
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class MedicalCardService {
	
	@Autowired
	private IMedicalCardDao medicalCardDao;
	
	public MedicalCard getCardByCid(String cardId){
		return medicalCardDao.getCardByCid(cardId);
	}
	
}
