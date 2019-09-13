package com.his.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.JsonResult;
import com.his.service.OutpatientRequestionMedicineService;

/**  
* @ClassName: OutpatientRequestionController  
* @Description: 药品申领controller
* @author crazy_long
* @date 2019年8月28日  下午11:57:21
*    
*/
@Controller
public class OutpatientRequestionMedicineController {
	
	@Autowired
	private OutpatientRequestionMedicineService outpatientRequestionMedicineService;
	
	/**
	* @Title:updata_request_state
	* @Description:改变申领单的状态为已入库
	* @param:@param reqId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月3日 下午3:34:56
	 */
	@ResponseBody
	@GetMapping("updata_request_state_already_put")
	public JsonResult updata_request_state_already_put(String reqId) {
		JsonResult jsonResult = new JsonResult();
		try {
			outpatientRequestionMedicineService.updateOPRMstate(reqId);;
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}
	
	/**
	* @Title:get_already_outStock
	* @Description:查找对应部门已出库状态的申领单
	* @param:@param deptId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月5日 下午5:28:52
	 */
	@ResponseBody
	@GetMapping("get_already_outStock")
	public JsonResult get_already_outStock(String deptId) {
		JsonResult jsonResult = new JsonResult();
		try {
			jsonResult.setResult(outpatientRequestionMedicineService.getAlreadyOutStockByDeptId(deptId));
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}
	
	/**
	* @Title:get_already_out_count
	* @Description:查找对应部门已出库状态的申领单条数
	* @param:@param deptId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月5日 下午5:09:41
	 */
	@ResponseBody
	@GetMapping("get_already_out_count")
	public JsonResult get_already_out_count(String deptId) {
		JsonResult jsonResult = new JsonResult();
		try {
			jsonResult.setResult(outpatientRequestionMedicineService.getAlreadyOutStockCount(deptId));
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}
	
	/**
	* @Title:updata_request_state
	* @Description:改变申领单的状态为已出库
	* @param:@param reqId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月3日 下午3:34:56
	 */
	@ResponseBody
	@GetMapping("updata_request_state")
	public JsonResult updata_request_state(String reqId) {
		JsonResult jsonResult = new JsonResult();
		try {
			outpatientRequestionMedicineService.updateRequestState(reqId);
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}
	
	/**
	* @Title:get_request_by_deptName
	* @Description:根据部门名称查询对应的申领单
	* @param:@param deptName
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月2日 上午9:14:30
	 */
	@ResponseBody
	@GetMapping("get_request_by_deptName")
	public JsonResult get_request_by_deptName(String deptName) {
		JsonResult jsonResult = new JsonResult();
		try {
			jsonResult.setResult(outpatientRequestionMedicineService.getRequestByDeptName(deptName));
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}
	
	/**
	* @Title:get_alldept_request_count
	* @Description:查询各个部门的申领条数
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月2日 上午12:04:23
	 */
	@ResponseBody
	@GetMapping("get_alldept_request_count")
	public JsonResult get_alldept_request_count() {
		JsonResult jsonResult = new JsonResult();
		try {
			jsonResult.setResult(outpatientRequestionMedicineService.getAllDeptRequestCount());
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}
	
	/**
	* @Title:get_request_count_byDeptId
	* @Description:根据部门id查询对应申领药品的条数
	* @param:@param deptId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年8月29日 下午11:54:38
	 */
	@ResponseBody
	@GetMapping("get_request_count_byDeptId")
	public JsonResult get_request_count_byDeptId(String deptId) {
		System.out.println("-------------------");
		System.out.println(deptId);
		JsonResult jsonResult = new JsonResult();
		try {
			jsonResult.setResult(outpatientRequestionMedicineService.queryRequestCountByDetpId(deptId));
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}
	
	/**
	* @Title:get_no_request_medicine
	* @Description:查找未申领的药品
	* @param:@param curPage
	* @param:@param pageSize
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年8月29日 上午11:29:32
	 */
	@ResponseBody
	@GetMapping("get_no_request_medicine")
	public JsonResult get_no_request_medicine(int curPage,int pageSize) {
		JsonResult jsonResult = new JsonResult();
		try {
			jsonResult.setResult(outpatientRequestionMedicineService.queryNoRequest(curPage, pageSize));
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}
	
	/**
	* @Title:get_yes_request_medicine
	* @Description:查找已申领的药品
	* @param:@param curPage
	* @param:@param pageSize
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年8月29日 上午11:30:33
	 */
	@ResponseBody
	@GetMapping("get_yes_request_medicine")
	public JsonResult get_yes_request_medicine(int curPage,int pageSize) {
		JsonResult jsonResult = new JsonResult();
		try {
			jsonResult.setResult(outpatientRequestionMedicineService.queryYesRequest(curPage, pageSize));
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}
	
	/**
	* @Title:get_no_request_medicine
	* @Description:查找药品未申领的数量
	* @param:@return
	* @return:int
	* @throws
	* @author:crazy_long
	* @Date:2019年8月29日 上午12:00:06
	 */
	@ResponseBody
	@GetMapping("get_no_request_medicine_count")
	public int get_no_request_medicine_count() {
		return outpatientRequestionMedicineService.queryNoRequestCount();
	}

}
