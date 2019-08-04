package com.his.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.JsonResult;
import com.his.pojo.OtherProject;
import com.his.service.OtherProjectService;

/**  
* @ClassName: OtherProjectController  
* @Description: 其他收费项控制器
* @author Sbaby
* @date 2019年8月3日  上午9:10:24
*    
*/
@Controller
public class OtherProjectController {

	@Autowired
	private OtherProjectService otherProjectService;
	
	/**
	* @Title:addOtherProject
	* @Description:添加其他收费项
	* @param:@param otherProject
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月3日 上午9:13:11
	 */
	@PostMapping("/add_other_project")
	@ResponseBody
	public JsonResult addOtherProject(OtherProject otherProject) {
		System.out.println(otherProject.getProjectName());
		JsonResult result = new JsonResult();
		try {
			otherProjectService.addOtherProject(otherProject);
			result.setResult(otherProject);
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(otherProject);
			result.setStatus("error");
		}
		return result;
	}
	
}
