package com.his.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IEmpInformationDao;
import com.his.dao.IExaminationDao;
import com.his.dao.IOutMedicalRecordDao;
import com.his.dao.IOutpatientRegistrationDao;
import com.his.pojo.EmpInformation;
import com.his.pojo.Examination;
import com.his.pojo.History;
import com.his.pojo.JsonResult;
import com.his.pojo.OutMedicalRecord;
import com.his.pojo.OutpatientRegistration;
import com.his.pojo.Prescription;

import oracle.net.aso.e;

/**  
* @ClassName: ExaminationService  
* @Description: 体检Service
* @author Sbaby
* @date 2019年8月22日  上午11:21:51
*    
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class ExaminationService {

	@Autowired
	private IExaminationDao examinationDao;
	@Autowired
	private IOutMedicalRecordDao outMedicalRecordDao;
	@Autowired
	private IOutpatientRegistrationDao outpatientRegistrationDao;
	@Autowired
	private IEmpInformationDao empInformationDao;
	
	/**
	* @Title:initExamination
	* @Description:初始化体检表
	* @param:@param cardNum
	* @param:@param roomId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月22日 上午11:49:39
	 */
	public JsonResult initExamination(String cardNum, String roomId, String ygxh) {
		JsonResult result = new JsonResult();
		PageRequest page = PageRequest.of(0, 1);
    	// 先根据就诊卡号与候诊厅编号查询就诊卡是否是今日咋当前候诊厅的患者，并获取挂号信息
    	List<OutMedicalRecord> omrs = outMedicalRecordDao.getOutMedicalRecord(cardNum, roomId, page);
    	if(omrs.size() > 0) {
    		OutMedicalRecord omr = omrs.get(0);
			OutpatientRegistration outpatientRegistration = omr.getOutpatientRegistration();
			if(outpatientRegistration.getExamination() != null) {
				result.setResult(outpatientRegistration.getExamination());
				result.setStatus("ok");
			} else {
				EmpInformation empInformation = empInformationDao.findById(ygxh).get();
				Examination examination = new Examination();
				examination.setExamId(UUID.randomUUID().toString().replaceAll("-", ""));
				examination.setOutpatientRegistration(outpatientRegistration);
				examination.setHeat(new BigDecimal(0));
				examination.setPressure(new BigDecimal(0));
				examination.setSphygmus(new BigDecimal(0));
				examination.setPressureH(new BigDecimal(0));
				examination.setEmpInformation(empInformation);
				examinationDao.save(examination);
				outpatientRegistration.setExamination(examination);
				outpatientRegistrationDao.save(outpatientRegistration);
		        result.setResult(examination);
		        result.setStatus("ok");
			}
    	} else {
    		result.setResult("此患者未排队！");
    		result.setStatus("error");
    	}
    	return result;
	}
	
	/**
	* @Title:takeExam
	* @Description:做检查，填入检查数据
	* @param:@param examination
	* @return:void
	* @throws
	* @author:Sbaby
	* @Date:2019年8月22日 下午2:11:14
	 */
	public void takeExam(Examination examination) {
		examination.setExamTime(new Date());
		examinationDao.save(examination);
		OutpatientRegistration outpatientRegistration = examination.getOutpatientRegistration();
		outpatientRegistration.setExamination(examination);
		outpatientRegistrationDao.save(outpatientRegistration);
	}
	
	/**
	* @Title:getExamByRegId
	* @Description:根据挂号id查询体检
	* @param:@param regId
	* @param:@return
	* @return:Examination
	* @throws
	* @author:Sbaby
	* @Date:2019年8月22日 下午3:26:03
	 */
	public Examination getExamByRegId(String regId) {
		return examinationDao.getExamByRegId(regId);
	}
}
