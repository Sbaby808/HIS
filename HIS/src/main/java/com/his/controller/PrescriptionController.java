package com.his.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	
	@PostMapping("/add_outpatient_prescription")
	@ResponseBody
	public JsonResult addPrescription(@RequestBody Prescription prescription) {
		JsonResult result = new JsonResult();
		
		return result;
	}
	
}
