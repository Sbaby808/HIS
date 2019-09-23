package com.his.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.dao.ForChart;
import com.his.dao.IDepartmentDao;
import com.his.dao.IEmpInformationDao;
import com.his.dao.IHosBedDao;
import com.his.dao.IHosEmpDao;
import com.his.dao.IHosOutDao;
import com.his.dao.IHosPatientDao;
import com.his.dao.IHosPayRecordDao;
import com.his.dao.IMedicalRecordDao;
import com.his.dao.IWardRoomDao;
import com.his.pojo.Department;
import com.his.pojo.EmpInformation;
import com.his.pojo.HosBed;
import com.his.pojo.HosEmp;
import com.his.pojo.HosEmpPK;
import com.his.pojo.HosPayRecord;
import com.his.pojo.HospitalizedPatient;
import com.his.pojo.MedicalRecord;
import com.his.pojo.OutHospitaiRecord;
import com.his.pojo.WardRoom;
/**
 * 
* @ClassName: HosPatientsService  
* @Description: 住院登记  
* @author Hamster
* @date 2019年9月18日  下午1:35:35
*
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class HosPatientsService {
	
	@Autowired
	private IHosPatientDao hosPatientDao;
	@Autowired
	private IHosBedDao hosBedDao;
	@Autowired
	private IWardRoomDao wardRoomDao;
	@Autowired
	private IMedicalRecordDao medicalRecordDao;
	@Autowired
	private IHosOutDao hosOutDao;
	@Autowired
	private IHosEmpDao hosEmpDao;
	@Autowired
	private IEmpInformationDao empInformationDao;
	@Autowired
	private IDepartmentDao departmentDao;
	@Autowired
	private IHosPayRecordDao hosPayRecordDao;
	
	/**
	 * 
	* @Title:getHosPatientsByPage
	* @Description:分页查询住院登记
	* @param:@param curpage
	* @param:@param pagesize
	* @param:@return
	* @return:Map
	* @throws
	* @author:Hamster
	* @Date:2019年8月9日 下午6:32:44
	 */
	public Map getHosPatientsByPage(String hospName,String ksName,String roomName,int curpage,int pagesize){
		List <HospitalizedPatient> patients = hosPatientDao.getAllPatientsByPage(hospName,ksName,roomName,PageRequest.of(curpage-1,pagesize));
		long total = hosPatientDao.countNum(hospName, ksName, roomName);
		Map map = new HashMap<>();
		map.put("list", patients);
		map.put("total", total);
		return map;
	}

	
	
	/**
	 * 
	* @Title:getPatientsByWroomId
	* @Description:根据病房id查询住院患者
	* @param:@param id
	* @param:@return
	* @return:List<HospitalizedPatient>
	* @throws
	* @author:Hamster
	* @Date:2019年8月1日 下午8:01:53
	 */
	public List <HospitalizedPatient> getPatientsByWroomId(String id){
		return hosPatientDao.getPatientsByWroomId(id);
	}
	
	/**
	 * 
	* @Title:addHosPatient
	* @Description:新增住院患者
	* @param:@param patient
	* @return:void
	* @throws
	* @author:Hamster
	* @Date:2019年8月1日 下午8:03:05
	 */
	public void addHosPatient(HospitalizedPatient patient){
			
	    MedicalRecord record = newMedicalRecord();
	   	
		patient.setHospState("在院");
		patient.setMedRid(record.getMedRid());
		patient.setMedicalRecord(record);
		patient.setHosBid(patient.getHosBed().getHosBid());
		patient.setHosBed(patient.getHosBed());
		patient.setRegisterTime(new Date());
		patient.setBalance(patient.getDepositMoney());
		hosPatientDao.save(patient);
		
		
		String bedId = patient.getHosBed().getHosBid();
		HosBed hosBed = hosBedDao.findById(bedId).get();
		hosBed.setHosBstate("已入住");
		hosBed.setHospitalizedPatient(patient);
		hosBedDao.save(hosBed);
		
		record.setMedInDept(patient.getDepartment().getKsName());
		record.setHospitalizedPatient(patient);
		medicalRecordDao.save(record);
		
		
		String roomId = patient.getHosBed().getWardRoom().getWroomId();
		WardRoom room = wardRoomDao.findById(roomId).get();
		room.setWNum(room.getWNum()+1);
		wardRoomDao.save(room);
		
		List <HosEmp> emps = patient.getHosEmps();
		for(int i=0;i<emps.size();i++){
			HosEmpPK pk = new HosEmpPK();
			pk.setHospId(patient.getHospId());
			pk.setYgxh(emps.get(i).getEmpInformation().getYgxh());
			emps.get(i).setId(pk);
			hosEmpDao.save(emps.get(i));
		}
			
	}
	
	/**
	 * 
	* @Title:newMedicalRecord
	* @Description:新增病案
	* @param:@return
	* @return:MedicalRecord
	* @throws
	* @author:Hamster
	* @Date:2019年8月17日 上午8:57:23
	 */
	public MedicalRecord newMedicalRecord(){	
		MedicalRecord record = new MedicalRecord();
		record.setMedInTime(new Date());
		medicalRecordDao.save(record);
		return record;
	}
	
	
	/**
	 * 
	* @Title:getPatientByBid
	* @Description:根据床位号查询登记信息
	* @param:@param bedId
	* @param:@return
	* @return:HospitalizedPatient
	* @throws
	* @author:Hamster
	* @Date:2019年8月4日 下午8:52:02
	 */
	public HospitalizedPatient getPatientByBid(String bedId){
		return hosPatientDao.getPatientByBid(bedId);
	}
	
	/**
	 * 
	* @Title:outHosPatient
	* @Description:出院记录
	* @param:@param hospId
	* @param:@throws ParseException
	* @return:void
	* @throws
	* @author:Hamster
	* @Date:2019年8月12日 下午10:28:48
	 */
	public void outHosPatient(String hospId,String empId) throws ParseException{
		HospitalizedPatient patient = hosPatientDao.findById(hospId).get();
		EmpInformation emp = empInformationDao.findById(empId).get();
		
		OutHospitaiRecord outRecord = new OutHospitaiRecord();
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = dateFormat.format(new Date());
		outRecord.setOutRecTime(new Date());
		outRecord.setHospitalizedPatient(patient);
		outRecord.setEmpInformation(emp);
		String departId = patient.getHosBed().getWardRoom().getWard().getDepartment().getKsId();
		Department department = departmentDao.findById(departId).get();
		outRecord.setOutDepart(department.getKsName());
		hosOutDao.save(outRecord);
		
		MedicalRecord record = patient.getMedicalRecord();
		record.setMedOutDept(patient.getHosBed().getWardRoom().getWard().getDepartment().getKsName());
		record.setMedOutTime(dateFormat.parse(time));
		medicalRecordDao.save(record);
		
		HosBed bed = patient.getHosBed();
		bed.setHosBstate(null);
		bed.setHospitalizedPatient(null);
		hosBedDao.save(bed);
		
		WardRoom room = patient.getHosBed().getWardRoom();
		room.setWNum(room.getWNum()-1);
		wardRoomDao.save(room);
			
		patient.setHosBid(null);
		patient.setHosBed(null);
		patient.setOutRid(outRecord.getOutRid());
		patient.setOutHospitaiRecord(outRecord);
		patient.setOutHospitaiRecord(outRecord);
		patient.setHospState("已出院");
		patient.setBalance(new BigDecimal(0));
		hosPatientDao.save(patient);
		
		
	}
	
	/**
	 * 
	* @Title:addDepositMoney
	* @Description:补交预交款
	* @param:@param hospId
	* @param:@param money
	* @return:void
	 * @throws ParseException 
	* @throws
	* @author:Hamster
	* @Date:2019年8月26日 上午11:24:41
	 */
	public void addDepositMoney(String hospId,String money,String empId) throws ParseException{
		HospitalizedPatient patient = hosPatientDao.findById(hospId).get();
		EmpInformation emp = empInformationDao.findById(empId).get();
		
		
		BigDecimal add = new BigDecimal(money);
		BigDecimal balance = add.add(patient.getBalance());
		patient.setBalance(balance);
		hosPatientDao.save(patient);
		
		HosPayRecord payRecord = new HosPayRecord();
		String payRecordId = UUID.randomUUID().toString().replace("-", "");
		payRecord.setHosPrid(payRecordId);
		payRecord.setHospitalizedPatient(patient);
		payRecord.setEmpInformation(emp);
		payRecord.setHosPrmoney(add);
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = dateFormat.format(new Date());
		payRecord.setHosPrtime(dateFormat.parse(time));
		hosPayRecordDao.save(payRecord);
	}
	
	/**
	 * 
	* @Title:checkBalance
	* @Description:实时监测余额
	* @param:
	* @return:void
	* @throws
	* @author:Hamster
	* @Date:2019年9月18日 下午7:26:55
	 */
	
	@Scheduled(cron="0 0/10 * * * ?")
	public void checkBalance(){
		List <HospitalizedPatient> patients = hosPatientDao.getHosInPatient();
		for(int i=0;i<patients.size();i++){
			BigDecimal balance = patients.get(i).getBalance();
			BigDecimal depositMoney = patients.get(i).getDepositMoney();
			BigDecimal percent = new BigDecimal(0.05);
			if(balance.compareTo(depositMoney.multiply(percent)) == -1){
				patients.get(i).setHosNote("余额不足,请充值");
			}
			else{
				patients.get(i).setHosNote(null);
			}
		}
	}
	
	/**
	 * 
	* @Title:countForCharts
	* @Description:入院图表统计
	* @param:@return
	* @return:List<BigDecimal>
	* @throws
	* @author:Hamster
	* @Date:2019年8月31日 下午9:37:40
	 */
	public List<BigDecimal> countForCharts(){
		List object = hosPatientDao.countForCharts();
		List<ForChart> list = new ArrayList();
		for (Object row : object) {
			Object[] cells = (Object[]) row;
			ForChart result= new ForChart();
			result.setTime((String) cells[0]);
			result.setNum((BigDecimal) cells[1]);
			list.add(result);
		}
        List<BigDecimal> numbers=list.stream().map(ForChart::getNum).collect(Collectors.toList());
        return numbers;
	}
	
	public List <HospitalizedPatient> getPatientsByroomId(String roomId){
		return hosPatientDao.getPatientsByroomId(roomId);
	}
	
}
