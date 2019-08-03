package com.his.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.bean.OpeNoticebean;
import com.his.pojo.OpeNotice;
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
	public List<OpeNotice> getallNotices(){
		return opeNoticeService.getallNotices();
	}
@ResponseBody
@GetMapping("getall_ope_noticebean")
    public String getList(){
	    System.out.println(opeNoticeService.getaNoticebeans());
    	return opeNoticeService.getaNoticebeans();
    }
}
