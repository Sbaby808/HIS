package com.his.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.DrugScrapDetail;
import com.his.pojo.JsonResult;
import com.his.service.DrugScrapDetailsService;

/**  
* @ClassName: DrugScrapDetailController  
* @Description: 报废明细Controller
* @author crazy_long
* @date 2019年8月21日  下午4:54:47
*    
*/
@Controller
public class DrugScrapDetailController {
	
	@Autowired
	private DrugScrapDetailsService drugScrapDetailsService;
		
	@ResponseBody
	@GetMapping("query_ttt")
	public JsonResult query_ttt(String ygxh,String type,long firstDate,long lastDate,BigDecimal minMoney,BigDecimal maxMoney,int curPage,int pageSize){
		System.out.println("--------------------------------1111111111111111");
		JsonResult jsonResult = new JsonResult();
		
		return jsonResult;
	}
	
	
	/**
	* @Title:query_scrap_by_pgae
	* @Description:根据条件查找对应报废的药品
	* @param:@param ygxh
	* @param:@param type
	* @param:@param firstDate
	* @param:@param lastDate
	* @param:@param minMoney
	* @param:@param maxMoney
	* @param:@param curPage
	* @param:@param pageSize
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年8月23日 上午11:04:22
	 */
	@ResponseBody
	@GetMapping("query_scrap_by_pgae")
	public JsonResult query_scrap_by_pgae(String ygxh,String type,long firstDate,long lastDate,BigDecimal minMoney,BigDecimal maxMoney,int curPage,int pageSize){
		System.out.println("--------------------------------1111111111111111");
		JsonResult jsonResult = new JsonResult();
		try {
			Map map = drugScrapDetailsService.queryScrapDrugBypage(ygxh, type, firstDate, lastDate, minMoney, maxMoney, curPage,pageSize);
			jsonResult.setResult(map);
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}
	
	/**
	* @Title:add_drug_scrap_overdue
	* @Description:插入报废药品
	* @param:@param drugScrapDetail
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年8月21日 下午4:57:57
	 */
	@ResponseBody
	@PostMapping("add_drug_scrap")
	public JsonResult add_drug_scrap(@RequestBody List<DrugScrapDetail> drugScrapDetail) {
		JsonResult jsonResult = new JsonResult();
		try {
			drugScrapDetailsService.addDrugScrapDetailByBatch(drugScrapDetail);
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}
	
	/**
	* @Title:add_drug_scrap_overdue
	* @Description:插入过期药品
	* @param:@param drugScrapDetail
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年8月21日 下午4:57:57
	 */
	@ResponseBody
	@PostMapping("add_drug_scrap_overdue")
	public JsonResult add_drug_scrap_overdue(@RequestBody List<DrugScrapDetail> drugScrapDetail) {
		JsonResult jsonResult = new JsonResult();
		try {
			drugScrapDetailsService.addDrugOverdueDetailByBatch(drugScrapDetail);
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}

}
