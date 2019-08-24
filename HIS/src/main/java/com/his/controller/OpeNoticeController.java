package com.his.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.bean.OpeNoticebean;
import com.his.pojo.JsonResult;
import com.his.pojo.OpeNotice;
import com.his.pojo.OperationPay;
import com.his.pojo.Role;
import com.his.service.OpeNoticeService;
import com.his.service.OperationPayService;

/**  
* @ClassName: OpeNoticeController  
* @Description: TODO(手术通知单controller)  
* @author TRC
* @date 2019年8月2日  上午11:58:01
*    
*/
@Controller
public class OpeNoticeController {
	@Autowired
	private OpeNoticeService opeNoticeService;
	@Autowired
	private OperationPayService operationPayService;
@ResponseBody
@GetMapping("getall_ope_notice")
	public Map getallNotices(int curpage, int pagesize,String sou){
		return opeNoticeService.getNoticebeans(curpage,pagesize,sou);
	}
@ResponseBody
@GetMapping("get_ope_notice_sou")
    public Map getnoticebysouu(String sou){
    	return opeNoticeService.getopenoticebysou(sou);
    }
@ResponseBody
@GetMapping("get_ope_pay_byid")
    public OperationPay getbyid(String opepayid){
    	return opeNoticeService.getOperationPaybyid(opepayid);
    }
@ResponseBody
@GetMapping("pay_fun")
    public void payfun(String brcard_id,String opepay_id,String ygxh,String noticeid) {
    	opeNoticeService.payfun(brcard_id, opepay_id,ygxh,noticeid);
    }
@ResponseBody
@GetMapping("aaaa")
    public List<OperationPay> geta(){
    	return opeNoticeService.getn();
    }
@ResponseBody
@GetMapping("bbbb")
    public List<Role> getallRoles(){
    	return opeNoticeService.getallRoles();
    }
@ResponseBody
@GetMapping("get_zhuyuannotice")
    public Map getzhuyuannotice(int curpage, int pagesize,String sou){
    	return opeNoticeService.getzhuyuannotice(curpage,pagesize,sou);
    }
@ResponseBody
@GetMapping("getbysou")
    public Map getbysou(int curpage, int pagesize,String sou) {
    	return opeNoticeService.getbysou(curpage, pagesize, sou);
    }

	/**
	* @Title:searchByKey
	* @Description:模糊查询手术项
	* @param:@param key
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月24日 上午9:40:26
	 */
	@GetMapping("/search_ope_by_key")
	@ResponseBody
	public JsonResult searchByKey(String key) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(operationPayService.searchByKey(key));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:addOpeNotice
	* @Description:添加手术通知项
	* @param:@param opeNotice
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月24日 上午9:54:21
	 */
	@PostMapping("/add_ope_notice")
	@ResponseBody
	public JsonResult addOpeNotice(@RequestBody OpeNotice opeNotice) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(opeNoticeService.addOpeNotice(opeNotice));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:getAllOpeNotice
	* @Description:查询所有手术通知项
	* @param:@param solveId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月24日 上午10:04:18
	 */
	@GetMapping("/get_all_ope_notice")
	@ResponseBody
	public JsonResult getAllOpeNotice(String solveId) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(opeNoticeService.getAllOpeNotice(solveId));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:delOpeNoticeById
	* @Description:根据id删除手术通知
	* @param:@param id
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月24日 上午10:11:19
	 */
	@GetMapping("/del_ope_notice_by_id")
	@ResponseBody
	public JsonResult delOpeNoticeById(String id) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(opeNoticeService.delById(id));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
}