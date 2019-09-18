package com.his.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Payload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IHosOutNoticeDao;
import com.his.dao.IHosPatientDao;
import com.his.pojo.HosDrugCost;
import com.his.pojo.HosOtherCost;
import com.his.pojo.HosOutNotice;
import com.his.pojo.HosPrescription;
import com.his.pojo.HospitalizedPatient;

@Service
@Transactional(rollbackFor=Exception.class)
public class HosOutNoticeService {

	@Autowired
	private IHosOutNoticeDao hosOutNoticeDao;
	@Autowired
	private IHosPatientDao hosPatientDao;
	
	/**
	 * 
	* @Title:getAllHosOutNotic
	* @Description:查询所有出院通知单
	* @param:@return
	* @return:List<HosOutNotice>
	 * @throws ParseException 
	* @throws
	* @author:Hamster
	* @Date:2019年8月3日 上午11:32:51
	 */
	public Map getHosOutNoticeByPage(String start,String end,String cardName,String ksName,String roomName,int curpage,int pagesize) throws ParseException{
		List <HosOutNotice> list;
		long total;
		if(start==null||end==null){
			list = hosOutNoticeDao.getHosOutNoticeByPage(cardName, ksName, roomName, PageRequest.of(curpage-1, pagesize));
			total = hosOutNoticeDao.countNum1(cardName, ksName, roomName);
		}
		else{
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			list = hosOutNoticeDao.getHosOutNoticeByPageandTime(format.parse(start), format.parse(end), cardName, ksName, roomName, PageRequest.of(curpage-1, pagesize));
			total = hosOutNoticeDao.countNum2(format.parse(start), format.parse(end), cardName, ksName, roomName);
		}
		Map map = new HashMap<>();
		map.put("list", list);
		map.put("total", total);
		return map;
	}
	
	/**
	 * 
	* @Title:delHosOutNotice
	* @Description:删除出院通知单
	* @param:@param outNotice
	* @return:void
	* @throws
	* @author:Hamster
	* @Date:2019年8月4日 上午9:57:09
	 */
	public void delHosOutNotice(HosOutNotice outNotice){
		hosOutNoticeDao.delete(outNotice);
	}
	
	/**
	 * 
	* @Title:checkMoney
	* @Description:出院对账
	* @param:@param outNotice
	* @param:@return
	* @return:boolean
	* @throws
	* @author:Hamster
	* @Date:2019年8月19日 上午10:16:52
	 */
	
	public boolean checkMoney(HosOutNotice outNotice){
		
		String hospId = outNotice.getHosDoctorAdvice().getHosDiagnosticRecord().getMedicalRecord().getHospitalizedPatient().getHospId();
		HospitalizedPatient patient = hosPatientDao.findById(hospId).get();
		BigDecimal depositMoney = patient.getDepositMoney();
		BigDecimal balance  = patient.getBalance();
		
		BigDecimal drugCost = new BigDecimal("0") ;
		for(int i=0;i<patient.getMedicalRecord().getHosDiagnosticRecords().size();i++){
			HosPrescription prescriptions = patient.getMedicalRecord().getHosDiagnosticRecords().get(i).getHosPrescription();
			if(prescriptions.getHosPreMoney()!=null){
				drugCost = drugCost.add(prescriptions.getHosPreMoney());
			}
		}
		
		BigDecimal otherCost = new BigDecimal("0");
		List <HosOtherCost> otherCosts = patient.getMedicalRecord().getHosOtherCosts();
		for(int j=0;j<otherCosts.size();j++){
			otherCost = otherCost.add(otherCosts.get(j).getOtherProject().getProjectPrice());
		}
		
		BigDecimal totalCost = drugCost.add(otherCost);
		
		if(depositMoney.subtract(balance).compareTo(totalCost)==0){
			return true;
		}
		else{
			return false;
		}
	}
}
