package com.his.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.DamagedDrugDetail;
import com.his.pojo.DamagedMedicine;
import com.his.pojo.JsonResult;
import com.his.service.DamagedDrugDetailsService;

/**  
* @ClassName: DamagedDrugDetailController  
* @Description: 药品报损明细Controller
* @author crazy_long
* @date 2019年9月6日  下午9:59:17
*    
*/
@Controller
public class DamagedDrugDetailController {
	
	@Autowired
	private DamagedDrugDetailsService damagedDrugDetailsService;
	
	/**
	* @Title:query_damagedDetail_ByPage
	* @Description:分页查找报损明细
	* @param:@param damagedId
	* @param:@param curPage
	* @param:@param pageSize
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月22日 下午1:58:33
	 */
	@ResponseBody
	@GetMapping("query_damagedDetail_ByPage")
	public JsonResult query_damagedDetail_ByPage(String damagedId,int curPage,int pageSize) {
		JsonResult jsonResult = new JsonResult();
		try {
			jsonResult.setResult(damagedDrugDetailsService.queryDamagedDetailByPage(damagedId, curPage, pageSize));
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}
	
	/**
	* @Title:damaged_medicine_is_hava_detail
	* @Description:判断一个报损单是否拥有明细
	* @param:@param damagedId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月22日 下午12:27:57
	 */
	@ResponseBody
	@GetMapping("damaged_medicine_is_hava_detail")
	public JsonResult damaged_medicine_is_hava_detail(String damagedId) {
		JsonResult jsonResult = new JsonResult();
		try {
			jsonResult.setResult(damagedDrugDetailsService.isHaveDetail(damagedId));
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}
	
	/**
	* @Title:del_one_damageDetail
	* @Description:更新一个报损单明细的数量
	* @param:@param damagedId
	* @param:@param medicineId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月22日 下午12:34:15
	 */
	@ResponseBody
	@GetMapping("update_damageDetial_number")
	public JsonResult update_damageDetial_number(String damagedId,String medicineId,BigDecimal updateNumber) {
		JsonResult jsonResult = new JsonResult();
		try {
			damagedDrugDetailsService.updateDamageDetialNumber(damagedId, medicineId, updateNumber);
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}
	
	/**
	* @Title:add_demageDetail_by_batch
	* @Description:批量加入报损明细
	* @param:@param damagedDrugDetail
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月6日 下午10:01:30
	 */
	@ResponseBody
	@PostMapping("add_demageDetail_by_batch")
	public JsonResult add_demageDetail_by_batch(@RequestBody List<DamagedDrugDetail> damagedDrugDetail) {
		JsonResult jsonResult = new JsonResult();
		try {
			damagedDrugDetailsService.addDamageDetailByBatch(damagedDrugDetail);
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}
	
	/**
	* @Title:del_one_damageDetail
	* @Description:删除一个明细
	* @param:@param damagedId
	* @param:@param medicineId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月7日 上午12:43:49
	 */
	@ResponseBody
	@GetMapping("del_one_damageDetail")
	public JsonResult del_one_damageDetail(String damagedId,String medicineId) {
		JsonResult jsonResult = new JsonResult();
		try {
			damagedDrugDetailsService.delOneDetail(damagedId, medicineId);
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}
	
	/**
	* @Title:get_detail_by_damagedId
	* @Description:根据报损单id查找对应的明细
	* @param:@param damagedId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月6日 下午11:39:40
	 */
	@ResponseBody
	@GetMapping("get_detail_by_damagedId")
	public JsonResult get_detail_by_damagedId(String damagedId) {
		JsonResult jsonResult = new JsonResult();
		try {
			jsonResult.setResult(damagedDrugDetailsService.queryDetailByDamagedId(damagedId));
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}

}
