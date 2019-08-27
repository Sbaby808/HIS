package com.his.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.Ward;
import com.his.service.WardService;

/**
 * 
* @ClassName: WardController  
* @Description: 住院病区  
* @author Hamster
* @date 2019年8月4日  下午10:11:39
*
 */
@Controller
public class WardController {
	
	@Autowired
	private WardService wardService;
	
	/**
	 * 
	* @Title:getAllWardByPage
	* @Description:分页查询所有病区信息
	* @param:@param curpage
	* @param:@param pagesize
	* @param:@return
	* @return:Map
	* @throws
	* @author:Hamster
	* @Date:2019年8月4日 下午10:11:54
	 */
	@ResponseBody
	@GetMapping("/get_all_wards_byPage")
	public Map getAllWardByPage(String text1,String text2,int curpage,int pagesize){
		String wardName = "%"+text1+"%";
		String ksName = "%"+text2+"%";
		return wardService.getAllWardByPage(wardName,ksName,curpage, pagesize);
	}
	
	/**
	 * 
	* @Title:getAllWard
	* @Description:无分页查询所有病区信息
	* @param:@return
	* @return:List<Ward>
	* @throws
	* @author:Hamster
	* @Date:2019年8月4日 下午10:12:09
	 */
	@ResponseBody
	@GetMapping("/get_all_wards")
	public List <Ward> getAllWard(){
		return wardService.getAllWard();
	}
	
	/**
	 * 
	* @Title:addWard
	* @Description:新增病区
	* @param:@param ward
	* @return:void
	* @throws
	* @author:Hamster
	* @Date:2019年8月4日 下午10:12:34
	 */
	@ResponseBody
	@PostMapping("/add_ward")
	public void addWard(@RequestBody Ward ward){
		wardService.addWard(ward);
	}
	
	
	@ResponseBody
	@PostMapping("/change_ward")
	public void changeWard(@RequestBody Ward ward){
		wardService.addWard(ward);
	}
	
	/**
	 * 
	* @Title:getWardByDid
	* @Description:根据科室id查询病区信息
	* @param:@param ks_id
	* @param:@return
	* @return:List<Ward>
	* @throws
	* @author:Hamster
	* @Date:2019年8月4日 下午10:12:56
	 */
	@ResponseBody
	@GetMapping("/get_ward_ByDid")
	public List <Ward> getWardByDid(String ks_id){
		return wardService.getWardByDid(ks_id);
	}
	
}
