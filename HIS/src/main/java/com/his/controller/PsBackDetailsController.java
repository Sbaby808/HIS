package com.his.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.JsonResult;
import com.his.service.PsBackDetailsService;

/**  
* @ClassName: PsBackDetailsController  
* @Description: 药房退药明细CONTROLLER
* @author crazy_long
* @date 2019年9月20日  下午3:30:24
*    
*/
@Controller
public class PsBackDetailsController {

	@Autowired
	private PsBackDetailsService psBackDetailsService;
	
	/**
	* @Title:get_drug_has_back_by_Page
	* @Description:查询退药明细
	* @param:@param gysId
	* @param:@param ygxh
	* @param:@param backDate
	* @param:@param pageSize
	* @param:@param curpage
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月20日 下午3:34:48
	 */
	@ResponseBody
	@GetMapping("get_drug_has_back_by_Page")
	public JsonResult get_drug_has_back_by_Page(String gysId,String ygxh,long backDate,int pageSize,int curpage) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(psBackDetailsService.getHasBackDrugByPage(gysId, ygxh, backDate, pageSize, curpage));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
}
