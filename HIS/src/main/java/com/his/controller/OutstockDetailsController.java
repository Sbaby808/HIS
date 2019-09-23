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
	* @Title:get_all_outStock_detail
	* @Description:分页查找出库单对应的明细
	* @param:@param ckId
	* @param:@param curPage
	* @param:@param pageSize
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月21日 下午1:13:35
	 */
	@ResponseBody
	@GetMapping("get_all_outStock_detail_byPage")
	public JsonResult get_all_outStock_detail_byPage(String ckId,int curPage,int pageSize) {		
		JsonResult jsonResult = new JsonResult();
		try {
			jsonResult.setResult(outstockDetailsService.getAllDetailByPage(ckId, curPage, pageSize));
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}
	
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
	* @Title:is_have_outStockDetail
	* @Description:判断是否还有出库明细没有处理完成
	* @param:@param reqId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月22日 下午2:53:20
	 */
	@ResponseBody
	@GetMapping("is_have_outStockDetail")
	public JsonResult is_have_outStockDetail(String reqId) {		
		JsonResult jsonResult = new JsonResult();
		try {
			jsonResult.setResult(outstockDetailsService.isHaveOutStockDetail(reqId));
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
	
	/**
	* @Title:add_one_drug_outStockDetail
	* @Description:忽略一个出库明细  修改申领明细状态为：无药品
	* @param:@param outstockDetail
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月21日 下午12:13:45
	 */
	@ResponseBody
	@GetMapping("no_drug_to_outStock")
	public JsonResult no_drug_to_outStock(String reqId,String ypId) {		
		JsonResult jsonResult = new JsonResult();
		try {
			outstockDetailsService.noDrugToOutStock(reqId, ypId);
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}

}
