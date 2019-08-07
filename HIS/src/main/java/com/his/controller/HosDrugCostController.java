package com.his.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.HosDrugCost;
import com.his.service.HosDrugCostService;


/**
 * 
* @ClassName: HosDrugCostController  
* @Description: 住院药品扣费记录  
* @author Hamster
* @date 2019年8月7日  下午7:50:34
*
 */
@Controller
public class HosDrugCostController {

	@Autowired
	private HosDrugCostService hosDrugCostService;
	
	@ResponseBody
	@GetMapping("/get_hos_drug_cost")
	public List <HosDrugCost> getHosDrugCost(){
		return hosDrugCostService.getHosDrugCost();
	}
}
