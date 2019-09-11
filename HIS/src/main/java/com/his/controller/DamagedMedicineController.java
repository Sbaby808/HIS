package com.his.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.DamagedMedicine;
import com.his.pojo.JsonResult;
import com.his.service.DamagedMedicineService;

/**  
* @ClassName: DamagedMedicineController  
* @Description: 药品报损Controller
* @author crazy_long
* @date 2019年9月6日  下午4:39:42
*    
*/
@Controller
public class DamagedMedicineController {

	@Autowired
	private DamagedMedicineService damagedMedicineService;
	
	/**
	* @Title:update_damege_for_yes_byId
	* @Description:修改报损单位已完成
	* @param:@param damagedId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月6日 下午11:51:45
	 */
	@ResponseBody
	@GetMapping("update_damege_for_yes_byId")
	public JsonResult update_damege_for_yes_byId(String damagedId) {
		JsonResult jsonResult = new JsonResult();
		try {
			damagedMedicineService.updateDamegeForYesById(damagedId);
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}
	
	/**
	* @Title:del_one_damageAndDetail_by_id
	* @Description:级联删除报损单及其明细
	* @param:@param damagedId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月6日 下午11:46:35
	 */
	@ResponseBody
	@GetMapping("del_one_damageAndDetail_by_id")
	public JsonResult del_one_damageAndDetail_by_id(String damagedId) {
		JsonResult jsonResult = new JsonResult();
		try {
			damagedMedicineService.delOneDamageAndDetail(damagedId);
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}
	
	/**
	* @Title:get_damagedMedicine_for_no
	* @Description:获取所有未完成的申领单
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月6日 下午4:54:14
	 */
	@ResponseBody
	@GetMapping("get_damagedMedicine_for_no")
	public JsonResult get_damagedMedicine_for_no() {
		JsonResult jsonResult = new JsonResult();
		try {
			jsonResult.setResult(damagedMedicineService.getDamagedMedicineForNo());
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}
	
	/**
	* @Title:add_a_damagedMedicine
	* @Description:创建一个报损单
	* @param:@param damagedMedicine
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月6日 下午4:42:57
	 */
	@ResponseBody
	@PostMapping("add_a_damagedMedicine")
	public JsonResult add_a_damagedMedicine(@RequestBody DamagedMedicine damagedMedicine) {
		JsonResult jsonResult = new JsonResult();
		try {
			damagedMedicineService.addDamagedMedicine(damagedMedicine);
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}
	
}
