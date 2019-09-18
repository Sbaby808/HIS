package com.his.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.EmpInformation;
import com.his.service.AmbulanceService;

/**  
* @ClassName: AmbulanceController  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author jack
* @date 2019年8月26日  下午4:35:11
*    
*/
@Controller
public class AmbulanceController {
	@Autowired
	private AmbulanceService ambulanceService;
	@ResponseBody
	@GetMapping("/findfindempbyrolename")
	public List<EmpInformation> findempsbyrolename(String rolename){
		return ambulanceService.findempsbyrolename(rolename);
	}
}
