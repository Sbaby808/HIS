package com.his.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.his.pojo.Department;
import com.his.service.DepartmentService;

@Controller
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@ResponseBody
	@GetMapping("/findaDepartments")
	public List <Department> finDepartments(String name,int currentpage){
		return departmentService.finDepartments(name,currentpage);
	}
	
	@ResponseBody
	@GetMapping("/deleteDepartment")
	public void deleteDepartment(String ksid) {
		departmentService.deleteDepartment(ksid);
	}
	
	@ResponseBody
	@PostMapping("/addDepartment")
	public void addDepartment(Department department) {
		departmentService.addorupdateDepartment(department);
	}
	
	@ResponseBody
	@PostMapping("/updateDepartment")
	public void updateDepartment(Department department) {
		departmentService.addorupdateDepartment(department);
	}
}
