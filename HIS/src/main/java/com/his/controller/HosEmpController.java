package com.his.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.HosEmp;
import com.his.service.HosEmpService;

@Controller
public class HosEmpController {

	@Autowired
	private HosEmpService hosEmpService;
	
	@ResponseBody
	@PostMapping("/add_hos_emp")
	public void addHosEmp(@RequestBody HosEmp hosEmp){
		hosEmpService.addHosEmp(hosEmp);
	}
}
