package com.his.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.CheckItem;
import com.his.service.CheckItemService;

/**  
* @ClassName: CheckItemController  
* @Description: TODO(检查小项controller)  
* @author TRC
* @date 2019年8月12日  上午8:54:34
*    
*/
@Controller
public class CheckItemController {
	@Autowired
	private CheckItemService checkItemService;
	/**
	 * 
	* @Title:AddcheckItem
	* @Description:TODO添加检查小项
	* @param:@param list
	* @return:void
	* @throws
	* @author:TRC
	* @Date:2019年8月12日 上午9:47:29
	 */
	@ResponseBody
	@PostMapping("add_checkitem")
	public void AddcheckItem(@RequestBody List<CheckItem> list) {
		checkItemService.AddCheckItem(list);
	}
	@ResponseBody
	@GetMapping("getall_item")
	public Map getallItems(int curpage, int pagesize,String sou){
		return checkItemService.getallcheck(curpage,pagesize,"%"+sou+"%");
	}
	

}
