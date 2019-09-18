package com.his.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.JsonResult;
import com.his.service.OutPreItemService;

/**  
* @ClassName: OutPreItemController  
* @Description: 处方明细controller
* @author crazy_long
* @date 2019年9月8日  下午11:54:10
*    
*/
@Controller
public class OutPreItemController {
	
	@Autowired
	private OutPreItemService outPreItemService;
	
	/**
	* @Title:get_prescription_detail_to_back
	* @Description:根据处方id查找对应的明细
	* @param:@param prescriptionId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月10日 下午4:51:26
	 */
	@ResponseBody
	@GetMapping("get_prescription_detail_to_back")
	public JsonResult get_prescription_detail_to_back(String prescriptionId) {
		JsonResult jsonresult = new JsonResult();
		try {
			jsonresult.setResult(outPreItemService.getPreDetailByPreID(prescriptionId));
			jsonresult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonresult.setStatus("error");
		}
		return jsonresult;
	}
	
	/**
	* @Title:query_outPre_item_by_preId
	* @Description:根据处方id查找对应的明细
	* @param:@param prescriptionId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月8日 下午11:59:26
	 */
	@ResponseBody
	@GetMapping("query_outPre_item_by_preId")
	public JsonResult query_outPre_item_by_preId(String prescriptionId) {
		JsonResult jsonresult = new JsonResult();
		try {
			jsonresult.setResult(outPreItemService.queryOutPreItemByPreId(prescriptionId));
			jsonresult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonresult.setStatus("error");
		}
		return jsonresult;
	}
	
}
