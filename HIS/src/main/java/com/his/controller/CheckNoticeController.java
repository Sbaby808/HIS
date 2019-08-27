package com.his.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.bean.Checknoticebean;
import com.his.pojo.CheckNoticeForm;
import com.his.pojo.CheckPay;
import com.his.pojo.CheckResultForm;
import com.his.pojo.JsonResult;
import com.his.service.CheckNoticeService;

import oracle.jdbc.proxy.annotation.Post;

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
	@ResponseBody
	@GetMapping("getall_zhuchecknotice")
	public Map getzhuyuanchecknotice(int curpage, int pagesize,String sou) {
		return checkNoticeService.getZhuyuanchecknotices(curpage,pagesize,sou);
	}
	@ResponseBody
	@GetMapping("get_twoweima")
	public Map gettwoweima(String checkpayid) {
		return checkNoticeService.gettwoeima(checkpayid);
	}
	@ResponseBody
	@GetMapping("pandinpay")
	public boolean whetherpay(String outTradeNo) {
		return checkNoticeService.whetherpay(outTradeNo);
	}
	
	/**
	* @Title:getAllCheckPay
	* @Description:查询所有检查项
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月23日 下午5:45:06
	 */
	@GetMapping("/get_all_check_item")
	@ResponseBody
	public JsonResult getAllCheckPay() {
		JsonResult result = new JsonResult();
		try {
			result.setResult(checkNoticeService.getAllCheckPay());
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:addCheckNotice
	* @Description:添加或修改检查通知
	* @param:@param checkNoticeForm
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月23日 下午11:35:46
	 */
	@PostMapping("/add_checkNotice")
	@ResponseBody
	public JsonResult addCheckNotice(@RequestBody CheckNoticeForm checkNotice) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(checkNoticeService.addCheckNotice(checkNotice));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:getAllCheckNotice
	* @Description:根据医嘱编号查询所有检查通知项
	* @param:@param solveId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月23日 下午11:48:06
	 */
	@GetMapping("/get_all_checkNotice_by_solveId")
	@ResponseBody
	public JsonResult getAllCheckNotice(String solveId) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(checkNoticeService.getAllCheckNotice(solveId));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:getAllCheckNoticeByHistoryId
	* @Description:根据诊断记录编号查询检查通知项
	* @param:@param historyId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月25日 下午1:49:23
	 */
	@GetMapping("/get_all_checkNotice_by_historyId")
	@ResponseBody
	public JsonResult getAllCheckNoticeByHistoryId(String historyId) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(checkNoticeService.getAllCheckNoticeByHistoryId(historyId));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:delCheckNotice
	* @Description:删除检查通知项
	* @param:@param checkNoticeId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月24日 上午12:10:47
	 */
	@GetMapping("/del_checknotice_by_id")
	@ResponseBody
	public JsonResult delCheckNotice(String checkNoticeId) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(checkNoticeService.delCheckNotice(checkNoticeId));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
  
}
