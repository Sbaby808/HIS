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

import com.his.pojo.HospitalizedPatient;
import com.his.service.HosPatientsService;

import oracle.jdbc.proxy.annotation.Post;

/**
 * 
* @ClassName: HosPatientController  
* @Description: 住院登记 
* @author Hamster
* @date 2019年8月4日  下午8:48:39
*
 */
@Controller
public class HosPatientController {
	
	@Autowired
	private HosPatientsService hosPatientsService;
	
	/**
	 * 
	* @Title:getHosPatientsByPage
	* @Description:分页查询所有登记信息
	* @param:@return
	* @return:List<HospitalizedPatient>
	* @throws
	* @author:Hamster
	* @Date:2019年8月4日 下午8:48:57
	 */
	@ResponseBody
	@GetMapping("/get_hos_patients_byPage")
	public Map getHosPatientsByPage(int curpage,int pagesize){
		return hosPatientsService.getHosPatientsByPage(curpage, pagesize);
	}
	
	/**
	 * 
	* @Title:getPatientsByWroomId
	* @Description:根据病房id查询登记信息
	* @param:@param wroomId
	* @param:@return
	* @return:List<HospitalizedPatient>
	* @throws
	* @author:Hamster
	* @Date:2019年8月4日 下午8:49:15
	 */
	@ResponseBody
	@GetMapping("/get_hos_patients_byWid")
	public List <HospitalizedPatient> getPatientsByWroomId(String wroomId){
		return hosPatientsService.getPatientsByWroomId(wroomId);
	}
	
	/**
	 * 
	* @Title:addHosPatient
	* @Description:入院登记
	* @param:@param patient
	* @return:void
	* @throws
	* @author:Hamster
	* @Date:2019年8月4日 下午8:49:56
	 */
	@ResponseBody
	@PostMapping("/add_hos_patient")
	public void addHosPatient(@RequestBody HospitalizedPatient patient){
		hosPatientsService.addHosPatient(patient);
	}
	
	
	/**
	 * 
	* @Title:getPatientByBid
	* @Description:根据床位id查询登记信息
	* @param:@param bedId
	* @param:@return
	* @return:HospitalizedPatient
	* @throws
	* @author:Hamster
	* @Date:2019年8月4日 下午8:53:18
	 */
	@ResponseBody
	@GetMapping("/get_hos_patient_by_bedId")
	public HospitalizedPatient getPatientByBid(String bedId){
		return hosPatientsService.getPatientByBid(bedId);
	}
	
	@ResponseBody
	@GetMapping("/out_hos_patient")
	public void outHosPatient(String hospId) throws ParseException{
		hosPatientsService.outHosPatient(hospId);
	}
}
