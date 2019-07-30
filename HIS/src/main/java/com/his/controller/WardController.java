package com.his.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.Ward;
import com.his.service.WardService;

/**
 * 住院病区
 * @author dell
 *
 */
@Controller
public class WardController {
	
	@Autowired
	private WardService wardService;
	
	@ResponseBody
	@GetMapping("/get_all_wards")
	public Map getAllWard(int curpage,int pagesize){
		return wardService.getAllWards(curpage, pagesize);
	}
	
	@ResponseBody
	@PostMapping("/add_ward")
	public void addWard(@RequestBody Ward ward){
		wardService.addWard(ward);
	}
	
	@ResponseBody
	@PostMapping("/change_ward")
	public void changeWard(@RequestBody Ward ward){
		wardService.addWard(ward);
	}
}
