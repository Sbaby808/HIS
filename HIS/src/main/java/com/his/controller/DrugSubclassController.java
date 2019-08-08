package com.his.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.JsonResult;
import com.his.service.DrugSubclassService;

/**  
* @ClassName: DrugSubclassController  
* @Description: 药品小类Controller 
* @author crazy_long
* @date 2019年8月8日  上午10:15:30
*    
*/
@Controller
public class DrugSubclassController {
	
	@Autowired
	private DrugSubclassService drugSubclassService;
	
	@GetMapping("/get_all_subclass")
	@ResponseBody
	public JsonResult getAllDrugSubclass() {
		JsonResult result = new JsonResult();
		try {
			result.setResult(drugSubclassService.getAllSubclass());
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}

}
