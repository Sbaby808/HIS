package com.his.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.HosDrugDetail;
import com.his.service.HosDrugDetailService;

/**
 * 
* @ClassName: HosDrugDetailController  
* @Description: 住院用药明细
* @author Hamster
* @date 2019年8月24日  下午4:46:59
*
 */

@Controller
public class HosDrugDetailController {

	@Autowired
	private HosDrugDetailService hosDrugDetailService;
	
	@ResponseBody
	@GetMapping("/get_hos_drug_detail")
	public Map getHosDrugDetail(String text1,String text2,String text3,int curpage,int pagesize){
		String cardName = "%"+text1+"%";
		String ksName = "%"+text2+"%";
		String roomName = "%"+text3+"%";
		return hosDrugDetailService.getHosDrugDetailbyPage(cardName,ksName,roomName,curpage, pagesize);
	}
	
	/**
	 * 
	* @Title:getHosDrugDetailbyDiagId
	* @Description:根据诊断记录id查询用药明细
	* @param:@param diagId
	* @param:@return
	* @return:List<HosDrugDetail>
	* @throws
	* @author:Hamster
	* @Date:2019年8月24日 下午4:51:48
	 */
	@ResponseBody
	@GetMapping("/get_hos_drug_detail_byDiagId")
	public List <HosDrugDetail> getHosDrugDetailbyDiagId(String diagId){
		return hosDrugDetailService.getHosDrugDetailbyDiagId(diagId);
	}
}
