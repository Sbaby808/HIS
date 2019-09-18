package com.his.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.JsonResult;
import com.his.service.UserRoleService;

/**  
* @ClassName: UserRoleController  
* @Description: 员工角色controller
* @author crazy_long
* @date 2019年9月15日  下午3:54:47
*    
*/
@Controller
public class UserRoleController {
	
	@Autowired
	private UserRoleService userRoleService;
	
	/**
	* @Title:get_yk_manager
	* @Description:获取药库管理员
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月15日 下午3:57:58
	 */
	@ResponseBody
	@GetMapping("get_yk_manager")
	public JsonResult get_yk_manager() {
		JsonResult result = new JsonResult();
		try {
			result.setResult(userRoleService.getYKManager());
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}

}
