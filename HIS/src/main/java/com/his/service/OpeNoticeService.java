package com.his.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.bean.OpeNoticebean;
import com.his.dao.IEmpInformationDao;
import com.his.dao.IHosSurgeryNoticeDao;
import com.his.dao.IMedicalCardDao;
import com.his.dao.IOpeEmpDao;
import com.his.dao.IOperPayRecordDao;
import com.his.dao.IOperationNoticeDao;
import com.his.dao.IOperationPayDao;
import com.his.dao.IOperationRecordDao;
import com.his.dao.IRoleDao;
import com.his.pojo.CheckNoticeForm;
import com.his.pojo.EmpInformation;
import com.his.pojo.HosSurgeryNotice;
import com.his.pojo.OpeEmp;
import com.his.pojo.OpeEmpPK;
import com.his.pojo.OpeNotice;
import com.his.pojo.OperPayRecord;
import com.his.pojo.OperationPay;
import com.his.pojo.OperationRecord;
import com.his.pojo.Role;
import com.his.pojo.SolveScheme;
import com.his.pojo.UserRole;
import com.his.utils.UUIDGenerator;

import oracle.net.aso.e;
import oracle.net.aso.s;

/**  
* @ClassName: OpeNoticeService  
* @Description: TODO(手术通知单service)  
* @author TRC
* @date 2019年8月2日  上午11:50:58
*    
*/
@Service
@Transactional(rollbackFor=Exception.class)
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
	@Autowired
	private IHosSurgeryNoticeDao iHosSurgeryNoticeDao;
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
    	List<OpeNotice> opeNotice=iOperationNotice.getbyid(noticeid);
    	if(!opeNotice.isEmpty()) {
    		opeNotice.get(0).setMoperComment("已处理");
        	iOperationNotice.save(opeNotice.get(0));
    	}
    	else {
    		HosSurgeryNotice hosnotice=iHosSurgeryNoticeDao.findById(noticeid).get();
    		hosnotice.setPayNote("已处理");
    		iHosSurgeryNoticeDao.save(hosnotice);
    	}
    	
    	
    }
    public List<OperationPay> getn(){
    	return (List<OperationPay>) iOperationPayDao.findAll();
    }
    public List<Role> getallRoles(){
    List<Role> list =  iRoleDao.getAll();
    System.out.println(list.size());
    return list;
    }
    public Map getzhuyuannotice(int curpage, int pagesize,String sou){
    	List<OpeNoticebean> zhulist=iHosSurgeryNoticeDao.getzhuyuannotice(sou, PageRequest.of(curpage - 1,
    			  pagesize));
    	long zhutotal=iHosSurgeryNoticeDao.getcount(sou); 	
    	Map map=new HashMap();
    	map.put("zhulist", zhulist);
    	map.put("zhutotal", zhutotal);
    	return map;
    }
    public Map getbysou(int curpage, int pagesize,String sou) {
    	List<OpeNoticebean> list=iOperationNotice.getNoticebeans( sou,PageRequest.of(curpage - 1,
    			  pagesize));
    	long total=iOperationNotice.getcountbysou(sou);
    	List<OpeNoticebean> zhulist=iHosSurgeryNoticeDao.getzhuyuannotice(sou, PageRequest.of(curpage - 1,
    			  pagesize));    	
    	long zhutotal=iHosSurgeryNoticeDao.getcount(sou);
    	Map map=new HashMap();
    	map.put("list", list);
    	map.put("total", total);
    	map.put("zhulist", zhulist);
    	map.put("zhutotal", zhutotal);
    	return map;
    }
    
    /**
    * @Title:getAllOpeNotice
    * @Description:根据医嘱编号查询手术通知项
    * @param:@param solveId
    * @param:@return
    * @return:List<OpeNotice>
    * @throws
    * @author:Sbaby
    * @Date:2019年8月24日 上午9:56:54
     */
    public List<OpeNotice> getAllOpeNotice(String solveId) {
    	return iOperationNotice.getAllBySolveId(solveId);
    }
    
    /**
    * @Title:getAllOpeNoticeByHistoryId
    * @Description:根据诊断记录编号查询手术通知项
    * @param:@param historyId
    * @param:@return
    * @return:List<OpeNotice>
    * @throws
    * @author:Sbaby
    * @Date:2019年8月25日 下午2:32:09
     */
    public List<OpeNotice> getAllOpeNoticeByHistoryId(String historyId) {
    	return iOperationNotice.getAllOpeNoticeByHistoryId(historyId);
    }
    
    /**
    * @Title:addOpeNotice
    * @Description:添加手术通知项
    * @param:@param opeNotice
    * @param:@return
    * @return:List<OpeNotice>
    * @throws
    * @author:Sbaby
    * @Date:2019年8月24日 上午9:57:43
     */
    public List<OpeNotice> addOpeNotice(OpeNotice opeNotice) {
    	opeNotice.setMoperId(UUID.randomUUID().toString().replaceAll("-", ""));
    	iOperationNotice.save(opeNotice);
    	return iOperationNotice.getAllBySolveId(opeNotice.getSolveScheme().getScheId());
    }
    
    /**
    * @Title:delById
    * @Description:根据编号删除手术通知项
    * @param:@param id
    * @param:@return
    * @return:List<OpeNotice>
    * @throws
    * @author:Sbaby
    * @Date:2019年8月24日 上午10:12:20
     */
    public List<OpeNotice> delById(String id) {
    	OpeNotice opeNotice = iOperationNotice.findById(id).get();
    	SolveScheme scheme = opeNotice.getSolveScheme();
    	iOperationNotice.delete(opeNotice);
    	return this.getAllOpeNotice(scheme.getScheId());
    }
 
}
