package com.his.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
