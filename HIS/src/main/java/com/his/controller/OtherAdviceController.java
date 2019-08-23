package com.his.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.JsonResult;
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
	public JsonResult addOtherAdvice(@RequestBody SolveScheme scheme) {
		JsonResult result = new JsonResult();
		
		return result;
	}
	
}
