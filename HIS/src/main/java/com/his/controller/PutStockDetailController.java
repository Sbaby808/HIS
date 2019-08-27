package com.his.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.JsonResult;
import com.his.service.PutStockDetailsService;

/**  
* @ClassName: PutStockDetailController  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author crazy_long
* @date 2019年8月19日  下午12:11:45
*    
*/
@Controller
public class PutStockDetailController {
	
	@Autowired
	private PutStockDetailsService putStockDetailsService;
	
	/**
	* @Title:get_putStock_by_page
	* @Description:分页获取入库记录
	* @param:@param ygxh
	* @param:@param rkTime
	* @param:@param curPage
	* @param:@param pageSize
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年8月19日 上午10:22:45
	 */
	@ResponseBody
	@GetMapping("get_putStockDetail_by_page")
	public JsonResult get_putStockDetail_by_page(String ygxh,long rkTime,long fisrtTime,long lastTime,int curPage,int pageSize) {
		System.out.println("----------------------------------");
		System.err.println("ygxh:"+ygxh);
		System.out.println("rkTime:"+rkTime);
		System.out.println("fisrtTime:"+fisrtTime);
		System.out.println("lastTime:"+lastTime);
		System.out.println("curPage"+curPage);
		System.out.println("pageSize"+pageSize);
		if("".equals(ygxh)) {
			System.out.println("员工序号为空字符串");
		}
		JsonResult jsonResult = new JsonResult();
		try {
			jsonResult.setResult(putStockDetailsService.queryPutstockRecordByPage(ygxh,rkTime,fisrtTime,lastTime,curPage, pageSize));
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}
	
}
