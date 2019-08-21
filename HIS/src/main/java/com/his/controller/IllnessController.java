package com.his.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.Illness;
import com.his.pojo.JsonResult;
import com.his.service.IllnessService;

/**  
* @ClassName: IllnessController  
* @Description: 疾病控制器
* @author Sbaby
* @date 2019年8月21日  下午3:24:56
*    
*/
@Controller
public class IllnessController {

	@Autowired
	private IllnessService illnessService;
	
	/**
	* @Title:batchImportIllness
	* @Description:批量导入疾病信息
	* @param:@param list
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月21日 下午3:31:20
	 */
	@PostMapping("/batch_import_illness")
	@ResponseBody
	public JsonResult batchImportIllness(@RequestBody List<Illness> list) {
		JsonResult result = new JsonResult();
		try {
			illnessService.batchImpoet(list);
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
}
