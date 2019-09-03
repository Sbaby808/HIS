package com.his.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.Dept;
import com.his.pojo.JsonResult;
import com.his.pojo.OutstockDetail;
import com.his.service.OutstockDetailsService;

/**  
* @ClassName: OutstockDetailsController  
* @Description: 出库明细Controller
* @author crazy_long
* @date 2019年9月3日  上午9:45:03
*    
*/

@Controller
public class OutstockDetailsController {
	
	@Autowired
	private OutstockDetailsService outstockDetailsService;
		
	/**
	* @Title:add_one_drug_outStockDetail
	* @Description:添加一个药品的出库明细
	* @param:@param outstockDetail
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月3日 上午9:50:37
	 */
	@ResponseBody
	@PostMapping("add_one_drug_outStockDetail")
	public JsonResult add_one_drug_outStockDetail(@RequestBody List<OutstockDetail> outstockDetail) {		
		JsonResult jsonResult = new JsonResult();
		try {
			outstockDetailsService.addOutStockByOneDrug(outstockDetail);
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}

}
