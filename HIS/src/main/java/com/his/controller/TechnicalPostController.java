package com.his.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.TechnicalPost;
import com.his.service.TechnicalPosService;

/**  
* @ClassName: TechnicalPostController  
* @Description: 职称Controller 
* @author crazy_long
* @date 2019年8月3日  上午12:32:39
*    
*/
@Controller
public class TechnicalPostController {
	
	@Autowired
	private TechnicalPosService technicalposService;
	
	@ResponseBody
	@GetMapping("get_all_technicalpost")
	public List<TechnicalPost> get_all_technicalpost(){
		return technicalposService.queryAllTechnicalPost();
	}
	

}
