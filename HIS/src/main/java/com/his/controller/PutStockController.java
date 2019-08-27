package com.his.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.JsonResult;
import com.his.service.PutStockService;

/**  
* @ClassName: PutStockController  
* @Description: 入库记录controller
* @author crazy_long
* @date 2019年8月19日  上午10:18:02
*    
*/
@Controller
public class PutStockController {
	
	@Autowired
	private PutStockService putStockService;
	
	/**
	* @Title:get_all_putTime
	* @Description:查询所有入库时间
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年8月19日 上午10:52:22
	 */
	@ResponseBody
	@GetMapping("get_all_putTime")
	public JsonResult get_all_putTime() {
		JsonResult jsonResult = new JsonResult();
		try {
			jsonResult.setResult(putStockService.queryAllputTime());
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}

}
