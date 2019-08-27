package com.his.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.HosOutNotice;
import com.his.pojo.HosPrescriptionDetail;
import com.his.pojo.LastDrugDetail;
import com.his.service.HosPreDetailService;

/**
 * 
* @ClassName: HosPreDetailController  
* @Description: 住院处方明细 
* @author Hamster
* @date 2019年8月6日  上午10:52:25
*
 */
@Controller
public class HosPreDetailController {

	@Autowired
	private HosPreDetailService hosPreDetailService;
	
	/**
	 * 
	* @Title:getHosPreDetail
	* @Description:无分页查询
	* @param:@return
	* @return:List<HosPrescriptionDetail>
	* @throws
	* @author:Hamster
	* @Date:2019年8月6日 上午10:55:03
	 */
	@ResponseBody
	@GetMapping("/get_hos_pre_detail")
	public List <HosPrescriptionDetail> getHosPreDetail(){
		return hosPreDetailService.getHosPreDetail();
	}
	
	@ResponseBody
	@GetMapping("/get_hos_pre_detail_byPage")
	public Map getHosPreDetailByPage(String text1,int curpage,int pagesize){
		String cardName = "%"+text1+"%";
		return hosPreDetailService.getHosPreDetailByPage(cardName,curpage, pagesize);
	}
	
	/**
	 * 
	* @Title:getHosPreDetailByPid
	* @Description:根据处方id获取处方明细
	* @param:@param pid
	* @param:@return
	* @return:List<HosPrescriptionDetail>
	* @throws
	* @author:Hamster
	* @Date:2019年8月6日 上午11:32:28
	 */
	@ResponseBody
	@GetMapping("/get_hos_pre_detail_byPid")
	public List <HosPrescriptionDetail> getHosPreDetailByPid(String pid){
		return hosPreDetailService.getHosPreDetailByPid(pid);
	}
	
	/**
	 * 
	* @Title:getHosPreDetailByDiagId
	* @Description:根据诊断记录查询处方明细
	* @param:@param diagId
	* @param:@return
	* @return:List<LastDrugDetail>
	* @throws
	* @author:Hamster
	* @Date:2019年8月19日 上午11:00:17
	 */
	@ResponseBody
	@GetMapping("/get_hos_pre_detail_byDiagId")
	public List <LastDrugDetail> getHosPreDetailByDiagId(String diagId){
		return hosPreDetailService.getHosPreDetailByDiagId(diagId);
	}
	
	@ResponseBody
	@PostMapping("/get_all_hos_pre_details")
	public List <HosPrescriptionDetail> getAllHosPreDetails(@RequestBody HosOutNotice outNotice){
		return hosPreDetailService.getAllHosPreDetails(outNotice);
	}
	
	
}
