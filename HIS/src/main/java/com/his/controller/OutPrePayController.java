package com.his.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.JsonResult;
import com.his.service.OutPrePayService;

/**  
* @ClassName: OutPrePayController  
* @Description: 门诊处方缴费Controller
* @author Sbaby
* @date 2019年8月26日  上午11:52:40
*    
*/
@Controller
public class OutPrePayController {

	@Autowired
	private OutPrePayService outPrePayService;
	
	/**
	* @Title:getPrescriptionByCardIdCount
	* @Description:根据就诊卡号查询处方数量
	* @param:@param cardId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月26日 上午11:55:40
	 */
	@GetMapping("/get_prescription_count_by_cardId")
	@ResponseBody
	public JsonResult getPrescriptionByCardIdCount(String cardId) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(outPrePayService.getPriscriptionCountByCardId(cardId));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:getPrescriptionByCardId
	* @Description:根据就诊卡号查询处方
	* @param:@param cardId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月26日 下午2:47:53
	 */
	@GetMapping("/get_prescription_by_cardId")
	@ResponseBody
	public JsonResult getPrescriptionByCardId(String cardId) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(outPrePayService.getPriscriptionByCardId(cardId));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
}
