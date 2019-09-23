package com.his.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.JsonResult;
import com.his.pojo.Outstock;
import com.his.service.OutstockService;

/**  
* @ClassName: OutstockController  
* @Description: 药库出库Controller
* @author crazy_long
* @date 2019年9月1日  下午11:20:30
*    
*/

@Controller
public class OutstockController {
	
	@Autowired
	private OutstockService outstockService;
	
	/**
	* @Title:get_all_outStock_by_page
	* @Description:分页获取所有的出库明细
	* @param:@param curPage
	* @param:@param pageSize
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月21日 下午12:53:05
	 */
	@ResponseBody
	@GetMapping("get_all_outStock_by_page")
	public JsonResult get_all_outStock_by_page(int curPage,int pageSize) {
		JsonResult josnResult = new JsonResult();
		try {
			josnResult.setResult(outstockService.getAllOutstockByPage(curPage, pageSize));
			josnResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			josnResult.setStatus("error");
		}
		return josnResult;
	}
	
	/**
	* @Title:add_a_outstock
	* @Description:添加出库单
	* @param:@param outstock
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月2日 上午10:37:27
	 */
	@ResponseBody
	@PostMapping("add_a_outstock")
	public JsonResult add_a_outstock(@RequestBody Outstock outstock) {
		JsonResult josnResult = new JsonResult();
		try {
			outstockService.addOutScotck(outstock);
			josnResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			josnResult.setStatus("error");
		}
		return josnResult;
	}
	
	/**
	* @Title:outstock_isexits
	* @Description:判断出库单是否已经存在
	* @param:@param req_id
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月1日 下午11:24:11
	 */
	@ResponseBody
	@GetMapping("outstock_isexits")
	public JsonResult outstock_isexits(String req_id) {
		JsonResult josnResult = new JsonResult();
		try {
			josnResult.setResult(outstockService.isExitsOutstock(req_id));
			josnResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			josnResult.setStatus("error");
		}
		return josnResult;
	}

}
