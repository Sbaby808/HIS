package com.his.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.Roman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.HosOutNotice;
import com.his.pojo.JsonResult;
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
	 * @throws ParseException 
	* @throws
	* @author:Hamster
	* @Date:2019年8月4日 上午10:00:00
	 */
	@ResponseBody
	@GetMapping("/get_hos_out_notice_byPage")
	public Map getHosOutNoticeByPage(String start,String end,String text1,String text2,String text3,int curpage,int pagesize) throws ParseException{
		String cardName = "%"+text1+"%";
		String ksName = "%"+text2+"%";
		String roomName = "%"+text3+"%";
		return hosOutNoticeService.getHosOutNoticeByPage(start,end,cardName,ksName,roomName,curpage, pagesize);
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
	@PostMapping("/delete_hos_out_notice")
	public void delHosOutNotice(@RequestBody HosOutNotice outNotice){
		hosOutNoticeService.delHosOutNotice(outNotice);
	}
	
	/**
	 * 
	* @Title:checkMoney
	* @Description:出院结算
	* @param:@param outNotice
	* @return:void
	* @throws
	* @author:Hamster
	* @Date:2019年8月19日 上午9:56:30
	 */
	@ResponseBody
	@PostMapping("/check_money")
	public JsonResult checkMoney(@RequestBody HosOutNotice outNotice){
		JsonResult result = new JsonResult();
		try{
			result.setResult(hosOutNoticeService.checkMoney(outNotice));
			result.setStatus("ok");
		} catch (Exception e){
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
		
	}
}
