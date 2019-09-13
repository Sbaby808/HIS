package com.his.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    	        outpatientRegistration.setHistory(history);
    	        outpatientRegistrationDao.save(outpatientRegistration);
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
    
    /**
    * @Title:getHistoryByYgxh
    * @Description:查询员工的诊断记录
    * @param:@param ygxh
    * @param:@return
    * @return:List<History>
    * @throws
    * @author:Sbaby
    * @Date:2019年8月24日 下午5:04:48
     */
    public List<History> getHistoryByYgxh(String ygxh, int pageNum, int pageSize) {
    	PageRequest page = PageRequest.of(pageNum - 1, pageSize);
    	return historyDao.getHistoryByYgxh(ygxh, page);
    }
    
    /**
    * @Title:getHistoryCountByYgxh
    * @Description:查询员工的诊断记录数量
    * @param:@param ygxh
    * @param:@return
    * @return:int
    * @throws
    * @author:Sbaby
    * @Date:2019年8月24日 下午5:37:18
     */
    public int getHistoryCountByYgxh(String ygxh) {
    	return historyDao.getHisotryByYgxhCount(ygxh);
    }
    
    /**
    * @Title:searchHistoryCount
    * @Description:搜索诊断记录条数
    * @param:@param ygxh
    * @param:@param illnessKey
    * @param:@param searchStartTime
    * @param:@param searchEndTime
    * @param:@return
    * @return:int
     * @throws ParseException 
    * @throws
    * @author:Sbaby
    * @Date:2019年8月25日 下午2:53:26
     */
    public int searchHistoryCount(String ygxh, String nameKey, String illnessKey, String searchStartTime, String searchEndTime) throws ParseException {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date startTime = sdf.parse("".equals(searchStartTime) ? "1900-00-00 00:00:00" : searchStartTime);
    	Date endTime = sdf.parse("".equals(searchEndTime) ? sdf.format(new Date()) : searchEndTime);
        return historyDao.searchHistoryCount(ygxh, SimpleTools.addCharForSearch(nameKey), SimpleTools.addCharForSearch(illnessKey), startTime, endTime);
    }
    
    /**
    * @Title:searchHistoryCountByCardId
    * @Description:根据就诊卡号查询诊断记录条数
    * @param:@param cardId
    * @param:@param searchStartTime
    * @param:@param searchEndTime
    * @param:@return
    * @param:@throws ParseException
    * @return:int
    * @throws
    * @author:Sbaby
    * @Date:2019年8月26日 下午3:31:34
     */
    public int searchHistoryCountByCardId(String cardId, String searchStartTime, String searchEndTime) throws ParseException {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date startTime = sdf.parse("".equals(searchStartTime) ? "1970-00-00 00:00:00" : searchStartTime);
    	Date endTime = sdf.parse("".equals(searchEndTime) ? sdf.format(new Date()) : searchEndTime);
    	return historyDao.searchHistoryCountByCardId(cardId, startTime, endTime);
    }
    
    /**
    * @Title:searchHisPreCountByCardId
    * @Description:查询门诊带处方诊断记录条数
    * @param:@param cardId
    * @param:@param searchStartTime
    * @param:@param searchEndTime
    * @param:@return
    * @param:@throws ParseException
    * @return:int
    * @throws
    * @author:Sbaby
    * @Date:2019年9月3日 上午9:00:36
     */
    public int searchHisPreCountByCardId(String cardId, String searchStartTime, String searchEndTime) throws ParseException {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date startTime = sdf.parse("".equals(searchStartTime) ? "1970-00-00 00:00:00" : searchStartTime);
    	Date endTime = sdf.parse("".equals(searchEndTime) ? sdf.format(new Date()) : searchEndTime);
    	return historyDao.searchHisPreCountByCardId(cardId, startTime, endTime);
    }
    
    /**
    * @Title:searchHisPreByCardId
    * @Description:查询带处方的门诊诊断记录
    * @param:@param cardId
    * @param:@param searchStartTime
    * @param:@param searchEndTime
    * @param:@return
    * @param:@throws ParseException
    * @return:List<History>
    * @throws
    * @author:Sbaby
    * @Date:2019年9月3日 上午9:08:50
     */
    public List<History> searchHisPreByCardId(String cardId, String searchStartTime, String searchEndTime) throws ParseException {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date startTime = sdf.parse("".equals(searchStartTime) ? "1970-00-00 00:00:00" : searchStartTime);
    	Date endTime = sdf.parse("".equals(searchEndTime) ? sdf.format(new Date()) : searchEndTime);
    	return historyDao.searchHisPreByCardId(cardId, startTime, endTime);
    }
    
    /**
    * @Title:searchHistory
    * @Description:搜索诊断记录
    * @param:@param ygxh
    * @param:@param illnessKey
    * @param:@param searchStartTime
    * @param:@param searchEndTime
    * @param:@param pageNum
    * @param:@param pageSize
    * @param:@return
    * @return:List<History>
     * @throws ParseException 
    * @throws
    * @author:Sbaby
    * @Date:2019年8月25日 下午3:19:02
     */
    public List<History> searchHistory(String ygxh, String nameKey, String illnessKey, String searchStartTime, String searchEndTime, int pageNum, int pageSize) throws ParseException {
    	PageRequest page = PageRequest.of(pageNum - 1, pageSize);
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date startTime = sdf.parse("".equals(searchStartTime) ? "1900-00-00 00:00:00" : searchStartTime);
    	Date endTime = sdf.parse("".equals(searchEndTime) ? sdf.format(new Date()) : searchEndTime);
        return historyDao.searchHistory(ygxh, SimpleTools.addCharForSearch(nameKey), SimpleTools.addCharForSearch(illnessKey), startTime, endTime, page);
    }
    
    /**
    * @Title:searchHistoryByCardId
    * @Description:根据就诊卡编号查询诊断记录
    * @param:@param cardId
    * @param:@param searchStartTime
    * @param:@param searchEndTime
    * @param:@param pageNum
    * @param:@param pageSize
    * @param:@return
    * @param:@throws ParseException
    * @return:List<History>
    * @throws
    * @author:Sbaby
    * @Date:2019年8月26日 下午3:37:46
     */
    public List<History> searchHistoryByCardId(String cardId, String searchStartTime, String searchEndTime, int pageNum, int pageSize) throws ParseException {
    	PageRequest page = PageRequest.of(pageNum - 1, pageSize);
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date startTime = sdf.parse("".equals(searchStartTime) ? "1900-00-00 00:00:00" : searchStartTime);
    	Date endTime = sdf.parse("".equals(searchEndTime) ? sdf.format(new Date()) : searchEndTime);
    	return historyDao.searchHistoryByCardId(cardId, startTime, endTime, page);
    }
    
    /**
    * @Title:getSolveSchemeByHistoryId
    * @Description:根据诊断记录编号查询医嘱
    * @param:@param historyId
    * @param:@return
    * @return:SolveScheme
    * @throws
    * @author:Sbaby
    * @Date:2019年8月26日 上午9:48:35
     */
    public SolveScheme getSolveSchemeByHistoryId(String historyId) {
    	return solveSchemeDao.getByHistoryId(historyId);
    }
    
    /**
    * @Title:getHistoryCountByCardId
    * @Description:查询门诊诊断记录数量
    * @param:@param cardId
    * @param:@return
    * @return:List<History>
    * @throws
    * @author:Sbaby
    * @Date:2019年9月11日 下午6:18:53
     */
    public int getHistoryCountByCardId(String cardId) {
    	return historyDao.getHistoryCountByCardId(cardId);
    }
    
    /**
    * @Title:getHistoryByCardId
    * @Description:查询门诊诊断记录
    * @param:@param cardId
    * @param:@param pageNum
    * @param:@param pageSize
    * @param:@return
    * @return:List<History>
    * @throws
    * @author:Sbaby
    * @Date:2019年9月11日 下午6:30:24
     */
    public List<History> getHistoryByCardId(String cardId, int pageNum, int pageSize) {
    	PageRequest page = PageRequest.of(pageNum - 1, pageSize);
    	return historyDao.getHistoryByCardId(cardId, page);
    }


}
