package com.his.controller;

import java.util.List;

import org.apache.poi.ss.formula.functions.Count;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


import com.his.pojo.Department;
import com.his.service.DepartmentService;

@Controller
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	@ResponseBody
	@GetMapping("/findalldepartments")
	public List<Department> findall(){
		return departmentService.findallDepartments();
	}
	@ResponseBody
	@PostMapping("/updatedepartment")
	public void updateks(@RequestBody Department department) {
		departmentService.updateks(department);
	}
	
	@ResponseBody
	@GetMapping("/findaDepartments")
	public List <Department> finDepartments(String name,int currentpage){
		return departmentService.finDepartments(name,currentpage);
	}
	@ResponseBody
	@GetMapping("/findcount1")
	public long findcount(String ksName) {
		return departmentService.findcount(ksName);
	}
	@ResponseBody
	@GetMapping("/countbynamess")
	public long countbyname(String name){
		return departmentService.countbyname(name);
	}
	
	@ResponseBody
	@GetMapping("/deleteDepartment")
	public void deleteDepartment(String ksid) {
		departmentService.deleteDepartment(ksid);
	}
	
	@ResponseBody
	@PostMapping("/addDepartment")
	public void addDepartment(@RequestBody Department department) {
		departmentService.addorupdateDepartment(department);
	}
	
	@ResponseBody
	@PostMapping("/updateDepartment")
	public void updateDepartment(@RequestBody Department department) {
		departmentService.addorupdateDepartment(department);
	}
}
