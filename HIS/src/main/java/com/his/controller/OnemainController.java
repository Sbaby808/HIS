package com.his.controller;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.EmpInformation;
import com.his.pojo.WorkTime;
import com.his.service.OnemainService;


/**  
* @ClassName: OnemainController  
* @Description: TODO(个人主页controller)  
* @author TRC
* @date 2019年8月22日  上午11:45:33
*    
*/
@Controller
public class OnemainController {
	@Autowired
	private OnemainService onemainService;
	/**
	 * 
	* @Title:getempbyid
	* @Description:TODO根据id获得员工信息
	* @param:@param id
	* @param:@return
	* @return:EmpInformation
	* @throws
	* @author:TRC
	* @Date:2019年8月22日 下午5:06:35
	 */
	@ResponseBody
	@GetMapping("getempinfo")
	public EmpInformation getempbyid(String id) {
		return onemainService.getbyid(id);
	}
	/**
	 * 
	* @Title:editemp
	* @Description:TODO修改员工信息个人
	* @param:@param emp
	* @return:void
	* @throws
	* @author:TRC
	* @Date:2019年8月22日 下午5:06:49
	 */
	@ResponseBody
	@PostMapping("editemp")
	public void editemp(@RequestBody EmpInformation emp) {
		onemainService.editemp(emp);
	}
	/**
	 * 
	* @Title:getbitime
	* @Description:TODO获取本周的排班个人
	* @param:@param date
	* @param:@return
	* @return:Map
	* @throws
	* @author:TRC
	* @Date:2019年8月22日 下午5:07:05
	 */
	@ResponseBody
	@PostMapping("getworktime")
	public Map getbitime(@RequestBody List<String> date){
		
		return onemainService.getbytime(date);
	}

}
