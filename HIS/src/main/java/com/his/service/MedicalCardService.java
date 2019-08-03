package com.his.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IMedicalCardDao;
import com.his.pojo.MedicalCard;
import com.his.utils.MD5Tools;
import com.his.utils.SimpleTools;

/**
 * 
* @ClassName: MedicalCardService  
* @Description: 就诊卡 
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
	
	/**
	* @Title:addMedicalCard
	* @Description:添加就诊卡
	* @param:@param medicalCard
	* @return:void
	* @throws
	* @author:Sbaby
	* @Date:2019年8月2日 上午9:47:19
	 */
	public void addMedicalCard(MedicalCard medicalCard) {
		medicalCard.setCardId(UUID.randomUUID().toString().replace("-", ""));
		medicalCard.setAge(new BigDecimal(SimpleTools.calAgeByBirthday(medicalCard.getBirthday())));
		medicalCard.setCardNum(new BigDecimal(0));
		medicalCard.setPasswd(MD5Tools.JM(MD5Tools.Md5(new Date().toString())));
		System.out.println(medicalCard.getLinkPerson());
		medicalCardDao.save(medicalCard);
	}
	
	/**
	* @Title:checkCardTimes
	* @Description:检查身份证号是否已办理就诊卡
	* @param:@param person_id
	* @param:@return
	* @return:boolean
	* @throws
	* @author:Sbaby
	* @Date:2019年8月2日 上午9:56:52
	 */
	public boolean checkCardTimes(String person_id) {
		Integer count = medicalCardDao.checkCardByPersonId(person_id);
		return count > 0 ? false : true;
	}
	
	public MedicalCard queryByPersonId(String person_id) {
		return medicalCardDao.queryByPersonId(person_id);
	}
	
}
