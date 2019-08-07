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
import com.his.utils.Result;

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
	public Result addEmpInformation(@RequestBody EmpInformation emp) {
		try {
			empInformationService.addEmpAllInformation(emp);
			/*System.out.println("----------------------------");
			System.out.println(emp.getWaitingRoomId());
			System.out.println(emp.getTechnicalPost().getTpId());
			System.out.println(emp.getYgName());*/
			return new Result();
		} catch (Exception e) {
			return new Result("添加员工失败");
		}
	}
	
	@ResponseBody
	@PostMapping("edit_emp_information")
	public Result edit_emp_information(@RequestBody EmpInformation emp) {
		try {
			empInformationService.updateEmpInformation(emp);
			return new Result();
		} catch (Exception e) {
			return new Result("修改员工失败");
		}
	}
	
	@ResponseBody
	@GetMapping("get_emp_by_page")
	public Map get_all_emp(int curpage,int pagesize,String searchState,String searchContent){
		if(searchState.equals("1")) {
			return empInformationService.queryByGH(searchContent);
			
		}else if(searchState.equals("2")) {
			return empInformationService.queryEmpByYgname(searchContent);
		}else {
			return empInformationService.queryEmpByPage(curpage, pagesize);
		}
		
	}
	
	@ResponseBody
	@GetMapping("get_emp_by_nameAndxh")
	public List<Object[]> get_emp_by_nameAndxh(){
		return empInformationService.queryEmpforNameAndXH();
	}
	

}
