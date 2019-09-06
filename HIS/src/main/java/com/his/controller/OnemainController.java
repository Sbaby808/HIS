package com.his.controller;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.his.pojo.AskleaveRecord;
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
	@GetMapping("getworktime")
	public Map getbitime(String time,String ygxh){
		
		return onemainService.getbytime(time,ygxh);
	}
	/**
	 * 
	* @Title:askqinjia
	* @Description:TODO申请请假
	* @param:@param askleaveRecord
	* @return:void
	* @throws
	* @author:TRC
	* @Date:2019年9月3日 上午9:49:57
	 */
	@ResponseBody
	@PostMapping("askleave")
	public String askqinjia(@RequestBody AskleaveRecord askleaveRecord) {
		System.out.println(askleaveRecord.getAskLeaveType());
		return onemainService.askqinjia(askleaveRecord);
	}
	/**
	 * 
	* @Title:getoneask
	* @Description:TODO获得个人请假申请
	* @param:@param ygxh
	* @param:@return
	* @return:Map
	* @throws
	* @author:TRC
	* @Date:2019年9月4日 下午4:23:18
	 */
	@ResponseBody
	@GetMapping("getoneaskleave")
	public Map getoneask(String ygxh) {
		return onemainService.getoneask(ygxh);
	}
	/**
	 * 
	* @Title:getksask
	* @Description:TODO根据排班人员的id查该科室的未处理的请假记录
	* @param:@param ygxh
	* @param:@return
	* @return:Map
	* @throws
	* @author:TRC
	* @Date:2019年9月4日 下午10:18:37
	 */
	@ResponseBody
	@GetMapping("getksask")
	public Map getksask(String ygxh) {
		return onemainService.getksask(ygxh);
	}
	/**
	 * 
	* @Title:agreeask
	* @Description:TODO批准请假
	* @param:@param ygxh
	* @param:@param time
	* @param:@param type
	* @param:@param askid
	* @return:void
	* @throws
	* @author:TRC
	* @Date:2019年9月4日 下午11:26:43
	 */
	@ResponseBody
	@GetMapping("pizhunask")
	public void agreeask(String ygxh,long time,String type,String askid) {
		onemainService.agreeask(ygxh, time, type, askid);
	}
	/**
	 * 
	* @Title:noagreeask
	* @Description:TODO不批准请假
	* @param:@param ygxh
	* @param:@param time
	* @param:@param type
	* @param:@param askid
	* @return:void
	* @throws
	* @author:TRC
	* @Date:2019年9月4日 下午11:26:55
	 */
	@ResponseBody
	@GetMapping("bupizhunask")
	public void noagreeask(String ygxh,long time,String type,String askid) {
		onemainService.noagreeask(ygxh, time, type, askid);
	}
	@ResponseBody
	@GetMapping("getaskrecord")
	public Map getdealask(int curpage,int pagesize,String name,@DateTimeFormat(pattern = "yyyy-MM-dd") @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")Date starttime,@DateTimeFormat(pattern = "yyyy-MM-dd") @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")Date endtime,String ygxh) {
		return onemainService.getdealask(curpage, pagesize, name, starttime, endtime, ygxh);
	}
}
