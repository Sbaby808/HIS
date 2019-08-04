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

import com.alipay.demo.trade.model.GoodsDetail;
import com.his.dao.IMedicalCardDao;
import com.his.pojo.AliPayEntity;
import com.his.pojo.MedicalCard;
import com.his.pojo.OtherProject;
import com.his.pojo.OutpatientPay;
import com.his.utils.AliPay;
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
	@Autowired
	private OtherProjectService otherProjectService;
	@Autowired
	private OutpatientPayService outpatientPayService;
	@Autowired
	private EmpInformationService empInformationService;
	
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
	
	/**
	* @Title:queryByPersonId
	* @Description:根据身份证号查询就诊卡
	* @param:@param person_id
	* @param:@return
	* @return:MedicalCard
	* @throws
	* @author:Sbaby
	* @Date:2019年8月3日 上午9:31:51
	 */
	public MedicalCard queryByPersonId(String person_id) {
		return medicalCardDao.queryByPersonId(person_id);
	}
	
	/**
	* @Title:getCardQrCode
	* @Description:获取就诊卡支付二维码
	* @param:@return
	* @return:Map
	* @throws
	* @author:Sbaby
	* @Date:2019年8月3日 上午9:33:07
	 */
	public Map<String, String> getCardQrCode() {
		Map<String, String> res = new HashMap<>();
		AliPayEntity payEntity = new AliPayEntity();
		payEntity.setOutTradeNo(UUID.randomUUID().toString().replace("-", ""));
		OtherProject cardProject = otherProjectService.getOtherPeojectById("879155e950b34ffba2cff01b2895c024");
		payEntity.setSubject(cardProject.getProjectName());
		payEntity.setTotalAmount(cardProject.getProjectPrice() + "");
		payEntity.setBody(cardProject.getProjectDesc());
		List<GoodsDetail> goods = new ArrayList<>();
		GoodsDetail good = GoodsDetail.newInstance(cardProject.getProjectId(), cardProject.getProjectName(), Long.parseLong(cardProject.getProjectPrice().toString()), 1);
		goods.add(good);
		res.put("code", AliPay.pay(payEntity, goods));
		res.put("outTradeNo", payEntity.getOutTradeNo());
		return res;
	}
	
	/**
	* @Title:checkPay
	* @Description:查询订单是否支付
	* @param:@param outTradeNo
	* @param:@return
	* @return:boolean
	* @throws
	* @author:Sbaby
	* @Date:2019年8月3日 上午9:58:37
	 */
	public boolean checkPay(String outTradeNo, String ygxh, String personId) {
		boolean flag = false;
		flag =  AliPay.query(outTradeNo);
		if(flag) {
			// 插入缴费记录
			OutpatientPay pay = new OutpatientPay();
			pay.setEmpInformation(empInformationService.getEmpInfoById(ygxh));
			pay.setPayAmount(new BigDecimal("5.0"));
			pay.setPayType("支付宝");
			pay.setActStatus("已缴费");
			pay.setOutPayTime(new Date());
			outpatientPayService.addOutPatientPay(pay);
			// 修改就诊卡的部分信息（办卡次数，校验值）
			MedicalCard card = queryByPersonId(personId);
			card.setCardNum(card.getCardNum().add(new BigDecimal(1)));
			card.setPasswd(MD5Tools.JM(MD5Tools.Md5(new Date().toString())));
			medicalCardDao.save(card);
		}
		return flag;
	}
	
	/**
	* @Title:addMoneyPay
	* @Description:添加现金缴费补办就诊卡记录
	* @param:@param ygxh
	* @param:@param personId
	* @return:void
	* @throws
	* @author:Sbaby
	* @Date:2019年8月3日 上午11:59:10
	 */
	public void addMoneyPay(String ygxh, String personId) {
		// 插入缴费记录
		OutpatientPay pay = new OutpatientPay();
		pay.setEmpInformation(empInformationService.getEmpInfoById(ygxh));
		pay.setPayAmount(new BigDecimal("5.0"));
		pay.setPayType("现金");
		pay.setActStatus("已缴费");
		pay.setOutPayTime(new Date());
		outpatientPayService.addOutPatientPay(pay);
		// 修改就诊卡的部分信息（办卡次数，校验值）
		MedicalCard card = queryByPersonId(personId);
		card.setCardNum(card.getCardNum().add(new BigDecimal(1)));
		card.setPasswd(MD5Tools.JM(MD5Tools.Md5(new Date().toString())));
		medicalCardDao.save(card);
	}
	
	
	
}
