package com.his.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.Purchase;
import com.his.utils.Result;

import oracle.jdbc.proxy.annotation.Post;

/**  
* @ClassName: PruchaseController  
* @Description: 采购计划Controller
* @author crazy_long
* @date 2019年8月1日  下午4:36:02
*    
*/
@Controller
public class PruchaseController {
	
	@ResponseBody
	@GetMapping("/h")
	public Result h() {
	
		return new Result("holle crazy_long");
		
	}
	
	@ResponseBody
	@PostMapping("/add_pruchase")
	public Result add_pruchase(List<Purchase> list) {
		
			System.out.println(list);
		
		return new Result();
	}

}
