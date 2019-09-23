package com.his.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.JsonResult;
import com.his.service.PurchaseChecService;

/**  
* @ClassName: PurchaseCheckController  
* @Description: 采购验收controller
* @author crazy_long
* @date 2019年8月17日  上午11:29:28
*    
*/
@Controller
public class PurchaseCheckController {
	
	@Autowired
	private PurchaseChecService purchaseChecService;

	/**
	* @Title:get_allPurchaseCheck_By_State
	* @Description:获取某一状态的采购验收
	* @param:@param state
	* @param:@param curPage
	* @param:@param pageSize
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月21日 上午1:05:43
	 */
	@ResponseBody
	@GetMapping("get_allPurchaseCheck_by_state")
	public JsonResult get_allPurchaseCheck_By_State(String state,int pageSize,int curPage) {
		JsonResult jsonResult = new JsonResult();
		try {
			jsonResult.setResult(purchaseChecService.getAllPurchaseCheckByState(state, curPage, pageSize));
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}
	
	/**
	* @Title:get_all_purcheck_noPut
	* @Description:获取能入库的验收
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年8月17日 上午11:34:14
	 */
	@ResponseBody
	@GetMapping("get_all_purcheck_noPut")
	public JsonResult get_all_purcheck_noPut() {
		JsonResult jsonResult = new JsonResult();
		try {
			jsonResult.setResult(purchaseChecService.getAllNoPut());
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}
	
	/**
	* @Title:update_purcheck_state_by_id
	* @Description:根据验收id改变验收状态
	* @param:@param checkid
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年8月19日 上午8:48:54
	 */
	@ResponseBody
	@GetMapping("update_purcheck_state_by_id")
	public JsonResult update_purcheck_state_by_id(String checkid) {
		JsonResult jsonResult = new JsonResult();
		try {
			purchaseChecService.updatePurchaseCheckState(checkid);
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			jsonResult.setStatus("error");
			e.printStackTrace();
		}
		return jsonResult;
	}
}
