package com.his.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.his.pojo.JsonResult;
import com.his.pojo.OutpatientPay;
import com.his.pojo.OutpatientRegistration;
import com.his.service.OtherProjectService;
import com.his.service.OutpatientPayService;

/**  
* @ClassName: OutpatientPayController  
* @Description: 门诊缴费处控制器
* @author Sbaby
* @date 2019年8月14日  下午3:21:53
*    
*/
@Controller
public class OutpatientPayController {

	private static final Logger logger = LoggerFactory.getLogger(OutpatientPayController.class);

	
	@Autowired
	private OutpatientPayService outpatientPayService;
	@Autowired
	private OtherProjectService otherProjectService;
	
	/**
	* @Title:getPriceByReg
	* @Description:根据挂号对象查询价格
	* @param:@param outpatientRegistration
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月15日 上午9:00:29
	 */
	@PostMapping("/get_price_by_reg")
	@ResponseBody
	public JsonResult getPriceByReg(@RequestBody OutpatientRegistration outpatientRegistration) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(otherProjectService.getPriceByReg(outpatientRegistration));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:moneyPayReg
	* @Description:门诊现金缴费
	* @param:@param outpatientPay
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月15日 上午9:28:10
	 */
	@PostMapping("/money_pay_reg")
	@ResponseBody
	public JsonResult moneyPayReg(@RequestBody OutpatientPay outpatientPay) {
		JsonResult result = new JsonResult();
		try {
			outpatientPayService.addRegPay(outpatientPay);
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(outpatientPay);
			result.setStatus("error");
		}
		return result;
	}
	
	
}
