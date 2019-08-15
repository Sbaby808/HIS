package com.his.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.HosPrescription;
import com.his.service.HosPrescriptionService;

/**
 * 
* @ClassName: HosPrescriptonService  
* @Description: 住院处方  
* @author Hamster
* @date 2019年8月5日  下午7:23:53
*
 */
@Controller
public class HosPrescriptonController {

	@Autowired
	private HosPrescriptionService hosPrescriptionService;
	
	/**
	 * 
	* @Title:getAllHosPrescription
	* @Description:无分页查询所有住院处方
	* @param:@return
	* @return:List<HosPrescription>
	* @throws
	* @author:Hamster
	* @Date:2019年8月5日 下午7:25:33
	 */
	@ResponseBody
	@GetMapping("/get_hos_prescription")
	public List <HosPrescription> getAllHosPrescription(){
		return hosPrescriptionService.getAllHosPrescription();
	}
	
	@ResponseBody
	@GetMapping("/get_hos_prescription_byPage")
	public Map getHosPrescriptionByPage(int curpage,int pagesize){
		return hosPrescriptionService.getHosPrescriptionByPage(curpage, pagesize);
	}
	
	/**
	 * 
	* @Title:getHosPresByDiagId
	* @Description:根据诊断记录id查询处方
	* @param:@param diagId
	* @param:@return
	* @return:HosPrescription
	* @throws
	* @author:Hamster
	* @Date:2019年8月7日 下午9:39:05
	 */
	@ResponseBody
	@GetMapping("/get_hos_prescription_byDiagId")
	public HosPrescription getHosPresByDiagId(String diagId){
		return hosPrescriptionService.getHosPresByDiagId(diagId);
	}
	
	/**
	 * 
	* @Title:addHosPrescription
	* @Description:新增住院处方
	* @param:@param hosPrescription
	* @param:@throws ParseException
	* @return:void
	* @throws
	* @author:Hamster
	* @Date:2019年8月8日 下午8:50:02
	 */
	@ResponseBody
	@PostMapping("/add_hos_prescription")
	public void addHosPrescription(@RequestBody HosPrescription hosPrescription) throws ParseException{
		hosPrescriptionService.addHosPrescription(hosPrescription);
	}
}
