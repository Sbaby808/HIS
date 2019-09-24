package com.his.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.JsonResult;
import com.his.service.ACR122uService;

/**  
* @ClassName: ACR122uController  
* @Description: ACR122u读写卡工具类
* @author Sbaby
* @date 2019年9月20日  下午5:40:27
*    
*/
@Controller
public class ACR122uController {

	@Autowired
	private ACR122uService acr122uService;
	
	/**
	* @Title:initReadCard
	* @Description: 初始化读卡
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年9月20日 下午5:58:49
	 */
	@GetMapping("/read_acr122u")
	@ResponseBody
	public JsonResult readCard() {
		JsonResult result = new JsonResult();
		try {
			result = acr122uService.readCard();
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:writeCard
	* @Description:写卡
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年9月23日 上午12:00:58
	 */
	@GetMapping("/write_acr122u")
	@ResponseBody
	public JsonResult writeCard(String cardId) {
		JsonResult result = new JsonResult();
		try {
			acr122uService.writeCard(cardId);
			result.setResult(cardId);
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
}