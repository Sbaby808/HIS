package com.his.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.ChangeDrugDetail;
import com.his.pojo.JsonResult;
import com.his.service.ChangeDrugDetailsService;
import com.his.utils.ServiceException;

/**  
* @ClassName: ChangeDrugDetailController  
* @Description: 调拨controller(当作回库用)
* @author crazy_long
* @date 2019年9月12日  下午10:40:02
*    
*/
@Controller
public class ChangeDrugDetailController {

	@Autowired
	private ChangeDrugDetailsService changeDrugDetailsService;
	
	/**
	* @Title:update_oneDetail_number
	* @Description:修改回库数量
	* @param:@param alloId
	* @param:@param medicineId
	* @param:@param updateNumber
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月22日 下午10:13:53
	 */
	@ResponseBody
	@GetMapping("update_oneDetail_number")
	public JsonResult update_oneDetail_number(String alloId,String medicineId,BigDecimal updateNumber) {
		JsonResult result = new JsonResult();
		try {
			changeDrugDetailsService.updateOneDetailNumber(alloId, medicineId, updateNumber);
			result.setStatus("ok");
		} catch (ServiceException e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:back_detail_is_have
	* @Description:判断回库单是否有明细
	* @param:@param alloId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月22日 下午9:27:06
	 */
	@ResponseBody
	@GetMapping("back_detail_is_have")
	public JsonResult back_detail_is_have(String alloId) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(changeDrugDetailsService.backDetailIsHave(alloId));;
			result.setStatus("ok");
		} catch (ServiceException e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:del_one_change_drug_details
	* @Description:删除一个回库明细
	* @param:@param alloId
	* @param:@param medicineId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月12日 下午11:34:36
	 */
	@ResponseBody
	@GetMapping("del_one_change_drug_details")
	public JsonResult del_one_change_drug_details(String alloId,String medicineId) {
		JsonResult result = new JsonResult();
		try {
			changeDrugDetailsService.delOneDetail(alloId,medicineId);
			result.setStatus("ok");
		} catch (ServiceException e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:get_detail_by_alloId
	* @Description:根据返库id查找明细
	* @param:@param alloId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月12日 下午11:16:15
	 */
	@ResponseBody
	@GetMapping("get_detail_by_alloId")
	public JsonResult get_detail_by_alloId(String alloId) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(changeDrugDetailsService.getDetailByAlloId(alloId));;
			result.setStatus("ok");
		} catch (ServiceException e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:add_change_drug_details_by_batch
	* @Description:添加回库明细
	* @param:@param changeDrugDetail
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月12日 下午10:58:36
	 */
	@ResponseBody
	@PostMapping("add_change_drug_details_by_batch")
	public JsonResult add_change_drug_details_by_batch(@RequestBody List<ChangeDrugDetail> changeDrugDetail) {
		JsonResult result = new JsonResult();
		try {
			changeDrugDetailsService.addDetailByBatch(changeDrugDetail);
			result.setStatus("ok");
		} catch (ServiceException e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		
		return result;
	}
	
}
