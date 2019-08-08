package com.his.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.his.service.DrugSubclassService;

/**  
* @ClassName: DrugSubclassController  
* @Description: 药品小类Controller 
* @author crazy_long
* @date 2019年8月8日  上午10:15:30
*    
*/
@Controller
public class DrugSubclassController {
	
	@Autowired
	private DrugSubclassService drugSubclassService;

}
