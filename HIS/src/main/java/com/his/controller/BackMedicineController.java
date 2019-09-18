package com.his.controller;

import java.rmi.ServerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.BackMedicine;
import com.his.pojo.JsonResult;
import com.his.service.BackMedicineService;

/**  
* @ClassName: BackMedicineController  
* @Description: 病人退药单Controller 
* @author crazy_long
* @date 2019年9月11日  下午3:27:12
*    
*/
@Controller
public class BackMedicineController {

	@Autowired
	private BackMedicineService backMedicineService;
	
	/**
	* @Title:patient_back_drug_now
	* @Description:患者退药
	* @param:@param backMedicine
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月11日 下午3:58:04
	 */
	@ResponseBody
	@PostMapping("patient_back_drug_now")
	public JsonResult patient_back_drug_now(@RequestBody BackMedicine backMedicine) {
		JsonResult jsonResult = new JsonResult();
		try {
			backMedicineService.patientBackDrug(backMedicine);
			jsonResult.setStatus("ok");
		} catch (ServerException e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}
}
