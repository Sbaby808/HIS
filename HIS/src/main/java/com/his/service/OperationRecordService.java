package com.his.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.naming.java.javaURLContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.his.bean.Opeempbean;
import com.his.bean.OperationPaybean;
import com.his.bean.OperationRecordbean;
import com.his.dao.IEmpInformationDao;
import com.his.dao.IOpeEmpDao;
import com.his.dao.IOperationRecordDao;
import com.his.dao.IRoleDao;
import com.his.pojo.EmpInformation;
import com.his.pojo.OpeEmp;
import com.his.pojo.OpeEmpPK;
import com.his.pojo.OperationRecord;
import com.his.pojo.Role;
import com.his.pojo.UserRole;

import oracle.net.aso.p;
/**  
* @ClassName: OperationRecordService  
* @Description: TODO(手术记录service)  
* @author TRC
* @date 2019年7月30日  上午9:06:27
*    
*/
@Service
public class OperationRecordService {

	@Autowired
	private IOperationRecordDao iOperationRecordDao;
	@Autowired
	private IEmpInformationDao iEmpInformationDao;
	@Autowired
	private  IRoleDao iRoleDao;
	@Autowired
	private IOpeEmpDao iOpeEmpDao;
	/**
	 * 
	* @Title:getallOperationRecords
	* @Description:TODO查询所有的手术记录
	* @param:@return
	* @return:List<OperationRecord>
	* @throws
	* @author:TRC
	* @Date:2019年7月30日 下午3:05:19
	 */
	public Map getallRecordbeans(int curpage, int pagesize,String sou,String ygxh){
		List<OperationRecordbean> list=iOperationRecordDao.getallOperationRecordbeans(sou,ygxh, PageRequest.of(curpage - 1,
				  pagesize));
	
		long total=iOperationRecordDao.getcount(sou,ygxh);
		 Map map = new HashMap();
		 map.put("total", total);
		 map.put("list", list);
		return map;
	}
	/**
	 * 
	* @Title:addOperationRecord
	* @Description:TODO添加手术记录包括添加员工
	* @param:@param operationRecord
	* @return:void
	* @throws
	* @author:TRC
	* @Date:2019年7月30日 下午3:07:38
	 */
	public void addOperationRecord(String Str,String opeid,String opeDiagnose,String opeType,String opeJg,Date opetime,String opeStatus) {
		String a[]=Str.split(",");
		List<OpeEmp> emplist=new ArrayList<OpeEmp>();
		OperationRecord operationRecord =iOperationRecordDao.findById(opeid).get();
		operationRecord.setOpeDiagnose(opeDiagnose);
		operationRecord.setOpeJg(opeJg);
		operationRecord.setOpeStatus(opeStatus);
		operationRecord.setOpeTime(opetime);
		operationRecord.setOpeType(opeType);
		List<OpeEmp> oelist=operationRecord.getOpeEmps();
		for (OpeEmp opeEmpa : oelist) {
			if(opeEmpa!=null) {
				if(!opeEmpa.getDuty().substring(opeEmpa.getDuty().length()-4, opeEmpa.getDuty().length()).equals("主刀医生"))
				{
					iOpeEmpDao.delete(opeEmpa);
				}
				else {
					emplist.add(opeEmpa);
				}
			}
			
			
		}
		for (String string : a) {
			String b[]=string.split(":");
			if(!string.substring(0, 9).equals("undefined")) {		
				OpeEmp opeEmp=new OpeEmp();
			    opeEmp.setEmpInformation(iEmpInformationDao.findEmpInformationByYgGh(b[0]));
			    opeEmp.setOperationRecord(iOperationRecordDao.findById(opeid).get());	
			    opeEmp.setDuty(b[1].substring(b[1].length()-4, b[1].length()));
			    OpeEmpPK pk=new OpeEmpPK();
			    pk.setOpeId(opeid);
			    pk.setYgxh(iEmpInformationDao.findEmpInformationByYgGh(b[0]).getYgxh());
			    opeEmp.setId(pk);
			    iOpeEmpDao.save(opeEmp);
			    emplist.add(opeEmp);
			}
			else {
				
			}
		}
		
	    operationRecord.setOpeEmps(emplist);
		iOperationRecordDao.save(operationRecord);
	}
	/**
	 * 
	* @Title:getopemp
	* @Description:TODO根据手术项名获得可以做该手术的人员
	* @param:@param opename
	* @param:@return
	* @return:Map
	* @throws
	* @author:TRC
	* @Date:2019年8月7日 下午4:23:57
	 */
    public Map getopemp(String opename) {
    	Role role=iRoleDao.getRole(opename+"助手");
    	List<UserRole> list=role.getUserRoles();
        List emplist=new ArrayList<EmpInformation>();
        for (UserRole userRole : list) {	
        	emplist.add(userRole.getEmpInformation().getYgGh()+":"+userRole.getEmpInformation().getYgName());
		}
        Role roletwo=iRoleDao.getRole(opename+"护士");
        List<UserRole> listtwo=roletwo.getUserRoles();
        List emplisttwo=new ArrayList<EmpInformation>();
        for (UserRole userRoletwo : listtwo) {	
        	emplisttwo.add(userRoletwo.getEmpInformation().getYgGh()+":"+userRoletwo.getEmpInformation().getYgName());
		}
        Map map=new HashMap();
        map.put("zslist", emplist);
        map.put("hslist", emplisttwo);
        return map;
    }
    /**
     * 
    * @Title:getopeempbyopeid
    * @Description:TODO根据手术记录id查员工
    * @param:@param opeid
    * @param:@return
    * @return:List<Opeempbean>
    * @throws
    * @author:TRC
    * @Date:2019年8月7日 下午4:47:33
     */
    public List<Opeempbean> getopeempbyopeid(String opeid){
    	return iOpeEmpDao.getOpeEmps(opeid);
    }
}
