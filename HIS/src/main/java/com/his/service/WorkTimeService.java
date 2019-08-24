package com.his.service;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IRoleDao;
import com.his.dao.IWktimeempDao;
import com.his.dao.IWorkTimeDao;
import com.his.pojo.EmpInformation;
import com.his.pojo.WktimeEmp;
import com.his.pojo.WktimeEmpPK;
import com.his.pojo.WorkTime;
import com.his.utils.UUIDGenerator;

/**  
* @ClassName: WorkTimeService  
* @Description: 排班时间安排service
* @author crazy_long
* @date 2019年7月30日  下午2:25:41
*    
*/

@Service
@Transactional(rollbackFor=Exception.class)
public class WorkTimeService {
	@Autowired
	private IWorkTimeDao iWorkTimeDao;
	@Autowired
	private IRoleDao iRoleDao;
	@Autowired
	private IWktimeempDao iWktimeempDao;
	
	
	public List<EmpInformation> getallemp(String empid){
	 String	kisid=iWorkTimeDao.getksid(empid);
	 List<EmpInformation> list=iRoleDao.getallemp(kisid);
	 return list;
	}
	/**
	 * 
	* @Title:addworktime
	* @Description:TODO排班
	* @param:@param emp
	* @return:void
	* @throws
	* @author:TRC
	* @Date:2019年8月21日 下午2:33:30
	 */
	public String addworktime(List<EmpInformation> emp) {
		int a=1;
		String worktype=null;
		Date date=null;
		List<EmpInformation> zrList=new ArrayList<EmpInformation>();
		List<EmpInformation> fzrList=new ArrayList<EmpInformation>();
		List<EmpInformation> hsList=new ArrayList<EmpInformation>();
		for (EmpInformation empInformation : emp) {
			if(empInformation.getYgxh().equals("66666666")) {
				a=a+1;
				worktype=empInformation.getYgName();
				date=empInformation.getYgBirth();
			}
			else {}
			
			if(a==1) {
				zrList.add(empInformation);
			}
			else if (a==2) {
				fzrList.add(empInformation);
			}
			else if(a==3){
				hsList.add(empInformation);
			}
			else {}
		}
		List<WorkTime> wlist=iWorkTimeDao.getbydateandtype(worktype, date);
		if(wlist.isEmpty()) {
		UUIDGenerator uuid=new UUIDGenerator();
		String workid=uuid.getUUID();
		WorkTime workTime=new WorkTime();
		workTime.setPbDate(date);
		workTime.setPbType(worktype);
		workTime.setPbId(workid);
		iWorkTimeDao.save(workTime);
		for (EmpInformation zrempInformation : zrList) {
			WktimeEmp wkemp=new WktimeEmp();
			wkemp.setWktimeDuty("主任医生");
			WktimeEmpPK pk=new WktimeEmpPK();
			pk.setPbId(workid);
			pk.setYgxh(zrempInformation.getYgxh());
			wkemp.setId(pk);
			iWktimeempDao.save(wkemp);
		}
		fzrList.remove(0);
		for (EmpInformation fzrempInformation : fzrList) {
			WktimeEmp wkemp=new WktimeEmp();
			wkemp.setWktimeDuty("副主任医生");
			WktimeEmpPK pk=new WktimeEmpPK();
			pk.setPbId(workid);
			pk.setYgxh(fzrempInformation.getYgxh());
			wkemp.setId(pk);
			iWktimeempDao.save(wkemp);
		}
		hsList.remove(0);
		for (EmpInformation hsempInformation : hsList) {
			WktimeEmp wkemp=new WktimeEmp();
			wkemp.setWktimeDuty("护士");
			WktimeEmpPK pk=new WktimeEmpPK();
			pk.setPbId(workid);
			pk.setYgxh(hsempInformation.getYgxh());
			wkemp.setId(pk);
			iWktimeempDao.save(wkemp);
		  }
		    return "排班成功";
	  }
		else {
			return "您已经排过该时间的班了";
		}
  } 
	public String editpaiban(List<EmpInformation> emp) {
		int a=1;
		String worktype=null;
		Date date=null;
		List<EmpInformation> zrList=new ArrayList<EmpInformation>();
		List<EmpInformation> fzrList=new ArrayList<EmpInformation>();
		List<EmpInformation> hsList=new ArrayList<EmpInformation>();
		for (EmpInformation empInformation : emp) {
			if(empInformation.getYgxh().equals("66666666")) {
				a=a+1;
				worktype=empInformation.getYgName();
				date=empInformation.getYgBirth();
			}
			else {}
			
			if(a==1) {
				zrList.add(empInformation);
			}
			else if (a==2) {
				fzrList.add(empInformation);
			}
			else if(a==3){
				hsList.add(empInformation);
			}
			else {}
		}
		List<WorkTime> wlist=iWorkTimeDao.getbydateandtype(worktype, date);
		WorkTime workTime=wlist.get(0);
		String workid=workTime.getPbId();
		List<WktimeEmp> list=workTime.getWktimeEmps();
		for (WktimeEmp wktimeEmp : list) {
			iWktimeempDao.delete(wktimeEmp);
		}
		for (EmpInformation zrempInformation : zrList) {
			WktimeEmp wkemp=new WktimeEmp();
			wkemp.setWktimeDuty("主任医生");
			WktimeEmpPK pk=new WktimeEmpPK();
			pk.setPbId(workid);
			pk.setYgxh(zrempInformation.getYgxh());
			wkemp.setId(pk);
			iWktimeempDao.save(wkemp);
		}
		fzrList.remove(0);
		for (EmpInformation fzrempInformation : fzrList) {
			WktimeEmp wkemp=new WktimeEmp();
			wkemp.setWktimeDuty("副主任医生");
			WktimeEmpPK pk=new WktimeEmpPK();
			pk.setPbId(workid);
			pk.setYgxh(fzrempInformation.getYgxh());
			wkemp.setId(pk);
			iWktimeempDao.save(wkemp);
		}
		hsList.remove(0);
		for (EmpInformation hsempInformation : hsList) {
			WktimeEmp wkemp=new WktimeEmp();
			wkemp.setWktimeDuty("护士");
			WktimeEmpPK pk=new WktimeEmpPK();
			pk.setPbId(workid);
			pk.setYgxh(hsempInformation.getYgxh());
			wkemp.setId(pk);
			iWktimeempDao.save(wkemp);
		  }
		    
		return "修改排班成功";		
	}
	/**
	 * 
	* @Title:getempbydate
	* @Description:TODO搜索排班
	* @param:@param date
	* @param:@param type
	* @param:@return
	* @return:Map
	* @throws
	* @author:TRC
	* @Date:2019年8月21日 下午4:13:42
	 */
	public Map getempbydate(Date date,String type) {
		System.out.println(date.toString());
		 Calendar c = Calendar.getInstance();  
	        c.setTime(date);
	        c.add(Calendar.DATE, 1); 
	        Date qdate = c.getTime();
		String workid=iWorkTimeDao.getbydateandtype(type, qdate).get(0).getPbId();
		List<EmpInformation> zrList=iWktimeempDao.getbyidzr(workid);
		List<EmpInformation> fzrList=iWktimeempDao.getbyidfzr(workid);
		List<EmpInformation> hsList=iWktimeempDao.getbyidhs(workid);
		Map map=new HashMap();
		map.put("zrList", zrList);
		map.put("fzrList", fzrList);
		map.put("hsList", hsList);
		return map;
	} 

}
