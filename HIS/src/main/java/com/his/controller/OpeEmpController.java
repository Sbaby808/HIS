package com.his.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.bean.Opeempbean;
import com.his.bean.OperationRecordbean;
import com.his.service.OpeEmpService;

/**  
* @ClassName: OpeEmpController  
* @Description: TODO(手术员工controller)  
* @author TRC
* @date 2019年7月30日  上午9:09:30
*    
*/
@Controller
public class OpeEmpController {
	@Autowired
	private  OpeEmpService opeEmpService;
	@ResponseBody
	@GetMapping("ope_emp_record")
	public List<Opeempbean> get(String opeid){
		return opeEmpService.getaList(opeid);
	}
	

}
