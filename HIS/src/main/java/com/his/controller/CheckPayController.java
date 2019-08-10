package com.his.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.CheckPay;
import com.his.service.CheckPayService;

/**  
* @ClassName: CheckPayController  
* @Description: TODO(检查缴费，划价controller)  
* @author TRC
* @date 2019年7月30日  上午9:43:16
*    
*/
@Controller
public class CheckPayController {

	@Autowired
	private CheckPayService checkPayService;
	
	@ResponseBody
	@GetMapping("/get_all_check_pay")
	public List <CheckPay> getAllCheckPay(){
		return checkPayService.getAllCheckPay();
	}
}
