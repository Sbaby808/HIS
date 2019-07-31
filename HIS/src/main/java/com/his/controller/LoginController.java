package com.his.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.EmpInformation;
import com.his.pojo.JsonResult;

/**  
* @ClassName: LoginController  
* @Description: 登录控制器
* @author Sbaby
* @date 2019年7月30日  下午2:11:06
*    
*/
@Controller
public class LoginController {

	@PostMapping("/login")
	@ResponseBody
	public JsonResult login(@RequestBody EmpInformation empInformation) {
		JsonResult result = new JsonResult();
		result.setResult(empInformation);
		result.setStatus("ok");
		return result;
	}
	
}
