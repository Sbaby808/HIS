package com.his.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.EmpInformation;
import com.his.pojo.JsonResult;
import com.his.pojo.OutpatientRegistration;
import com.his.service.OutpatientRegistrationService;

/**  
* @ClassName: OutpatientRegistrationController  
* @Description: 门诊挂号控制器
* @author Sbaby
* @date 2019年8月9日  下午4:23:32
*    
*/
@Controller
public class OutpatientRegistrationController {

	@Autowired
	private OutpatientRegistrationService outpatientRegistrationService;
	
	/**
	* @Title:getAllKSForOut
	* @Description:查询门诊的所有科室
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月9日 下午4:27:55
	 */
	@GetMapping("/get_all_ks_for_out")
	@ResponseBody
	public JsonResult getAllKSForOut() {
		JsonResult result = new JsonResult();
		try {
			result.setResult(outpatientRegistrationService.getKSbyOut());
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:getAllTPForOut
	* @Description:查询门诊的所有职称
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月9日 下午4:57:13
	 */
	@GetMapping("/get_all_tp_for_out")
	@ResponseBody
	public JsonResult getAllTPForOut() {
		JsonResult result = new JsonResult();
		try {
			result.setResult(outpatientRegistrationService.getTpbyOut());
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:getDocByKsAndTp
	* @Description:根据日期、科室和职称查询门诊医生
	* @param:@param ks
	* @param:@param tp
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月10日 上午8:47:43
	 */
	@GetMapping("/get_doc_by_ks_and_tp")
	@ResponseBody
	public JsonResult getDocByKsAndTp(String ks, String tp, Long doDate) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(outpatientRegistrationService.getDocByKsAndTp(ks, tp, doDate));
			result.setStatus("ok");
		} catch (Exception e) {
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:getWorktime
	* @Description:根据医生编号查询排班时间
	* @param:@param empId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月10日 上午9:24:33
	 */
	@GetMapping("/get_worktime_by_empid")
	@ResponseBody
	public JsonResult getWorktime(String empId) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(outpatientRegistrationService.getWorktimeByEmpid(empId));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:getRegistrationCount
	* @Description:根据医生编号查询当日挂号人数
	* @param:@param empId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月10日 下午2:05:33
	 */
	@GetMapping("/get_registration_by_empId")
	@ResponseBody
	public JsonResult getRegistrationCount(String empId) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(outpatientRegistrationService.getRegistrationCount(empId));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:getOutpatientDoctorNum
	* @Description:查询门诊各科室医生挂号详情
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月10日 下午2:44:28
	 */
	@GetMapping("/get_outpatient_doctor_num")
	@ResponseBody
	public JsonResult getOutpatientDoctorNum() {
		JsonResult result = new JsonResult();
		try {
			result.setResult(outpatientRegistrationService.getOutpatientDoctorCount());
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:addRegistration
	* @Description:生成挂号单
	* @param:@param outpatientRegistration
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月12日 上午11:07:29
	 */
	@PostMapping("/add_registration")
	@ResponseBody
	public JsonResult addRegistration(@RequestBody OutpatientRegistration outpatientRegistration) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(outpatientRegistrationService.addReg(outpatientRegistration));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(outpatientRegistration);
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:getDocByKsAndTp
	* @Description:根据日期、科室和职称随机分配门诊医生
	* @param:@param ks
	* @param:@param tp
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月10日 上午8:47:43
	 */
	@GetMapping("/get_rnd_doc_by_ks_and_tp")
	@ResponseBody
	public JsonResult getRandomDocByKsAndTp(String ks, String tp, Long doDate) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(outpatientRegistrationService.getRandomDocByKsAndTp(ks, tp, doDate));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:checkEmp
	* @Description:检查医生是否有余号
	* @param:@param ygxh
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月12日 下午3:46:02
	 */
	@GetMapping("/check_emp_reg")
	@ResponseBody
	public JsonResult checkEmp(String ygxh, int total) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(outpatientRegistrationService.checkEmp(ygxh, total));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
}
