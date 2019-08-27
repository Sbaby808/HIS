package com.his.controller;

import java.util.List;
import java.util.Map;

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
	
	/**
	 * 
	* @Title:getHosOtherCost
	* @Description:无分页查询其他扣费项
	* @param:@return
	* @return:List<HosOtherCost>
	* @throws
	* @author:Hamster
	* @Date:2019年8月20日 上午9:37:05
	 */
	@ResponseBody
	@GetMapping("/get_hos_other_cost")
	public List <HosOtherCost> getHosOtherCost(){
		return hosOtherCostService.getHosOtherCost();
	}
	
	@ResponseBody
	@GetMapping("/get_hos_other_cost_byPage")
	public Map getHosOtherCostbyPage(String text2,int curpage,int pagesize){
		String cardName = "%"+text2+"%";
		return hosOtherCostService.getAllOtherCostByPage(cardName, curpage, pagesize);
	}
	
	
	/**
	 * 
	* @Title:getOtherCostbyRid
	* @Description:根据病案号查询其他收费项
	* @param:@param outNotice
	* @param:@return
	* @return:List<HosOtherCost>
	* @throws
	* @author:Hamster
	* @Date:2019年8月20日 上午9:36:47
	 */
	@ResponseBody
	@PostMapping("/get_other_cost_byRid")
	public List <HosOtherCost> getOtherCostbyRid(@RequestBody HosOutNotice outNotice){
		return hosOtherCostService.getAllHosOtherCosts(outNotice);
	}
	
}
