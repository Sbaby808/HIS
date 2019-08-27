package com.his.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.HosEmp;
import com.his.service.HosEmpService;

/**
 * 
* @ClassName: HosEmpController  
* @Description: 住院登记_员工  
* @author Hamster
* @date 2019年8月15日  上午9:06:32
*
 */
@Controller
public class HosEmpController {

	@Autowired
	private HosEmpService hosEmpService;
	
	
}
