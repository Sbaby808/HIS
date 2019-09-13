package com.his.controller;

import java.math.BigDecimal;
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
	* @Title:get_medicine_can_use
	* @Description:查找对应部门存在库存且没有过期的药品 
	* @param:@param ypId
	* @param:@param deptId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月9日 下午5:15:15
	 */
	@ResponseBody
	@GetMapping("get_medicine_can_use")
	public JsonResult get_medicine_can_use(String ypId,String deptId) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(medicineService.getMedicineCanUse(ypId, deptId));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:search_medicine_drug_by_page
	* @Description:查找某一个部门的药房药品
	* @param:@param searchKey
	* @param:@param searchType
	* @param:@param searchSubclass
	* @param:@param searchGys
	* @param:@param searchMinorDefect
	* @param:@param minPrice
	* @param:@param maxPrice
	* @param:@param minNumber
	* @param:@param maxNumber
	* @param:@param deptId
	* @param:@param pageNum
	* @param:@param pageSize
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月6日 下午2:15:10
	 */
	@ResponseBody
	@GetMapping("search_medicine_drug_by_page")
	public JsonResult search_medicine_drug_by_page(String searchKey, String searchType, String searchSubclass, String searchGys, String searchMinorDefect,
			BigDecimal minPrice, BigDecimal maxPrice,BigDecimal minNumber,BigDecimal maxNumber, String deptId,int pageNum, int pageSize) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(medicineService.searchDrugByPage(searchKey, searchType, searchSubclass, searchGys, searchMinorDefect, minPrice, maxPrice, minNumber, maxNumber, deptId, pageNum, pageSize));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:update_KuCun_Count
	* @Description:修改药品的库存
	* @param:@param medicineId
	* @param:@param updateNumber
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月6日 下午3:02:19
	 */
	@ResponseBody
	@GetMapping("update_KuCun_Count")
	public JsonResult update_KuCun_Count(String medicineId,BigDecimal updateNumber) {
		JsonResult jsonResult = new JsonResult();
		try {
			medicineService.updateKuCunCount(medicineId, updateNumber);
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}
	
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
