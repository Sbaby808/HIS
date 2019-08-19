package com.his.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.HosOtherCost;
import com.his.pojo.HosOutNotice;
import com.his.service.HosOtherCostService;

/**
 * 
* @ClassName: HosOtherCostController  
* @Description: 住院其他扣费记录 
* @author Hamster
* @date 2019年8月17日  上午11:06:18
*
 */

@Controller
public class HosOtherCostController {

	@Autowired
	private HosOtherCostService hosOtherCostService;
	
	@ResponseBody
	@GetMapping("/get_hos_other_cost")
	public List <HosOtherCost> getHosOtherCost(){
		return hosOtherCostService.getHosOtherCost();
	}
	
	@ResponseBody
	@PostMapping("/get_other_cost_byRid")
	public List <HosOtherCost> getOtherCostbyRid(@RequestBody HosOutNotice outNotice){
		return hosOtherCostService.getAllHosOtherCosts(outNotice);
	}
	
}
