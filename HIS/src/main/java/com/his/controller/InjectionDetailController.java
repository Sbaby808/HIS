package com.his.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.InjectionDetail;
import com.his.pojo.JsonResult;
import com.his.service.InjectionDetailService;

/**  
* @ClassName: InjectionDetailController  
* @Description: 用药明细Controller
* @author crazy_long
* @date 2019年9月9日  下午9:13:56
*    
*/
@Controller
public class InjectionDetailController {

	@Autowired
	private InjectionDetailService injectionDetailService;
	
	/**
	* @Title:patient_back_drug
	* @Description:病人根据退药单退药
	* @param:@param prescriptionId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月11日 上午11:14:59
	 */
	@ResponseBody
	@GetMapping("patient_back_drug")
	public JsonResult patient_back_drug(String prescriptionId) {
		JsonResult jsonresult = new JsonResult();
		try {
			jsonresult.setResult(injectionDetailService.getDrugDetailToBack(prescriptionId));
			jsonresult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonresult.setStatus("error");
		}
		return jsonresult;
	}
	
	/**
	* @Title:del_one_injection_detail
	* @Description:删除一个明细
	* @param:@param injId
	* @param:@param medicineId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月10日 下午12:15:58
	 */
	@ResponseBody
	@GetMapping("del_one_injection_detail")
	public JsonResult del_one_injection_detail(String injId,String medicineId) {
		JsonResult jsonResult = new JsonResult();
		try {
			injectionDetailService.delOneInjectionDetail(injId, medicineId);
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}
	
	/**
	* @Title:update_detail_number
	* @Description:修改一个明细的数量
	* @param:@param injId
	* @param:@param medicineId
	* @param:@param updateNumber
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月10日 上午11:22:52
	 */
	@ResponseBody
	@GetMapping("update_detail_number")
	public JsonResult update_detail_number(String injId,String medicineId,BigDecimal updateNumber) {
		JsonResult jsonResult = new JsonResult();
		try {
			injectionDetailService.updateDetailNumber(injId, medicineId, updateNumber);
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}
	
	/**
	* @Title:get_already_send_drug
	* @Description：查找未完成用药记录的已经发药的药品
	* @param:@param injId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月10日 上午10:36:06
	 */
	@ResponseBody
	@GetMapping("get_already_send_drug")
	public JsonResult get_already_send_drug(String injId) {
		JsonResult jsonResult = new JsonResult();
		try {
			jsonResult.setResult(injectionDetailService.getAlreadySendDrug(injId));
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}
	/**
	* @Title:ypId_By_injId
	* @Description:查找已经存在的用药明细的ypid
	* @param:@param injId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月9日 下午10:57:11
	 */
	@ResponseBody
	@GetMapping("get_all_ypId_By_injId")
	public JsonResult get_all_ypId_By_injId(String injId) {
		JsonResult jsonResult = new JsonResult();
		try {
			jsonResult.setResult(injectionDetailService.getAllYpIdByInjId(injId));
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}
	
	/**
	* @Title:add_an_injectionDetail
	* @Description:插入一个药品的用药明细
	* @param:@param injectionDetail
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月9日 下午9:17:21
	 */
	@ResponseBody
	@PostMapping("add_an_injectionDetail")
	public JsonResult add_an_injectionDetail(@RequestBody List<InjectionDetail> injectionDetail) {
		JsonResult jsonResult = new JsonResult();
		try {
			injectionDetailService.addAnInjectionDetail(injectionDetail);
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}
	
}
