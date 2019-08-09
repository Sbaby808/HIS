package com.his.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.JsonResult;
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
}
