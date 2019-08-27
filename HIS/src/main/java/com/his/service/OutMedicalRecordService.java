package com.his.service;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import com.his.dao.IWaitingRoomDao;
import com.his.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.his.dao.IOutMedicalRecordDao;
import com.his.dao.IOutpatientRegistrationDao;
import com.his.utils.QRCodeUtil;

import javax.servlet.http.HttpSession;

/**
 * @Author Sbaby
 * @Description 门诊就诊排队记录service
 * @Date 2019/08/17 15:45
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OutMedicalRecordService {

    @Autowired
    private IOutMedicalRecordDao outMedicalRecordDao;
    @Autowired
    private IOutpatientRegistrationDao outpatientRegistrationDao;
    @Autowired
    private IWaitingRoomDao waitingRoomDao;

    /**
    * @Title:checkForCode
    * @Description:根据base64编码去检查此挂号单是否有效
    * @param:@param base64
    * @param:@param waitingRoomId
    * @param:@return
    * @param:@throws IOException
    * @return:boolean
     * @throws Exception 
    * @throws
    * @author:Sbaby
    * @Date:2019年8月19日 上午10:13:28
     */
    public JsonResult checkForCode(MultipartFile base64, String waitingRoomId) throws Exception {
    	JsonResult result = new JsonResult();
    	// 先生成图片
    	String fullName = "";
        String suffix = base64.getContentType().toLowerCase();//图片后缀，用以识别哪种格式数据
        suffix = suffix.substring(suffix.lastIndexOf("/")+1);

        if(suffix.equals("jpg") || suffix.equals("jpeg") || suffix.equals("png") || suffix.equals("gif")) {
            String fileName = "_" + UUID.randomUUID().toString().replaceAll("-", "") + "." + suffix;
            String imgFilePath = "D:\\HIS\\waitingRoomCheck\\";//新生成的图片
            fullName = imgFilePath + fileName;
            
            File targetFile = new File(imgFilePath, fileName);
            if (!targetFile.getParentFile().exists()) { //注意，判断父级路径是否存在
                targetFile.getParentFile().mkdirs();
            }
            base64.transferTo(targetFile);
        }
        // 解析二维码
//        fullName = "D:\\HIS\\waitingRoomCheck\\_83d512b08634424e88ef3393e38f7a67.png";
//        String result = QRCodeUtil.zxingCodeAnalyze(fullName);
        String resultInfo = QRCodeUtil.decodeQRCodeImage(fullName, null);
        if("".equals(resultInfo)) {
        	result.setResult("解析二维码失败，请重试拍照上传！");
        	result.setStatus("error");
        }else{
        	System.out.println(resultInfo);
        	JSONObject obj = JSONObject.parseObject(resultInfo);
        	// 检查是否允许
        	OutpatientRegistration outpatientRegistration = outpatientRegistrationDao.findById((String) obj.get("regId")).get();
        	// A.检查挂号单是否属于当前候诊厅
        	if(!checkRegRoom(waitingRoomId, outpatientRegistration)) {
        		result.setResult("此挂号单不属于当前候诊厅！");
        		result.setStatus("error");
        		return result;
        	}
        	// B.检查挂号单时间是否正确
        	if(!checkRegTime(outpatientRegistration)) {
        		result.setResult("不在规定时间就诊！");
        		result.setStatus("error");
        		return result;
        	}
        	// C.检查是否缴费
        	if(checkRegPay(outpatientRegistration)) {
        		result.setResult("请先缴费再排队候诊！");
        		result.setStatus("error");
        		return result;
        	}
        	// D.检查是否已经就诊过了
        	if(!checkRegStatus(outpatientRegistration)) {
        		result.setResult("此挂号已就诊，要就诊请重新挂号！");
        		result.setStatus("error");
        		return result;
        	}

            // 确认此人是否已经排队
            if(outMedicalRecordDao.checkForQueue(outpatientRegistration.getRegId()) != 0) {
                result.setResult("此挂号已经排队，无需重复排队！");
                result.setStatus("error");
                return result;
            }
        	
        	// 加入排队
        	this.addOutMedicalRecord(outpatientRegistration);
        	result.setStatus("ok");
        }
        return result;
    }
    
    /**
    * @Title:addOutMedicalRecord
    * @Description:门诊候诊排队
    * @param:@param outpatientRegistration
    * @return:void
    * @throws
    * @author:Sbaby
    * @Date:2019年8月19日 下午2:14:23
     */
    public void addOutMedicalRecord(OutpatientRegistration outpatientRegistration) {
    	OutMedicalRecord outMedicalRecord = new OutMedicalRecord();
    	outMedicalRecord.setOutMid(UUID.randomUUID().toString().replaceAll("-", ""));
    	outMedicalRecord.setOutpatientRegistration(outpatientRegistration);
    	outMedicalRecord.setOutMtime(new Date());
    	outMedicalRecord.setOutTimes(new BigDecimal(0));
    	outMedicalRecord.setOutStatus("候诊");
//    	outMedicalRecord.setRegId(outpatientRegistration.getRegId());
    	
    	outMedicalRecordDao.save(outMedicalRecord);
    	
    	outpatientRegistration.setOutMedicalRecord(outMedicalRecord);
    	outpatientRegistration.setOutMid(outMedicalRecord.getOutMid());
    	outpatientRegistrationDao.save(outpatientRegistration);
    }
    
    /**
    * @Title:checkRegRoom
    * @Description:检查挂号单是否属于当前候诊厅
    * @param:@param roomId
    * @param:@param RegId
    * @param:@return
    * @return:boolean
    * @throws
    * @author:Sbaby
    * @Date:2019年8月19日 上午11:07:05
     */
    public boolean checkRegRoom(String roomId, OutpatientRegistration outpatientRegistration) {
    	List<RegEmp> regEmps = outpatientRegistration.getRegEmps();
    	for (RegEmp regEmp : regEmps) {
			if("医生".equals(regEmp.getRegDuty())) {
				return regEmp.getEmpInformation().getWaitingRoom().getWaitingRoomId().equals(roomId);
			}
		}
    	return false;
    }
    
    /**
    * @Title:checkRegTime
    * @Description:检查挂号单时间是否正确
    * @param:@param regId
    * @param:@param outpatientRegistration
    * @param:@return
    * @return:boolean
    * @throws
    * @author:Sbaby
    * @Date:2019年8月19日 上午11:15:15
     */
    public boolean checkRegTime(OutpatientRegistration outpatientRegistration) {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	String doDate = sdf.format(outpatientRegistration.getDoDate());
    	String now = sdf.format(new Date());
    	return now.equals(doDate);
    }
    
    /**
    * @Title:checkRegPay
    * @Description:检查挂号单是否缴费
    * @param:@param outTradeNo
    * @param:@return
    * @return:boolean
    * @throws
    * @author:Sbaby
    * @Date:2019年8月19日 上午11:20:02
     */
    public boolean checkRegPay(OutpatientRegistration outpatientRegistration) {
    	return "待缴费".equals(outpatientRegistration.getRegStatus());
    }
    
    /**
    * @Title:checkRegStatus
    * @Description:检查此挂号单是否已经就诊
    * @param:@param outpatientRegistration
    * @param:@return
    * @return:boolean
    * @throws
    * @author:Sbaby
    * @Date:2019年8月19日 上午11:50:54
     */
    public boolean checkRegStatus(OutpatientRegistration outpatientRegistration) {
    	return "已缴费".equals(outpatientRegistration.getRegStatus());
    }
    
    /**
    * @Title:getQueueByRoomId
    * @Description:根据候诊厅id查询排队队列
    * @param:@param roomId
    * @param:@return
    * @return:List<OutMedicalRecord>
    * @throws
    * @author:Sbaby
    * @Date:2019年8月19日 下午3:15:17
     */
    public List<OutMedicalRecord> getQueueByRoomId(String roomId) {
//		PageRequest page = PageRequest.of(0, 10);
    	List<OutMedicalRecord> list = outMedicalRecordDao.getQueueByRoomId(roomId);
    	return list;
    }

    /**
    * @Title:callPatient
    * @Description:刷新候诊室排队信息
    * @param:@param roomId
    * @param:@return
    * @return:List<OutMedicalRecord>
    * @throws
    * @author:Sbaby
    * @Date:2019年8月21日 上午11:10:41
     */
    public List<OutMedicalRecord> callPatient(String roomId) {
		// 先检查呼叫的患者是否在就诊
		OutMedicalRecord outMedicalRecord = outMedicalRecordDao.checkCallPatient(roomId);
		// 查询前四位患者
    	List<OutMedicalRecord> list = outMedicalRecordDao.getQueueByRoomId(roomId);
		// 如果不在就诊，将患者设置成候诊
		if(outMedicalRecord != null) {
			if(outMedicalRecord.getOutTimes().compareTo(new BigDecimal(3)) >= 0) {
				outMedicalRecord.setOutStatus("挂号作废");
			} else {
				outMedicalRecord.setOutStatus("候诊");
			}
			outMedicalRecordDao.save(outMedicalRecord);
			
			// 叫号下一位患者
			boolean flag = false;
			boolean isChange = false;
			for (OutMedicalRecord omr :	list) {
				System.out.println(omr.getOutpatientRegistration().getMedicalCard().getCardName());
				if(flag) {
					omr.setOutTimes(omr.getOutTimes().add(new BigDecimal(1)));
					omr.setOutStatus("正在叫号");
					outMedicalRecordDao.save(omr);
					isChange = true;
					break;
				}
				if(outMedicalRecord == omr) {
					flag = true;
				}
//				if(!omr.getOutMid().equals(outMedicalRecord.getOutMid())) {
//					omr.setOutTimes(omr.getOutTimes().add(new BigDecimal(1)));
//					omr.setOutStatus("正在叫号");
//					outMedicalRecordDao.save(omr);
//					break;
//				}
			}
			if(!isChange && list.size() > 1) {
				OutMedicalRecord omr = list.get(0);
				omr.setOutTimes(omr.getOutTimes().add(new BigDecimal(1)));
				omr.setOutStatus("正在叫号");
				outMedicalRecordDao.save(omr);
			}
		}
		return this.getQueueByRoomId(roomId);
	}


    /**
    * @Title:callNext
    * @Description:医生呼叫下一位患者
    * @param:@param roomId
    * @param:@return
    * @return:boolean
    * @throws
    * @author:Sbaby
    * @Date:2019年8月21日 上午11:10:58
     */
	public boolean callNext(String roomId) {
    	PageRequest page = PageRequest.of(0, 4);
    	List<OutMedicalRecord> list = outMedicalRecordDao.getQueueByRoomId(roomId);
    	if(list.size() == 0) {
    		return false;
    	} else {
    		OutMedicalRecord outMedicalRecord = list.get(0);
        	outMedicalRecord.setOutStatus("正在叫号");
        	outMedicalRecord.setOutTimes(outMedicalRecord.getOutTimes().add(new BigDecimal(1)));
        	outMedicalRecordDao.save(outMedicalRecord);
        	return true;
    	}
	}

	/**
	* @Title:checkCall
	* @Description:检查当前是否有患者正在就着或叫号
	* @param:@param roomId
	* @param:@return
	* @return:boolean
	* @throws
	* @author:Sbaby
	* @Date:2019年8月21日 上午11:11:12
	 */
	public boolean checkCall(String roomId) {
		return outMedicalRecordDao.checkCall(roomId) > 0 ? true : false;
	}

}
