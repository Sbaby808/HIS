package com.his.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.his.service.OpeDrugDetailsService;

/**  
* @ClassName: OpeDrugDetailController  
* @Description: TODO(手术用药详情controller)  
* @author TRC
* @date 2019年7月30日  上午9:13:02
*    
*/
@Controller
public class OpeDrugDetailController {
	@Autowired
	private OpeDrugDetailsService opeDrugDetailsService;

}
