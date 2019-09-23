package com.his.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.JsonResult;
import com.his.pojo.PurCheDetail;
import com.his.service.PurCheDetailsService;

/**  
* @ClassName: PurCheDetailController  
* @Description:采购验收明细controller
* @author crazy_long
* @date 2019年8月16日  下午1:28:24
*    
*/
@Controller
public class PurCheDetailController {
	
	@Autowired
	private PurCheDetailsService purCheDetailsService;
	
	/**
	* @Title:get_an_CheckAndDetial_by_purchaId
	* @Description:分页获取采购验收和明细
	* @param:@param checkId
	* @param:@param curPage
	* @param:@param pageSize
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月21日 上午1:24:02
	 */
	@ResponseBody
	@GetMapping("get_an_CheckAndDetial_by_purchaId")
	public JsonResult get_an_CheckAndDetial_by_purchaId(String checkId,int curPage,int pageSize) {
		JsonResult jsonReult = new JsonResult();
		try {
			Map map = purCheDetailsService.getAnCheckAndDetialByPurchaId(checkId,curPage, pageSize);
			jsonReult.setResult(map);
			jsonReult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonReult.setStatus("error");
		}
		return jsonReult;
	}
	
	/**
	* @Title:get_onePurcheckDetail_by_checkId
	* @Description:根据id分页获取未入库的采购验收及其明细
	* @param:@param checkId
	* @param:@param curPage
	* @param:@param pageSize
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年8月17日 下午12:02:11
	 */
	@ResponseBody
	@GetMapping("get_onePurcheckDetail_by_checkId")
	public JsonResult get_onePurcheckDetail_by_checkId(int curPage,int pageSize,String checkId) {
		JsonResult jsonReult = new JsonResult();
		try {
			Map map = purCheDetailsService.getOnePurchaseCheckNoputById(curPage, pageSize, checkId);
			jsonReult.setResult(map);
			jsonReult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonReult.setStatus("error");
		}
		return jsonReult;
	}
	
	/**
	* @Title:add_purcheck_Detail
	* @Description:批量添加采购验收明细
	* @param:@param checkinfo
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年8月16日 下午1:33:48
	 */
	@ResponseBody
	@PostMapping("add_purcheck_Detail")
	public JsonResult add_purcheck_Detail(@RequestBody List<PurCheDetail> checkinfo) {
		JsonResult jsonResult = new JsonResult();
		try {
			purCheDetailsService.addPurcheDetail(checkinfo);
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}

}
