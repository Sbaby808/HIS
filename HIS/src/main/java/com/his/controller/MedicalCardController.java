package com.his.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.JsonResult;
import com.his.pojo.MedicalCard;
import com.his.service.MedicalCardService;


/**
 * 
* @ClassName: MedicalCardController  
* @Description: 就诊卡 
* @author Hamster
* @date 2019年8月1日  下午8:39:53
*
 */
@Controller
public class MedicalCardController {

	@Autowired
	private MedicalCardService medicalCardService;
	
	@ResponseBody
	@GetMapping("/get_medical_card_by_Cid")
	public MedicalCard getCardByCid(String cardId){
		return medicalCardService.getCardByCid(cardId);
	}
	
	/**
	* @Title:addCard
	* @Description:添加就诊卡信息
	* @param:@param medicalCard
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月2日 上午9:43:06
	 */
	@PostMapping("/add_medical_card")
	@ResponseBody
	public JsonResult addCard(@RequestBody MedicalCard medicalCard) {
		JsonResult result = new JsonResult();
		try {
			medicalCardService.addMedicalCard(medicalCard);
			result.setResult(medicalCard);
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(medicalCard);
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	 * 
	* @Title:checkCardTimes
	* @Description:检查身份证号是否已办理就诊卡
	* @param:@param person_id
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月2日 上午9:55:40
	 */
	@GetMapping("/check_person_card")
	@ResponseBody
	public JsonResult checkCardTimes(String person_id) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(medicalCardService.checkCardTimes(person_id));
			result.setStatus("ok");
		} catch (Exception e) {
			result.setResult(person_id);
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:getCardByPersonId
	* @Description:根据身份证号查询就诊卡
	* @param:@param person_id
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月3日 上午8:41:05
	 */
	@GetMapping("/get_card_by_person_id")
	@ResponseBody
	public JsonResult getCardByPersonId(String person_id) {
		JsonResult result = new JsonResult();
		try {
			MedicalCard card = medicalCardService.queryByPersonId(person_id);
			result.setResult(card);
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(person_id);
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:getCardQrCode
	* @Description:获取办理就诊卡支付二维码
	* @param:@param ygxh
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月3日 上午8:44:57
	 */
	@GetMapping("get_card_qr_code")
	@ResponseBody
	public JsonResult getCardQrCode() {
		JsonResult result = new JsonResult();
		try {
			Map<String, String> res = medicalCardService.getCardQrCode();
			result.setResult(res);
			result.setStatus("ok");
		} catch (Exception e) {
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:CheckPay
	* @Description:查询订单是否支付
	* @param:@param outTradeNo
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月3日 上午9:57:30
	 */
	@GetMapping("/check_pay")
	@ResponseBody
	public JsonResult CheckPay(String outTradeNo, String ygxh, String personId) {
		JsonResult result = new JsonResult();
		try {
			boolean flag = medicalCardService.checkPay(outTradeNo, ygxh, personId);
			result.setResult(flag);
			result.setStatus("ok");
		} catch (Exception e) {
			result.setResult(outTradeNo);
			result.setStatus("error");
		}
		return result;
	}
	
	
}
