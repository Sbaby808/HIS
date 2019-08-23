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
