package com.his.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.bean.OpeNoticebean;
import com.his.pojo.OpeNotice;
import com.his.pojo.OperationPay;
import com.his.pojo.Role;
import com.his.service.OpeNoticeService;

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
}