package com.his.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
			result.setResult(observationNoticeService.addObs(observationNotice));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:delObsById
	* @Description:根据编号删除留观建议
	* @param:@param id
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月23日 下午4:57:11
	 */
	@GetMapping("/del_obs_by_id")
	@ResponseBody
	public JsonResult delObsById(String id) {
		JsonResult result = new JsonResult();
		try {
			observationNoticeService.delObsById(id);
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("ok");
		}
		return result;
	}
}
