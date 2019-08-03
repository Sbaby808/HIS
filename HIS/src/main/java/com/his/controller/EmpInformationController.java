package com.his.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.EmpInformation;
import com.his.service.EmpInformationService;

/**  
* @ClassName: EmpInformationController  
* @Description: 员工Controller 
* @author crazy_long
* @date 2019年8月2日  下午3:13:44
*    
*/
@Controller
public class EmpInformationController {
	
	@Autowired
	private EmpInformationService empInformationService; 
	
	
	@ResponseBody
	@PostMapping("add_emp_information")
	public EmpInformation addEmpInformation(@RequestBody EmpInformation emp) {
		return emp;
	}
	
	@ResponseBody
	@GetMapping("get_emp_by_page")
	public Map get_all_emp(int curpage,int pagesize){
		return empInformationService.queryEmpByPage(curpage, pagesize);
	}
	
	

}
