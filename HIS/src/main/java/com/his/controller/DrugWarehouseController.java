package com.his.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.JsonResult;
import com.his.pojo.PutStockInformation;
import com.his.service.DrugWarehouseService;

/**  
* @ClassName: DrugWarehouseController  
* @Description: 药库批次库存Controller
* @author crazy_long
* @date 2019年8月17日  下午2:56:21
*    
*/
@Controller
public class DrugWarehouseController {
	
	@Autowired
	private DrugWarehouseService drugWarehouseService;

	/**
	* @Title:drug_put_Warehouse
	* @Description:药品一键入库
	* @param:@param psinfo
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年8月17日 下午5:35:46
	 */
	@ResponseBody
	@PostMapping("drug_put_Warehouse")
	public JsonResult drug_put_Warehouse(@RequestBody List<PutStockInformation> psinfo) {
		JsonResult jsonResult = new JsonResult();
		try {
			drugWarehouseService.DrugPutStockBybatch(psinfo);
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}
}
