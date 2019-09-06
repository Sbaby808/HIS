package com.his.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.JsonResult;
import com.his.pojo.ReqDetail;
import com.his.service.ReqDetailsService;

/**  
* @ClassName: ReqDetailsController  
* @Description: 药房药品申领明细controller
* @author crazy_long
* @date 2019年8月28日  下午9:58:17
*    
*/
@Controller
public class ReqDetailsController {
	
	@Autowired
	private ReqDetailsService reqDetailsService;
	
	/**
	* @Title:is_have_no_do
	* @Description:查看是否有没有未处理的药品
	* @param:@param reqId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月3日 下午3:54:10
	 */
	@ResponseBody
	@GetMapping("is_have_no_do")
	public JsonResult is_have_no_do(String reqId) {
		JsonResult jsonResult = new JsonResult();
		try {
			jsonResult.setResult(reqDetailsService.IsHaveNoDo(reqId));
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}
	
	/**
	* @Title:get_requestDetail_by_reqId
	* @Description:根据申领单号查找对应的明细
	* @param:@param reqId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年8月29日 下午7:44:56
	 */
	@ResponseBody
	@GetMapping("get_requestDetail_by_reqId")
	public JsonResult get_requestDetail_by_reqId(String reqId) {
		JsonResult jsonResult = new JsonResult();
		try {
			jsonResult.setResult(reqDetailsService.qeuryRequestDetail(reqId));
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}
	
	/**
	* @Title:get_no_request_drug
	* @Description:查找未申领的药品
	* @param:@param curPage
	* @param:@param pageSize
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年8月29日 上午12:24:56
	 */
	@ResponseBody
	@PostMapping("get_no_request_drug")
	public JsonResult get_no_request_drug(int curPage,int pageSize) {
		JsonResult jsonResult = new JsonResult();
		try {
			reqDetailsService.qeuryForNoRequest(curPage, pageSize);
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}
	
	/**
	* @Title:get_no_request_drug
	* @Description:查找已申领的药品
	* @param:@param curPage
	* @param:@param pageSize
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年8月29日 上午12:24:56
	 */
	@ResponseBody
	@PostMapping("get_yes_request_drug")
	public JsonResult get_yes_request_drug(int curPage,int pageSize) {
		JsonResult jsonResult = new JsonResult();
		try {
			reqDetailsService.qeuryForYesRequest(curPage, pageSize);
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}
	
	/**
	* @Title:add_reqDetails_by_batch
	* @Description:添加药房药品申领单和申领明细
	* @param:@param reqDetails
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年8月28日 下午10:01:22
	 */
	@ResponseBody
	@PostMapping("add_reqDetails_by_batch")
	public JsonResult add_reqDetails_by_batch(@RequestBody List<ReqDetail> reqDetails) {
		JsonResult jsonResult = new JsonResult();
		try {
			reqDetailsService.addRequestMedicine(reqDetails);
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}

}
