package com.his.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.MedicalCard;
import com.his.service.MedicalCardService;


/**
 * 
* @ClassName: MedicalCardController  
* @Description: TODO就诊卡 
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
}
