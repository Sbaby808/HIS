package com.his.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.JsonResult;
import com.his.pojo.OutstockDetail;
import com.his.service.OutstockDetailsService;

/**  
* @ClassName: OutstockDetailsController  
* @Description: 出库明细Controller
* @author crazy_long
* @date 2019年9月3日  上午9:45:03
*    
*/

@Controller
public class OutstockDetailsController {
	
	@Autowired
	private OutstockDetailsService outstockDetailsService;
	
	/**
	* @Title:update_detail_state
	* @Description:回库时修改明细单状态
	* @param:@param ckId
	* @param:@param pckcId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月6日 上午10:02:16
	 */
	@ResponseBody
	@GetMapping("update_detail_state")
	public JsonResult update_detail_state(String ckId,String pckcId) {		
		JsonResult jsonResult = new JsonResult();
		try {
			outstockDetailsService.updateDetailState(ckId, pckcId);
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}
		
	/**
	* @Title:get_OutstockDetail_By_ReqId
	* @Description:根据申领id查找对应的明细
	* @param:@param reqId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月5日 下午5:50:46
	 */
	@ResponseBody
	@GetMapping("get_OutstockDetail_By_ReqId")
	public JsonResult get_OutstockDetail_By_ReqId(String reqId) {		
		JsonResult jsonResult = new JsonResult();
		try {
			jsonResult.setResult(outstockDetailsService.getOutstockDetailByReqId(reqId));
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}
	
	/**
	* @Title:add_one_drug_outStockDetail
	* @Description:添加一个药品的出库明细
	* @param:@param outstockDetail
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月3日 上午9:50:37
	 */
	@ResponseBody
	@PostMapping("add_one_drug_outStockDetail")
	public JsonResult add_one_drug_outStockDetail(@RequestBody List<OutstockDetail> outstockDetail) {		
		JsonResult jsonResult = new JsonResult();
		try {
			outstockDetailsService.addOutStockByOneDrug(outstockDetail);
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}

}
