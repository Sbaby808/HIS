package com.his.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.Dept;
import com.his.service.DeptService;

/**  
* @ClassName: DeptController  
* @Description:dept增删改查  
* @author jack
* @date 2019年8月13日  上午9:47:10
*    
*/
@Controller
public class DeptController {
	@Autowired
	private DeptService deptService;
	//查看数据库有多少条数据
	@GetMapping("/findcount")
	@ResponseBody
	public int findcount() {
		return deptService.findcount();
	}
//添加部门
	@PostMapping("/addDept")
	@ResponseBody
	public void addDept(@RequestBody Dept dept) {
		deptService.addDept(dept);
	}
	//查询所有部门
	@GetMapping("/findDept1")
	@ResponseBody
	public List<Dept> find() {
		return deptService.findDept();
	}
	//分页查询
	@GetMapping("/finddept")
	@ResponseBody
	public List<Dept> finddept(int currentpage){
		return deptService.findDepts(currentpage);
	}
	//修改部门
	@PostMapping("/updateDept")
	@ResponseBody
	public void updateDept(@RequestBody Dept dept) {
		deptService.updateDept(dept);
	}
	//删除部门
	@GetMapping("/deleteDept")
	@ResponseBody
	public void deleteDept(String id) {
		deptService.deleteDept(id);
	}
	//根据部门名字查询有多少个部门
	@GetMapping("/countbyname")
	@ResponseBody
	public long findcount(String name) {
		return deptService.findcounts(name);
	}
	//根据部门名字模糊查询有多少
	@GetMapping("/findnamecount")
	@ResponseBody
	public long findcounts(String name) {
		return deptService.findcountss(name);
	}
	//根据名字查询部门集合
	@GetMapping("/finddeptss")
	@ResponseBody
	public List<Dept> finddepts(String name,int currentpage){
		return deptService.findDeptss(name,currentpage);
	}
	//根据id查询部门
	@GetMapping("/findbyid")
	@ResponseBody
	public Dept finDept(String id) {
		return deptService.finDept(id);
	}
	
	
	
	
}
