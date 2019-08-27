package com.his.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.JsonResult;
import com.his.service.MedicineService;

/**  
* @ClassName: MedicineController  
* @Description: 药房药品信息Controller
* @author crazy_long
* @date 2019年8月26日  下午4:48:50
*    
*/
@Controller
public class MedicineController {
	
	@Autowired
	private MedicineService medicineService;

	/**
	* @Title:query_medicine_by_page
	* @Description:根据药房药品库存段查找对应的药品
	* @param:@param chooseNuber
	* @param:@param curPage
	* @param:@param pageSize
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年8月26日 下午4:55:03
	 */
	@ResponseBody
	@GetMapping("query_medicine")
	public JsonResult query_medicine(int chooseNuber) {
		JsonResult jsonResult = new JsonResult();
		try {
			jsonResult.setResult(medicineService.queryMedicineNowNumber(chooseNuber));
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}
}
