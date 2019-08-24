package com.his.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IHistoryDao;
import com.his.dao.IIllnessDao;
import com.his.dao.IOutMedicalRecordDao;
import com.his.dao.IOutPreItemDao;
import com.his.dao.IOutpatientRegistrationDao;
import com.his.dao.IPrescriptionDao;
import com.his.dao.ISolveSchemeDao;
import com.his.pojo.History;
import com.his.pojo.Illness;
import com.his.pojo.JsonResult;
import com.his.pojo.OutMedicalRecord;
import com.his.pojo.OutPreItem;
import com.his.pojo.OutPreItemPK;
import com.his.pojo.OutpatientRegistration;
import com.his.pojo.Prescription;
import com.his.pojo.SolveScheme;
import com.his.utils.SimpleTools;

/**
 * @Author Sbaby
 * @Date 2019/08/20 11:57
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class HistoryService {

    @Autowired
    private IHistoryDao historyDao;
    @Autowired
    private IOutpatientRegistrationDao outpatientRegistrationDao;
    @Autowired
    private IOutMedicalRecordDao outMedicalRecordDao;
    @Autowired
    private IIllnessDao illnessDao;
    @Autowired
    private ISolveSchemeDao solveSchemeDao;
    @Autowired
    private IPrescriptionDao prescriptionDao;
    @Autowired
    private IOutPreItemDao outPreItemDao;
    
    /**
    * @Title:initHistory
    * @Description:创建病历，返回挂号信息
    * @param:@param regId
    * @param:@return
    * @return:OutpatientRegistration
    * @throws
    * @author:Sbaby
    * @Date:2019年8月21日 上午11:25:46
     */
    public JsonResult initHistory(String cardNum, String roomId) {
    	JsonResult result = new JsonResult();
    	// 先根据就诊卡号与候诊厅编号查询就诊卡是否是呼叫的患者，并获取挂号信息
    	OutMedicalRecord omr = outMedicalRecordDao.checkCallPatient(roomId);
    	if(omr != null) {
    		if(omr.getOutpatientRegistration().getMedicalCard().getCardId().equals(cardNum)) {
    			OutpatientRegistration outpatientRegistration = omr.getOutpatientRegistration();
    			// 创建诊断记录
    	        History history = new History();
    	        history.setHistoryId(UUID.randomUUID().toString().replaceAll("-", ""));
    	        history.setOutpatientRegistration(outpatientRegistration);
    	        historyDao.save(history);
    	        outpatientRegistration.setHistory(history);
    	        outpatientRegistrationDao.save(outpatientRegistration);
    	        // 创建医嘱
    			SolveScheme solveScheme = new SolveScheme();
    			solveScheme.setScheId(UUID.randomUUID().toString().replaceAll("-", ""));
    			solveScheme.setHistory(history);
    			solveSchemeDao.save(solveScheme);
    			List<SolveScheme> solveSchemes = new ArrayList<SolveScheme>();
    			solveSchemes.add(solveScheme);
    			history.setSolveScheme(solveSchemes);
    	        // 修改排队状态
    	        omr.setOutStatus("正在就诊");
    	        outMedicalRecordDao.save(omr);
    	        result.setResult(history);
    	        result.setStatus("ok");
    		} else {
    			result.setResult("请在叫号时进行就诊！");
        		result.setStatus("error");
    		}
    	} else {
    		result.setResult("当前无叫号患者！");
    		result.setStatus("error");
    	}
    	return result;
    }
    
    /**
    * @Title:checkDiagnose
    * @Description:检查当前是否在诊断患者
    * @param:@param roomId
    * @param:@return
    * @return:JsonResult
    * @throws
    * @author:Sbaby
    * @Date:2019年8月21日 下午2:07:59
     */
    public JsonResult checkDiagnose(String roomId) {
    	JsonResult result = new JsonResult();
    	OutMedicalRecord outMedicalRecord = outMedicalRecordDao.getDiagnosePatient(roomId);
    	if(outMedicalRecord != null) {
    		result.setResult(outMedicalRecord.getOutpatientRegistration().getHistory());
    		result.setStatus("ok");
    	} else {
    		result.setResult("当前无患者就诊！");
    		result.setStatus("error");
    	}
    	return result;
    }
    
    /**
    * @Title:searchIllness
    * @Description:搜索疾病疾病
    * @param:@param searchKey
    * @param:@return
    * @return:List<Illness>
    * @throws
    * @author:Sbaby
    * @Date:2019年8月21日 下午3:36:41
     */
    public List<Illness> searchIllness(String searchKey) {
    	PageRequest page = PageRequest.of(0, 10);
    	return illnessDao.searchByKey(SimpleTools.addCharForSearch(searchKey.toUpperCase()), page);
    }
    
    /**
    * @Title:addHistory
    * @Description:诊断结束
    * @param:@param history
    * @return:void
    * @throws
    * @author:Sbaby
    * @Date:2019年8月24日 上午11:20:37
     */
    public History addHistory(History history) {
    	OutpatientRegistration outpatientRegistration = history.getOutpatientRegistration();
    	// 处方单
    	Prescription prescription = history.getPrescription();
    	prescription.setPrescriptionId(UUID.randomUUID().toString().replaceAll("-", ""));
    	prescription.setHistory(history);
    	prescription.setPresTime(new Date());
    	prescriptionDao.save(prescription);
    	// 处方明细
    	List<OutPreItem> outPreItems  = prescription.getOutPreItems();
    	for (OutPreItem outPreItem : outPreItems) {
			OutPreItemPK PK = new OutPreItemPK();
			PK.setPrescriptionId(prescription.getPrescriptionId());
			PK.setYpId(outPreItem.getDrugInformation().getYpId());
			outPreItem.setId(PK);
			outPreItem.setPrescription(prescription);
			outPreItemDao.save(outPreItem);
		}
    	history.setPrescriptionId(prescription.getPrescriptionId());
    	history.setHisTime(new Date());
    	history.setDepartment(history.getOutpatientRegistration().getDepartment());
    	historyDao.save(history);
    	outpatientRegistration.setHistory(history);
    	outpatientRegistration.setExamination(outpatientRegistration.getExamination());
    	outpatientRegistrationDao.save(outpatientRegistration);
    	OutMedicalRecord outMedicalRecord = outMedicalRecordDao.findById(outpatientRegistration.getOutMid()).get();
    	outMedicalRecord.setOutStatus("已就诊");
    	outMedicalRecordDao.save(outMedicalRecord);
    	return history;
    }


}
