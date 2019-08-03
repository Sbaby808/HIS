package com.his.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.EmpInformation;

/**  
* @ClassName: EmpInformationController  
* @Description: 员工Controller 
* @author crazy_long
* @date 2019年8月2日  下午3:13:44
*    
*/
@Controller
public class EmpInformationController {
	
	@ResponseBody
	@GetMapping("eintest")
	public String et(String tt) {
		System.out.println(tt);
		return "EmpInformationController";
	}
	
	@ResponseBody
	@PostMapping("add_emp_information")
	public EmpInformation addEmpInformation(@RequestBody EmpInformation emp) {
		
		return emp;
	}
	

}
