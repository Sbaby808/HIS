package com.his.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.JsonResult;
import com.his.pojo.PhaIn;
import com.his.service.PhaInService;

/**  
* @ClassName: PhaInController  
* @Description: 药房入库Controller
* @author crazy_long
* @date 2019年9月5日  下午8:06:25
*    
*/
@Controller
public class PhaInController {
	
	@Autowired
	private PhaInService pahInservice;
	
	/**
	* @Title:add_a_phaIn
	* @Description:插入一个药房入库单
	* @param:@param phaIn
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月5日 下午8:15:18
	 */
	@ResponseBody
	@PostMapping("add_a_phaIn")
	public JsonResult add_a_phaIn(@RequestBody PhaIn phaIn) {
		JsonResult jsonResult = new JsonResult();
		try {
			pahInservice.addPhaIn(phaIn);
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}

	/**
	* @Title:phaIn_is_exits
	* @Description:判断入库单是否存在
	* @param:@param phaInId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月5日 下午8:10:03
	 */
	@ResponseBody
	@GetMapping("phaIn_is_exits")
	public JsonResult phaIn_is_exits(String reqId) {
		JsonResult jsonResult = new JsonResult();
		try {
			jsonResult.setResult(pahInservice.pahInIsExits(reqId));
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}
}
