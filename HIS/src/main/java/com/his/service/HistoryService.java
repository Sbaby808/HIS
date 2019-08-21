package com.his.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IHistoryDao;
import com.his.dao.IIllnessDao;
import com.his.dao.IOutMedicalRecordDao;
import com.his.dao.IOutpatientRegistrationDao;
import com.his.pojo.History;
import com.his.pojo.Illness;
import com.his.pojo.JsonResult;
import com.his.pojo.OutMedicalRecord;
import com.his.pojo.OutpatientRegistration;
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
    	        History history = new History();
    	        history.setHistoryId(UUID.randomUUID().toString().replaceAll("-", ""));
    	        history.setOutpatientRegistration(outpatientRegistration);
    	        historyDao.save(history);
    	        outpatientRegistration.setHistory(history);
    	        outpatientRegistrationDao.save(outpatientRegistration);
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


}
