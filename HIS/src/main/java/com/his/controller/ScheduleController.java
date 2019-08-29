package com.his.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.his.bean.Zong;
import com.his.pojo.EmpInformation;
import com.his.service.WorkTimeService;

/**  
* @ClassName: ScheduleController  
* @Description: TODO(排班controller)  
* @author TRC
* @date 2019年8月20日  下午2:17:54
*    
*/
@Controller
public class ScheduleController {
	@Autowired
	private WorkTimeService WorkTimeService;
	@ResponseBody
	@GetMapping("getall_emp")
	public List<EmpInformation> getksemp(String ygxh){
		return WorkTimeService.getallemp(ygxh);
	}
	@ResponseBody
	@PostMapping("addworktime")
	public String addworktime(@RequestBody List<EmpInformation> emp) {
		return WorkTimeService.addworktime(emp);
	}
	@ResponseBody
	@GetMapping("getempbydate")
	public Map getempbydate(@DateTimeFormat(pattern = "yyyy-MM-dd") @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8") Date date,String type) {
		return WorkTimeService.getempbydate(date,type);
	}
	@ResponseBody
	@PostMapping("editworktime")
	public String editpaibanemp(@RequestBody List<EmpInformation> emp) {
		return WorkTimeService.editpaiban(emp);
	}
	@ResponseBody
	@GetMapping("getzhouworktime")
	public Zong getworktimebyzhou(@DateTimeFormat(pattern = "yyyy-MM-dd") @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8") Date time,String ygxh) {
		return WorkTimeService.getworktimezhou(time, ygxh);
	}
	@ResponseBody
	@PostMapping("addzhouworktime")
	public String addworkzhou(@RequestBody Zong zong) {
		List<EmpInformation> list=zong.getOnelist().getWhite().getZrlist();
		for (EmpInformation empInformation : list) {
			System.out.println(empInformation.getYgName());
		}
		return WorkTimeService.addworkzhou(zong);
	}


}
