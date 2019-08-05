package com.his.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.his.bean.OpeNoticebean;
import com.his.dao.IEmpInformationDao;
import com.his.dao.IMedicalCardDao;
import com.his.dao.IOperPayRecordDao;
import com.his.dao.IOperationNoticeDao;
import com.his.dao.IOperationPayDao;
import com.his.dao.IOperationRecordDao;
import com.his.pojo.OpeNotice;
import com.his.pojo.OperPayRecord;
import com.his.pojo.OperationPay;
import com.his.pojo.OperationRecord;
import com.his.utils.UUIDGenerator;

/**  
* @ClassName: OpeNoticeService  
* @Description: TODO(手术通知单service)  
* @author TRC
* @date 2019年8月2日  上午11:50:58
*    
*/
@Service
public class OpeNoticeService {
	@Autowired
	private IOperationNoticeDao iOperationNotice;
	@Autowired
	private IOperPayRecordDao iOperPayRecordDao;
	@Autowired
	private IOperationRecordDao iOperationRecordDao;
	@Autowired
	private IMedicalCardDao iMedicalCardDao;
	@Autowired
	private IOperationPayDao iOperationPayDao;
	@Autowired
	private IEmpInformationDao iEmpInformationDao;
    public Map getNoticebeans(int curpage, int pagesize,String sou){
    	List<OpeNoticebean> list=iOperationNotice.getNoticebeans( sou,PageRequest.of(curpage - 1,
		  pagesize));
    	long total=iOperationNotice.getcountbysou(sou);
    	 Map map = new HashMap();
		 map.put("total", total);
		 map.put("list", list);
	  return  map;
  }
    public Map getopenoticebysou(String sou){
    	List<OpeNoticebean> list=iOperationNotice.getopenoticebysou(sou);
    	long total=iOperationNotice.getcountbysou(sou);
    	 Map map = new HashMap();
		 map.put("total", total);
		 map.put("list", list);
    	return map;
    }
    
    public OperationPay getOperationPaybyid(String id){
    	return iOperationNotice.getPaybyid(id);
    }
    public void payfun(String brcard_id,String opepay_id,String ygxh) {
    	UUIDGenerator uuone=new UUIDGenerator();
    	String operecordid=uuone.getUUID();
    	OperationRecord operationRecord=new OperationRecord();
    	operationRecord.setOpeId(operecordid);
    	iOperationRecordDao.save(operationRecord);
    	OperPayRecord operPayRecord=new OperPayRecord();
    	UUIDGenerator uutwo=new UUIDGenerator();
    	operPayRecord.setOperJfId(uutwo.getUUID());
    	operPayRecord.setMedicalCard(iMedicalCardDao.findById(brcard_id).get());
    	operPayRecord.setOperationPay(iOperationPayDao.findById(opepay_id).get());
    	operPayRecord.setOperationRecord(iOperationRecordDao.findById(operecordid).get());
    	operPayRecord.setEmpInformation(iEmpInformationDao.findById(ygxh).get());
    	operPayRecord.setOperJfTime(new Date());
    	iOperPayRecordDao.save(operPayRecord);
    }
    public List<OperationPay> getn(){
    	return (List<OperationPay>) iOperationPayDao.findAll();
    }
}
