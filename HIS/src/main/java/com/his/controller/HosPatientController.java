package com.his.controller;

import java.math.BigDecimal;
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
	public Map getHosPatientsByPage(String text1,String text2,String text3,int curpage,int pagesize){
		String hospName = "%"+text1+"%";
		String ksName = "%"+text2+"%";
		String roomName = "%"+text3+"%";
		return hosPatientsService.getHosPatientsByPage(hospName,ksName,roomName,curpage, pagesize);
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
	
	/**
	 * 
	* @Title:outHosPatient
	* @Description:出院登记
	* @param:@param hospId
	* @param:@throws ParseException
	* @return:void
	* @throws
	* @author:Hamster
	* @Date:2019年8月19日 下午7:30:14
	 */
	@ResponseBody
	@GetMapping("/out_hos_patient")
	public void outHosPatient(String hospId,String empId) throws ParseException{
		hosPatientsService.outHosPatient(hospId,empId);
	}
	
	/**
	 * 
	* @Title:checkBalance
	* @Description:定时检测账户余额
	* @param:
	* @return:void
	* @throws
	* @author:Hamster
	* @Date:2019年8月26日 上午11:27:09
	 */
	@ResponseBody
	@GetMapping("/check_balance")
	public void checkBalance(){
		hosPatientsService.checkBalance();
	}
	
	/**
	 * 
	* @Title:addDepositMoney
	* @Description:缴纳预交款
	* @param:@param hospId
	* @param:@param money
	* @return:void
	 * @throws ParseException 
	* @throws
	* @author:Hamster
	* @Date:2019年8月26日 上午11:28:29
	 */
	@ResponseBody
	@GetMapping("/add_deposit_money")
	public void addDepositMoney(String hospId,String money,String empId) throws ParseException{
		hosPatientsService.addDepositMoney(hospId, money,empId);
	}
	
	@ResponseBody
	@GetMapping("/get_numbers_for_charts")
	public List <BigDecimal> countForCharts(){
		return hosPatientsService.countForCharts();
	}
}