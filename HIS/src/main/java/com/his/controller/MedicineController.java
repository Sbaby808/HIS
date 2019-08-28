package com.his.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.JsonResult;
import com.his.pojo.Medicine;
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
	* @Title:query_medicine
	* @Description:查找没有库存的药品
	* @param:@param chooseNuber
	* @param:@param deptId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年8月27日 下午10:02:42
	 */
	@ResponseBody
	@GetMapping("query_no_kucun")
	public JsonResult query_no_kucun(int chooseNuber,String deptId) {
		JsonResult jsonResult = new JsonResult();
		try {
			jsonResult.setResult(medicineService.qeuryNoKuCun(chooseNuber,deptId));
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}

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
	public JsonResult query_medicine(int chooseNuber,String deptId) {
		JsonResult jsonResult = new JsonResult();
		Map map = new HashMap();
		try {
			List<Medicine> list = medicineService.queryMedicineNowNumber(chooseNuber,deptId);
			map.put("list", list);
			map.put("chooseStatus", chooseNuber);
			jsonResult.setResult(map);
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}
}
