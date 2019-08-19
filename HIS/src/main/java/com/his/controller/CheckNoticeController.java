package com.his.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.bean.Checknoticebean;
import com.his.pojo.CheckNoticeForm;
import com.his.pojo.CheckPay;
import com.his.pojo.CheckResultForm;
import com.his.service.CheckNoticeService;

/**  
* @ClassName: CheckNoticeController  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author TRC
* @date 2019年8月13日  下午4:11:54
*    
*/
@Controller
public class CheckNoticeController {
	@Autowired
	private CheckNoticeService checkNoticeService;
	/**
	 * 
	* @Title:getlist
	* @Description:TODO获得门诊通知单
	* @param:@param curpage
	* @param:@param pagesize
	* @param:@param sou
	* @param:@return
	* @return:Map
	* @throws
	* @author:TRC
	* @Date:2019年8月14日 下午3:04:02
	 */
	@ResponseBody
	@GetMapping("getall_checknotice")
	public Map getlist(int curpage, int pagesize,String sou){
		return checkNoticeService.get(curpage,pagesize,sou);
	}
	@ResponseBody
	@GetMapping("get_checkmmoney")
	public BigDecimal getmoneybyid(String checkpayid) {
		return checkNoticeService.getmoneybyid(checkpayid);
	}
	@ResponseBody
	@GetMapping("get_checkpaybyid")
	public List<CheckPay> getbyid(String str){
		return checkNoticeService.getbyid(str);
	}
	@ResponseBody
	@GetMapping("check_payfun")
	public void checkpayfun(String brcard_id,String checkpayid,String noticeid,String ygxh) {
	     checkNoticeService.checkpayfun(brcard_id, checkpayid, noticeid, ygxh);
	}
	@ResponseBody
	@GetMapping("check_pipayfun")
	public void pivheckpayfun(String brcard_id,String checkpayid,String noticeid,String ygxh) {
		checkNoticeService.picheckfun(brcard_id, checkpayid, noticeid, ygxh);
	}
	@ResponseBody
	@GetMapping("getcheckpay")
	public List<CheckPay> getPays(){
		return checkNoticeService.getPays();
	}
	@ResponseBody
	@GetMapping("getcheckrecord")
	public Map getresule(String card_id,String cheid){
		return checkNoticeService.getcheckresult(card_id, cheid);
	}
	@ResponseBody
	@GetMapping("fuzhi")
	public Map fuzhi(int changdu,String cheitemid){
		return checkNoticeService.fuzhi(changdu,cheitemid);
	}
	@ResponseBody
	@GetMapping("Addcheckrecord")
	public void addcheckrecord(String card_id,String cheid,String itemid,String itemval,String beizhu,String ygxh) {
		checkNoticeService.addcheckrecord(card_id, cheid, itemid, itemval, beizhu, ygxh);
	}
	@ResponseBody
	@GetMapping("kkkk")
	public List<CheckResultForm> getform(){
		return checkNoticeService.getform();
	}
  
}
