package com.his.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.CheckItem;
import com.his.pojo.CheckPay;
import com.his.pojo.JsonResult;
import com.his.service.CheckPayService;

/**  
* @ClassName: CheckPayController  
* @Description: TODO(检查缴费，划价controller)  
* @author TRC
* @date 2019年7月30日  上午9:43:16
*    
*/
@Controller
public class CheckPayController {

	@Autowired
	private CheckPayService checkPayService;
	
	@ResponseBody
	@GetMapping("/getall_checkpay")
	public Map getAllCheckPay(int curpage, int pagesize,String sou){
		return checkPayService.getallcheckpay(curpage,pagesize,"%"+sou+"%");
	}
	@ResponseBody
	@GetMapping("getall_checkitem")
	public List<CheckItem> getItems(){
		return checkPayService.getitems();
	}
	@ResponseBody
	@GetMapping("getname_byitemid")
	public String getnamebyitemid(String str) {
		return checkPayService.getnamebyitemid(str);
	}
	@ResponseBody
	@GetMapping("add_checkpay")
	public void addcheckpay(String str,String cname,String cdesc,BigDecimal cprice,String ygxh,String checkid) {
		checkPayService.addcheckpay(str,cname,cdesc,cprice,ygxh,checkid);
	}
	@ResponseBody
	@GetMapping("getitem_bycheck")
	public List<CheckItem> getcheitem(String checkid){
		return checkPayService.getbycheck(checkid);
	}
	@ResponseBody
	@GetMapping("del_check")
	public void delcheck(String checkid) {
		checkPayService.delcheck(checkid);
	}
	@ResponseBody
	@GetMapping("getcheckpayrecord")
	public Map getcheckpayrecord(int curpage, int pagesize,String sou) {
		return checkPayService.getcheckayrecord(curpage, pagesize, "%"+sou+"%");
	}
	
	/**
	* @Title:searchCheckPay
	* @Description:模糊查询检查项
	* @param:@param key
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月23日 下午9:27:45
	 */
	@GetMapping("/search_check_pay")
	@ResponseBody
	public JsonResult searchCheckPay(String key) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(checkPayService.searchCheckPay(key));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
}
