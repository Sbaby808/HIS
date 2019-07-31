package com.his.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.EmpInformation;
import com.his.pojo.JsonResult;
import com.his.service.EmpInformationService;

/**  
* @ClassName: LoginController  
* @Description: 登录控制器
* @author Sbaby
* @date 2019年7月30日  下午2:11:06
*    
*/
@Controller
public class LoginController {
	
	@Autowired
	private EmpInformationService empInformationService;

	@PostMapping("/login")
	@ResponseBody
	public JsonResult login(@RequestBody EmpInformation empInformation) {
		JsonResult result = new JsonResult();
		try {
			if(empInformationService.loginTestEmp(empInformation)) {
				result.setStatus("ok");
				result.setResult(empInformation);
			} else {
				result.setStatus("error");
				result.setResult("用户名或密码错误！");
			}
		} catch (Exception e) {
			result.setResult("用户不存在");
			result.setStatus("error");
		}
		return result;
	}
	
}
