package com.his.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.his.pojo.EmpInformation;
import com.his.pojo.JsonResult;
import com.his.pojo.MedicinePay;
import com.his.service.MedicinePayService;

/**  
* @ClassName: MedicinePayController  
* @Description: 药品划价项目控制器
* @author Sbaby
* @date 2019年8月6日  下午4:59:39
*    
*/
@Controller
public class MedicinePayController {

	@Autowired
	private MedicinePayService medicinePayService;
	
	/**
	* @Title:getAllMedicinePay
	* @Description:查询所有具有药品收费项的药品信息
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月6日 下午5:07:40
	 */
	@GetMapping("/get_all_medicine_pay")
	@ResponseBody
	public JsonResult getAllMedicinePay() {
		JsonResult result = new JsonResult();
		try {
			result.setResult(medicinePayService.getAll());
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:getMedicinePayByPage
	* @Description:分页查询药品划价项
	* @param:@param pageNum
	* @param:@param pageSize
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月6日 下午5:12:47
	 */
	@GetMapping("/get_medicine_pay_by_page")
	@ResponseBody
	public JsonResult getMedicinePayByPage(int pageNum, int pageSize) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(medicinePayService.getByPage(pageNum, pageSize));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:getAllCount
	* @Description:查询药品划价项总记录条数
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月6日 下午5:31:47
	 */
	@GetMapping("/get_medicine_pay_count")
	@ResponseBody
	public JsonResult getAllCount() {
		JsonResult result = new JsonResult();
		try {
			result.setResult(medicinePayService.getAllCount());
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:addMedicinePay
	* @Description:添加药品收费项
	* @param:@param medicinePay
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月7日 上午8:29:10
	 */
	@PostMapping("/add_medicine_pay")
	@ResponseBody
	public JsonResult addMedicinePay(@RequestBody MedicinePay medicinePay) {
		JsonResult result = new JsonResult();
		try {
			medicinePayService.addMedicinePay(medicinePay);
			result.setResult(medicinePay);
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(medicinePay);
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:setDrug
	* @Description:绑定药品收费项与药品间的关系
	* @param:@param drugId
	* @param:@param medicinePayId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月7日 上午11:19:59
	 */
	@GetMapping("/set_medicine_pay_for_drug")
	@ResponseBody
	public JsonResult setDrug(String drugId, String medicinePayId) {
		JsonResult result = new JsonResult();
		try {
			medicinePayService.setForDrug(drugId, medicinePayId);
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:searchMedicinePay
	* @Description:分页搜索药品收费项
	* @param:@param searchKey
	* @param:@param searchType
	* @param:@param searchSubclass
	* @param:@param searchGys
	* @param:@param pageNum
	* @param:@param pageName
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月7日 下午5:08:03
	 */
	@GetMapping("/search_medicine_pay_by_page")
	@ResponseBody
	public JsonResult searchMedicinePay(String searchKey, String searchType, String searchSubclass, String searchGys, String searchEmp,
			BigDecimal minPrice, BigDecimal maxPrice, int pageNum, int pageSize) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(medicinePayService.searchByPage(searchKey, searchType, searchSubclass, searchGys, searchEmp, minPrice, maxPrice, pageNum, pageSize));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:searchMedicinePayCount
	* @Description:查询符合搜索条件的记录条数
	* @param:@param searchKey
	* @param:@param searchType
	* @param:@param searchSubclass
	* @param:@param searchGys
	* @param:@param searchEmp
	* @param:@param minPrice
	* @param:@param maxPrice
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月8日 上午8:41:24
	 */
	@GetMapping("/search_medicine_pay_count")
	@ResponseBody
	public JsonResult searchMedicinePayCount(String searchKey, String searchType, String searchSubclass, String searchGys, String searchEmp,
			BigDecimal minPrice, BigDecimal maxPrice) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(medicinePayService.searchCount(searchKey, searchType, searchSubclass, searchGys, searchEmp, minPrice, maxPrice));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:updateMedicinePay
	* @Description:修改药品划价项
	* @param:@param medicinePay
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月8日 下午3:14:23
	 */
	@GetMapping("/update_medicine_pay")
	@ResponseBody
	public JsonResult updateMedicinePay(String ygxh, String medicinePayId, BigDecimal price) {
		JsonResult result = new JsonResult();
		try {
			medicinePayService.update(medicinePayId, price, ygxh);
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
}
