package com.his.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.Examination;
import com.his.pojo.JsonResult;
import com.his.service.ExaminationService;

/**  
* @ClassName: ExaminationController  
* @Description: 体检控制器
* @author Sbaby
* @date 2019年8月22日  上午11:22:44
*    
*/
@Controller
public class ExaminationController {

	@Autowired
	private ExaminationService examinationService;
	
	/**
	* @Title:initExamination
	* @Description:初始化体检表
	* @param:@param cardNum
	* @param:@param roomId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月22日 上午11:49:54
	 */
	@GetMapping("/init_examination")
	@ResponseBody
	public JsonResult initExamination(String cardNum, String roomId) {
		JsonResult result = new JsonResult();
		try {
			result = examinationService.initExamination(cardNum, roomId);
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult("生成体检单错误！");
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:takeExam
	* @Description:做检查
	* @param:@param examination
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月22日 下午2:10:17
	 */
	@PostMapping("/take_exam")
	@ResponseBody
	public JsonResult takeExam(@RequestBody Examination examination) {
		JsonResult result = new JsonResult();
		try {
			examinationService.takeExam(examination);
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
}
