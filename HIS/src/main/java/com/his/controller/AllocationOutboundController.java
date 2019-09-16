package com.his.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.AllocationOutbound;
import com.his.pojo.JsonResult;
import com.his.service.AllocationOutboundService;

/**  
* @ClassName: AllocationOutboundController  
* @Description: 药房调拨（回库单） Controller
* @author crazy_long
* @date 2019年9月12日  下午4:29:58
*    
*/
@Controller
public class AllocationOutboundController {

	@Autowired
	private AllocationOutboundService allocationOutboundService;
	
	/**
	* @Title:finish_back_stock
	* @Description:完成回库
	* @param:@param alloId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月13日 下午10:20:20
	 */
	@ResponseBody
	@GetMapping("finish_back_stock")
	public JsonResult finish_back_stock(String alloId) {
		JsonResult result = new JsonResult();
		try {
			allocationOutboundService.finishBack(alloId);
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:get_passTime_submit
	* @Description:获取所有已提交的过期回库单
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月13日 下午1:59:23
	 */
	@ResponseBody
	@GetMapping("get_passTime_submit")
	public JsonResult get_passTime_submit() {
		JsonResult result = new JsonResult();
		try {
			result.setResult(allocationOutboundService.getForPassTimeSubmit());
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:get_no_passTime_submit
	* @Description:获取所有已提交的正常回库单
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月13日 下午1:58:39
	 */
	@ResponseBody
	@GetMapping("get_no_passTime_submit")
	public JsonResult get_no_passTime_submit() {
		JsonResult result = new JsonResult();
		try {
			result.setResult(allocationOutboundService.getForNoPassTimeSubmit());
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:get_for_no_passTime_submt
	* @Description:获取所有已提交的正常回库单的条数
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月13日 下午1:50:19
	 */
	@ResponseBody
	@GetMapping("get_for_no_passTime_submit_count")
	public JsonResult get_for_no_passTime_submit_count() {
		JsonResult result = new JsonResult();
		try {
			result.setResult(allocationOutboundService.getForNoPassTimeSubmitCount());
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:get_for_no_passTime_submit_count
	* @Description:获取所有已提交的过期的条数
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月13日 下午1:52:10
	 */
	@ResponseBody
	@GetMapping("get_for_passTime_submit_count")
	public JsonResult get_for_passTime_submit_count() {
		JsonResult result = new JsonResult();
		try {
			result.setResult(allocationOutboundService.getForPassTimeSubmitCount());
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:get_all_no_dl
	* @Description:获取所有未提交的回库单
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月12日 下午5:10:42
	 */
	@ResponseBody
	@GetMapping("get_all_no_do")
	public JsonResult get_all_no_do() {
		JsonResult result = new JsonResult();
		try {
			result.setResult(allocationOutboundService.getAllNoDo());
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:get_for_no_passTime
	* @Description:查找正常的回库单
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月12日 下午5:05:14
	 */
	@ResponseBody
	@GetMapping("get_for_no_passTime")
	public JsonResult get_for_no_passTime() {
		JsonResult result = new JsonResult();
		try {
			result.setResult(allocationOutboundService.getForNoPassTime());
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:get_for_passTime
	* @Description:查找过期的回库单
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月12日 下午5:06:01
	 */
	@ResponseBody
	@GetMapping("get_for_passTime")
	public JsonResult get_for_passTime() {
		JsonResult result = new JsonResult();
		try {
			result.setResult(allocationOutboundService.getForPassTime());
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:update_allocationOutbound_status
	* @Description:改变回库单状态
	* @param:@param alloId
	* @param:@param newStatus
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月12日 下午5:03:28
	 */
	@ResponseBody
	@GetMapping("update_allocationOutbound_status")
	public JsonResult update_allocationOutbound_status(String alloId,String newStatus) {
		JsonResult result = new JsonResult();
		try {
			allocationOutboundService.upadateStatus(alloId, newStatus);
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:add_a_allocationOutbound
	* @Description:创建回库单
	* @param:@param allocationOutbound
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月12日 下午4:33:54
	 */
	@ResponseBody
	@PostMapping("add_a_allocationOutbound")
	public JsonResult add_a_allocationOutbound(@RequestBody AllocationOutbound allocationOutbound) {
		JsonResult result = new JsonResult();
		try {
			allocationOutboundService.addAnAllocationOutbound(allocationOutbound);
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	
}
