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
	* @param:@param purchaseDetail
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月3日 下午11:56:57
	 */
	@ResponseBody
	@PostMapping("/add_pruchase_x")
	public JsonResult add_pruchase_x(@RequestBody Purchase purchase) {		
		JsonResult jsonResult = new JsonResult();
		try {
			purchaseService.addPurchaseX(purchase);
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}

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
	
	/**
	* @Title:get_all_purchase_for_no
	* @Description:查询所有未执行的采购计划
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年8月15日 上午11:44:56
	 */
	@ResponseBody
	@GetMapping("get_all_purchase_for_nodo")
	public JsonResult get_all_purchase_for_no() {
		JsonResult jsonResult = new JsonResult();
		try {
			jsonResult.setResult(purchaseService.getAllPurchaseForNoDo());
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}
	
	/**
	* @Title:get_all_purchase_for_No
	* @Description:分页查询一个未执行的采购计划
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年8月14日 上午10:06:17
	 */
	@ResponseBody
	@GetMapping("get_all_purchase_for_No")
	public JsonResult get_all_purchase_for_No(int pageSize,int pageNum){
		JsonResult jsonresult = new JsonResult();
		try {
			jsonresult.setResult(purchaseService.getAllPurchaseForNo(pageSize,pageNum));
			jsonresult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonresult.setStatus("error");
		}
		return jsonresult;
	}
	
	/**
	* @Title:get_all_purchase_for_No
	* @Description:分页查询一个已执行的采购计划
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年8月14日 上午10:06:17
	 */
	@ResponseBody
	@GetMapping("get_all_purchase_for_Yes")
	public JsonResult get_all_purchase_for_Yes(int pageSize,int pageNum){
		JsonResult jsonresult = new JsonResult();
		try {
			jsonresult.setResult(purchaseService.getAllPurchaseForYes(pageSize,pageNum));
			jsonresult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonresult.setStatus("error");
		}
		return jsonresult;
	}
	
	/**
	* @Title:del_by_purchase_and_detail
	* @Description:根据id删除一个采购计划及其明细
	* @param:@param cgid
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年8月14日 上午11:48:54
	 */
	@ResponseBody
	@GetMapping("del_by_purchase_and_detail")
	public JsonResult del_by_purchase_and_detail(String cgid) {
		JsonResult jsonResult = new JsonResult();
		try {
			purchaseService.delPurchaseByCascade(cgid);
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}
	
	/**
	* @Title:updata_state_by_id
	* @Description:修改状态
	* @param:@param cgid
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年8月14日 下午2:45:12
	 */
	@ResponseBody
	@GetMapping("updata_state_by_id")
	public JsonResult updata_state_by_id(String cgid) {
		JsonResult jsonResult = new JsonResult();
		try {
			purchaseService.updataForState(cgid);;
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}

}
