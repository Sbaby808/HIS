package com.his.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.HosOutNotice;
import com.his.service.HosOutNoticeService;

@Controller
public class HosOutNoticeController {

	@Autowired
	private HosOutNoticeService hosOutNoticeService;
	
	/**
	 * 
	* @Title:getAllHosOutNotic
	* @Description:获取所有出院通知单
	* @param:@return
	* @return:List<HosOutNotice>
	* @throws
	* @author:Hamster
	* @Date:2019年8月4日 上午10:00:00
	 */
	@ResponseBody
	@GetMapping("/get_hos_out_notice_byPage")
	public Map getHosOutNoticeByPage(int curpage,int pagesize){
		return hosOutNoticeService.getHosOutNoticeByPage(curpage, pagesize);
	}
	
	/**
	 * 
	* @Title:delHosOutNotice
	* @Description:删除出院通知单
	* @param:@param outNotice
	* @return:void
	* @throws
	* @author:Hamster
	* @Date:2019年8月4日 上午10:00:17
	 */
	@ResponseBody
	@GetMapping("/delete_hos_out_notice")
	public void delHosOutNotice(@RequestBody HosOutNotice outNotice){
		hosOutNoticeService.delHosOutNotice(outNotice);
	}
}
