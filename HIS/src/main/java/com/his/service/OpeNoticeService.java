package com.his.service;

import java.util.ArrayList;
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
import com.his.dao.IOpeEmpDao;
import com.his.dao.IOperPayRecordDao;
import com.his.dao.IOperationNoticeDao;
import com.his.dao.IOperationPayDao;
import com.his.dao.IOperationRecordDao;
import com.his.dao.IRoleDao;
import com.his.pojo.EmpInformation;
import com.his.pojo.OpeEmp;
import com.his.pojo.OpeEmpPK;
import com.his.pojo.OpeNotice;
import com.his.pojo.OperPayRecord;
import com.his.pojo.OperationPay;
import com.his.pojo.OperationRecord;
import com.his.pojo.Role;
import com.his.pojo.UserRole;
import com.his.utils.UUIDGenerator;

import oracle.net.aso.e;

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
	@Autowired
	private  IRoleDao iRoleDao;
	@Autowired
	private IOpeEmpDao iOpeEmpDao;
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
    public void payfun(String brcard_id,String opepay_id,String ygxh,String noticeid) {
    	UUIDGenerator uuone=new UUIDGenerator();
    	String operecordid=uuone.getUUID();
    	OperationRecord operationRecord=new OperationRecord();
    	operationRecord.setOpeId(operecordid);
    	operationRecord.setOpeDiagnose(" ");
    	operationRecord.setOpeJg(" ");
    	operationRecord.setOpeStatus("未执行");
    	operationRecord.setOpeType(" ");
    	operationRecord.setOpeTime(new Date());
    	iOperationRecordDao.save(operationRecord);
        Role role=iRoleDao.getRole(iOperationPayDao.findById(opepay_id).get().getOperPayName()+"主刀医生");
        System.out.println(iOperationPayDao.findById(opepay_id).get().getOperPayName()+"主刀医生");
        System.out.println(role.getRoleId());
        List<UserRole> list=role.getUserRoles();
        List<EmpInformation> emplist=new ArrayList<EmpInformation>();
        for (UserRole userRole : list) {	
        	emplist.add(userRole.getEmpInformation());
        	
		}
        List<OpeEmp> listopeEmps=new ArrayList<OpeEmp>();
        OpeEmp opeEmp=new OpeEmp();
        opeEmp.setDuty("主刀医生");
        opeEmp.setEmpInformation(emplist.get(0));
        opeEmp.setOperationRecord(operationRecord);
        OpeEmpPK opepk=new OpeEmpPK();
        opepk.setOpeId(operecordid);
        opepk.setYgxh(emplist.get(0).getYgxh());
        opeEmp.setId(opepk);
        listopeEmps.add(opeEmp);
        emplist.get(0).setOpeEmps(listopeEmps);
    	operationRecord.setOpeEmps(listopeEmps);
    	iOpeEmpDao.save(opeEmp);
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
    	OpeNotice opeNotice=iOperationNotice.findById(noticeid).get();
    	opeNotice.setMoperComment("已处理");
    	iOperationNotice.save(opeNotice);
    	
    }
    public List<OperationPay> getn(){
    	return (List<OperationPay>) iOperationPayDao.findAll();
    }
    public List<Role> getallRoles(){
    List<Role> list =  iRoleDao.getAll();
    System.out.println(list.size());
    return list;
    }
}
