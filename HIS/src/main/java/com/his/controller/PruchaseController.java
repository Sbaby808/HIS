package com.his.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.JsonResult;
import com.his.pojo.Purchase;
import com.his.pojo.PurchaseDetail;
import com.his.service.PurchaseChecService;
import com.his.service.PurchaseService;
import com.his.utils.Result;

import oracle.jdbc.proxy.annotation.Post;

/**  
* @ClassName: PruchaseController  
* @Description: 采购计划Controller
* @author crazy_long
* @date 2019年8月1日  下午4:36:02
*    
*/
@Controller
public class PruchaseController {
	
	@Autowired
	private PurchaseService purchaseService;
	

	/**
	* @Title:add_pruchase
	* @Description:插入一个采购计划
	* @param:@param list
	* @param:@return
	* @return:Result
	* @throws
	* @author:crazy_long
	* @Date:2019年8月13日 下午3:28:33
	 */
	@ResponseBody
	@PostMapping("/add_pruchase")
	public JsonResult add_pruchase(@RequestBody List<PurchaseDetail> purchaseDetail) {		
		System.out.println("------------------------------");
		JsonResult jsonResult = new JsonResult();
		try {
			purchaseService.addPurchaseAndpurchaseDetail(purchaseDetail);
			jsonResult.setResult(purchaseDetail);
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setResult(purchaseDetail);
			jsonResult.setStatus("error");
		}
		return jsonResult;
	
	}

}
