package com.his.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.JsonResult;
import com.his.pojo.Prescription;
import com.his.service.PrescriptionService;

/**  
* @ClassName: PrescriptionController  
* @Description: 门诊处方控制器
* @author Sbaby
* @date 2019年8月22日  上午11:00:41
*    
*/
@Controller
public class PrescriptionController {

	@Autowired
	private PrescriptionService prescriptionService;
	
	/**
	* @Title:addPrePay
	* @Description:添加处方缴费项
	* @param:@param prescriptionId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月26日 下午4:24:43
	 */
	@GetMapping("/add_pre_pay")
	@ResponseBody
	public JsonResult addPrePay(String prescriptionId, String ygxh) {
		JsonResult result = new JsonResult();
		try {
			prescriptionService.addPrePay(prescriptionId, ygxh);
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
}
