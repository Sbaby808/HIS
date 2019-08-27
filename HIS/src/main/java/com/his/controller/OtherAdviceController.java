package com.his.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.JsonResult;
import com.his.pojo.OtherAdvice;
import com.his.pojo.SolveScheme;
import com.his.service.OtherAdviceService;

/**  
* @ClassName: OtherAdviceController  
* @Description: 门诊其他建议Controller
* @author Sbaby
* @date 2019年8月22日  下午5:44:36
*    
*/
@Controller
public class OtherAdviceController {

	@Autowired
	private OtherAdviceService otherAdviceService;
	
	/**
	* @Title:addOtherAdvice
	* @Description:添加其他建议
	* @param:@param otherAdvice
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月23日 下午11:40:38
	 */
	@PostMapping("/add_otheradvice")
	@ResponseBody
	public JsonResult addOtherAdvice(@RequestBody OtherAdvice otherAdvice) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(otherAdviceService.addOtherAdvice(otherAdvice));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:findAllAdvice
	* @Description:查询所有其他建议
	* @param:@param solveScheId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月23日 下午11:40:47
	 */
	@GetMapping("/find_all_advice")
	@ResponseBody
	public JsonResult findAllAdvice(String solveScheId) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(otherAdviceService.findOtherAdvice(solveScheId));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:findOtherAdviceByHistoryId
	* @Description:根据诊断记录id查询常规医嘱
	* @param:@param historyId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月25日 下午1:00:45
	 */
	@GetMapping("/get_other_advice_by_history")
	@ResponseBody
	public JsonResult findOtherAdviceByHistoryId(String historyId) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(otherAdviceService.findOtherAdviceByHistoryId(historyId));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:delAdviceById
	* @Description:根据Id删除其他建议
	* @param:@param advId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月23日 下午11:41:00
	 */
	@GetMapping("/del_adv_by_id")
	@ResponseBody
	public JsonResult delAdviceById(String advId) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(otherAdviceService.delAdviceById(advId));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
}
