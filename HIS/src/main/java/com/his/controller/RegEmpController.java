package com.his.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.JsonResult;
import com.his.pojo.RegEmp;
import com.his.service.RegEmpService;

/**  
* @ClassName: RegEmpController  
* @Description: 挂号员工控制器
* @author Sbaby
* @date 2019年8月12日  下午2:03:32
*    
*/
@Controller
public class RegEmpController {

	@Autowired
	private RegEmpService regEmpService;
	
	/**
	* @Title:addRegEmp
	* @Description:添加挂号员工关系
	* @param:@param regEmp
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月12日 下午2:17:30
	 */
	@PostMapping("/add_reg_emp")
	@ResponseBody
	public JsonResult addRegEmp(@RequestBody RegEmp regEmp) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(regEmpService.addRegEmp(regEmp));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(regEmp);
			result.setStatus("error");
		}
		return result;
	}
}
