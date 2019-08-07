package com.his.controller;

import java.util.ArrayList;
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
	@GetMapping("tt")
	public String tt(int a,int b) {
		System.out.println("----------------------------");
		System.out.println(a+b);
		return "hello TechnicalPost";
	}
	
	@ResponseBody
	@GetMapping("get_all_technicalpost")
	public List<TechnicalPost> get_all_technicalpost(){
		System.out.println("----------------------------------------1");
		List<TechnicalPost> list = technicalposService.queryAllTechnicalPost();
		for (TechnicalPost technicalPost : list) {
			System.out.println("----------------------------------------2");
			System.out.println(technicalPost.getTpName());
		}
		/*List<TechnicalPost> list = new ArrayList<TechnicalPost>();
		TechnicalPost t1 = new TechnicalPost();
		t1.setTpName("aaaaaa");
		TechnicalPost t2 = new TechnicalPost();
		t2.setTpName("bbbbbbbbbbb");
		list.add(t1);
		list.add(t2);*/
		return list;
	}
	

}
