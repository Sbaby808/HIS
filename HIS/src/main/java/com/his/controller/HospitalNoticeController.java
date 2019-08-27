package com.his.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.HospitalNotice;
import com.his.pojo.JsonResult;
import com.his.service.HospitalNoticeService;

/**  
* @ClassName: HospitalNoticeController  
* @Description: 入院通知单Controller
* @author Sbaby
* @date 2019年8月23日  上午11:08:43
*    
*/
@Controller
public class HospitalNoticeController {
	
	@Autowired
	private HospitalNoticeService HospitalNoticeService;

	/**
	* @Title:addHosNotice
	* @Description:添加或编辑入院通知
	* @param:@param hospitalNotice
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月23日 下午2:20:53
	 */
	@PostMapping("/add_hos_notice")
	@ResponseBody
	public JsonResult addHosNotice(@RequestBody HospitalNotice hospitalNotice) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(HospitalNoticeService.addHospitalNotice(hospitalNotice));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:getHosDepartment
	* @Description:查询所有住院科室
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月23日 下午2:21:04
	 */
	@GetMapping("/get_all_hos_departments")
	@ResponseBody
	public JsonResult getHosDepartment() {
		JsonResult result = new JsonResult();
		try {
			result.setResult(HospitalNoticeService.getAllHosDepartment());
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:findHosNotice
	* @Description:根据医嘱编号查询入院通知
	* @param:@param scheId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月23日 下午2:05:26
	 */
	@GetMapping("/get_hos_notice")
	@ResponseBody
	public JsonResult findHosNotice(String  scheId) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(HospitalNoticeService.getHosNoticeBySolveId(scheId));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:getHospitalNoticeByHistoryId
	* @Description:根据诊断记录编号查询入院通知
	* @param:@param historyId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月25日 下午1:13:55
	 */
	@GetMapping("/get_hospital_notice_by_history_id")
	@ResponseBody
	public JsonResult getHospitalNoticeByHistoryId(String historyId) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(HospitalNoticeService.getHospitalNoticeByHistoryId(historyId));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:delHosNoticeById
	* @Description:删除入院通知
	* @param:@param hosId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月23日 下午2:22:15
	 */
	@GetMapping("/del_hos_notice_by_id")
	@ResponseBody
	public JsonResult delHosNoticeById(String hosId) {
		JsonResult result = new JsonResult();
		try {
			HospitalNoticeService.delHospitalNoticeById(hosId);
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
}
