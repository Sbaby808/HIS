package com.his.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.his.service.SolveSchemeService;

/**  
* @ClassName: SolveSchemeController  
* @Description: 门诊医嘱控制器
* @author Sbaby
* @date 2019年8月23日  上午10:04:32
*    
*/
@Controller
public class SolveSchemeController {

	@Autowired
	private SolveSchemeService solveSchemeService;
	
	
}
