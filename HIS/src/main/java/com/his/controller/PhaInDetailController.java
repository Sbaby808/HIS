package com.his.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.JsonResult;
import com.his.pojo.PhaInDetailHelp;
import com.his.service.PhaInDetailsService;

/**  
* @ClassName: PhaInDetailController  
* @Description: 药房入库明细Controller
* @author crazy_long
* @date 2019年9月5日  下午11:50:05
*    
*/
@Controller
public class PhaInDetailController {
	
	@Autowired
	private PhaInDetailsService phaInDetailsService;
	
	/**
	* @Title:get_oneDrug_AllPutStock_byPage
	* @Description:获取某一个药品的所有入库记录
	* @param:@param searchContent
	* @param:@param deptId
	* @param:@param curPage
	* @param:@param pageSize
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月22日 下午5:47:40
	 */
	@ResponseBody
	@GetMapping("get_oneDrug_AllPutStock_byPage")
	public JsonResult get_oneDrug_AllPutStock_byPage(String searchContent,String deptId,int curPage,int pageSize) {
		JsonResult jsonResult = new JsonResult();
		try {
			jsonResult.setResult(phaInDetailsService.getOneDrugAllPutStockByPage(searchContent, deptId, curPage, pageSize));
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}
	
	
	/**
	* @Title:add_phaInDetail_by_batch
	* @Description:批量添加药房入库明细
	* @param:@param phaInDetailHelp
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月6日 上午1:19:05
	 */
	@ResponseBody
	@PostMapping("add_phaInDetail_by_batch")
	public JsonResult add_phaInDetail_by_batch(@RequestBody List<PhaInDetailHelp> phaInDetailHelp) {
		JsonResult jsonResult = new JsonResult();
		try {
			phaInDetailsService.addPhaInDetailByBatch(phaInDetailHelp);
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}

}
