package com.his.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

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
	* @Description:查询所有药品划价项
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
	public JsonResult addMedicinePay(MedicinePay medicinePay) {
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
	
}
