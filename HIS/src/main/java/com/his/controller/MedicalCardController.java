package com.his.controller;

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
	
	/**
	 * 
	* @Title:getCardByCid
	* @Description:根据就诊卡id获取就诊卡信息
	* @param:@param cardId
	* @param:@return
	* @return:MedicalCard
	* @throws
	* @author:Hamster
	* @Date:2019年8月3日 上午10:37:43
	 */
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
}
