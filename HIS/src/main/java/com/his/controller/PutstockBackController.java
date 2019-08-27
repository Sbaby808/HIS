package com.his.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.JsonResult;
import com.his.pojo.PsBackDetail;
import com.his.service.PsBackDetailsService;

/**  
* @ClassName: PutstockBackController  
* @Description: 退药明细controller
* @author crazy_long
* @date 2019年8月25日  下午11:04:47
*    
*/
@Controller
public class PutstockBackController {
	
	@Autowired
	private PsBackDetailsService psBackDetailsService;
	
	/**
	* @Title:add_drug_bakc_and_detial
	* @Description:添加退药信息和退药明细
	* @param:@param psBackDetail
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年8月26日 上午12:11:01
	 */
	@ResponseBody
	@PostMapping("add_drug_bakc_and_detial")
	public JsonResult add_drug_bakc_and_detial(@RequestBody List<PsBackDetail> psBackDetail) {
		JsonResult jsonResult = new JsonResult();
		try {
			psBackDetailsService.addDrugBackAndDetail(psBackDetail);
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}

}
