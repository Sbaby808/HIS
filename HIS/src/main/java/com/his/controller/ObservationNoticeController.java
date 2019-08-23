package com.his.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.JsonResult;
import com.his.pojo.ObservationNotice;
import com.his.service.ObservationNoticeService;

/**  
* @ClassName: ObservationNoticeController  
* @Description: 留观通知Controlelr
* @author Sbaby
* @date 2019年8月23日  下午2:42:39
*    
*/
@Controller
public class ObservationNoticeController {

	@Autowired
	private ObservationNoticeService observationNoticeService;
	
	/**
	* @Title:addObsNotice
	* @Description:新增或编辑留观通知单
	* @param:@param observationNotice
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月23日 下午2:44:19
	 */
	@PostMapping("/add_obs_notice")
	@ResponseBody
	public JsonResult addObsNotice(@RequestBody ObservationNotice observationNotice) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(observationNotice);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
}
