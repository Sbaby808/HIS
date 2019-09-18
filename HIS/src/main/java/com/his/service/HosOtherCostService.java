package com.his.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IHosOtherCostDao;
import com.his.dao.IHosPatientDao;
import com.his.dao.IMedicalRecordDao;
import com.his.dao.IOtherProjectDao;
import com.his.pojo.HosOtherCost;
import com.his.pojo.HosOutNotice;
import com.his.pojo.HospitalizedPatient;
import com.his.pojo.MedicalRecord;
import com.his.pojo.OtherProject;
/**
 * 
* @ClassName: HosOtherCostService  
* @Description: 住院其他扣费记录  
* @author Hamster
* @date 2019年8月17日  上午11:03:50
*
 */
@Component
@Service
@Transactional(rollbackFor=Exception.class)
public class HosOtherCostService {

	@Autowired
	private IHosOtherCostDao hosOtherCostDao;
	@Autowired
	private IHosPatientDao hosPatientDao;
	@Autowired
	private IOtherProjectDao otherProjectDao;
	@Autowired
	private IMedicalRecordDao medicalRecordDao;
	
	/**
	 * 
	* @Title:getAllOtherCostByPage
	* @Description:分页查询
	* @param:@param curpage
	* @param:@param pagesize
	* @param:@return
	* @return:Map
	* @throws
	* @author:Hamster
	* @Date:2019年8月17日 上午11:05:06
	 */
	public Map getAllOtherCostByPage(String cardName,int curpage,int pagesize){
		List <HosOtherCost> list = hosOtherCostDao.getAllOtherCostByPage(cardName,PageRequest.of(curpage-1, pagesize));
		long total = list.size();
		Map map = new HashMap<>();
		map.put("list", list);
		map.put("total", total);
		return map;
	}
	
	/**
	 * 
	* @Title:getHosOtherCost
	* @Description:无分页查询
	* @param:@return
	* @return:List<HosOtherCost>
	* @throws
	* @author:Hamster
	* @Date:2019年8月17日 上午11:04:52
	 */
	public List <HosOtherCost> getHosOtherCost(){
		return hosOtherCostDao.getHosOtherCost();
	}
	
	/**
	 * 
	* @Title:getAllHosOtherCosts
	* @Description:患者所有的其他扣费记录(用于出院对账)
	* @param:@param outNotice
	* @param:@return
	* @return:List<HosOtherCost>
	* @throws
	* @author:Hamster
	* @Date:2019年8月20日 上午8:52:56
	 */
	public List <HosOtherCost> getAllHosOtherCosts(HosOutNotice outNotice){
		String recordId = outNotice.getHosDoctorAdvice().getHosDiagnosticRecord().getMedicalRecord().getMedRid();
		List <HosOtherCost> list = hosOtherCostDao.getAllOtherCostbyRid(recordId);
		return list;
	}
	
	/**
	 * 
	* @Title:addHosOtherCost
	* @Description:定时扣取床位费
	* @param:@throws ParseException
	* @return:void
	* @throws
	* @author:Hamster
	* @Date:2019年8月20日 上午9:17:34
	 */
	
	@Scheduled(cron = "0 00 09 ? * *")
	public void addHosOtherCost() throws ParseException{
		List <HospitalizedPatient> patients = hosPatientDao.getHosInPatient();
		
		for(int i=0;i<patients.size();i++){
			String projectId = patients.get(i).getHosBed().getOtherProject().getProjectId();
			OtherProject otherProject = otherProjectDao.findById(projectId).get();
			BigDecimal bedCost = otherProject.getProjectPrice();
			BigDecimal balance = patients.get(i).getBalance();
			patients.get(i).setBalance(balance.subtract(bedCost));
			hosPatientDao.save(patients.get(i));
			
			HosOtherCost otherCost = new HosOtherCost();
			String medRid = patients.get(i).getMedicalRecord().getMedRid();
			MedicalRecord record = medicalRecordDao.findById(medRid).get();
			otherCost.setMedicalRecord(record);
			otherCost.setOtherProject(otherProject);
			SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = dateFormat.format(new Date());
			otherCost.setHosOtime(dateFormat.parse(time));
			hosOtherCostDao.save(otherCost);
		}
	}
}
