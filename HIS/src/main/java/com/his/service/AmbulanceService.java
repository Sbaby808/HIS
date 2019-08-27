package com.his.service;

import static org.hamcrest.CoreMatchers.nullValue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IAbuEmpDAO;
import com.his.dao.IUserRoleDao;
import com.his.dao.IWktimeEmpDAO;
import com.his.dao.IWorkTimeDao;
import com.his.pojo.EmpInformation;
import com.his.pojo.WorkTime;

/**  
* @ClassName: Ambulance  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author jack
* @date 2019年8月26日  上午11:25:48
*    
*/
@Service
@Transactional(rollbackFor=Exception.class)
public class AmbulanceService {
	@Autowired
	private IUserRoleDao iUserRoleDao;
	@Autowired
	private IWktimeEmpDAO iWktimeEmpDAO;
	@Autowired
	private IWorkTimeDao iWorkTimeDao;
	@Autowired
	private IAbuEmpDAO iAbuEmpDAO;
	/**
	 * 
	* @Title:findempsbyrolename
	* @Description:通过职位名字找到这个职位所有的现在正在值班的员工
	* @param:@param rolename
	* @param:@return
	* @return:List<EmpInformation>
	* @throws
	* @author:jack
	* @Date:2019年8月26日 上午11:29:18
	 */
	public List<EmpInformation> findempsbyrolename(String rolename){
		//装最后的emps
		List<EmpInformation> emps2 = new ArrayList<EmpInformation>();
		List<EmpInformation> emps = new ArrayList<EmpInformation>();
		//找到司机职位对应的所有员工 没错
		List<EmpInformation> emps1=iUserRoleDao.findempsbyrole(rolename);
		if(emps1 == null) {
			return new ArrayList<EmpInformation>();
		} else {
			for (EmpInformation empInformation : emps1) {
				System.out.println(empInformation.getYgName());
			}
			//筛选出来今天在上班的
			SimpleDateFormat format0 = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat format1=new SimpleDateFormat("HHmmss");
			Date date = new Date();
			//获取当前时间的年月日
			String good = format0.format(date);
			//获取当前时间时分秒
			int good1 = Integer.parseInt(format1.format(date));
			//先判断当前时间是白班还是晚班
			if (60000<good1&&good1<180000) {
				//白班
				for (EmpInformation empInformation : emps1) {
					String ygxh=empInformation.getYgxh();
					//通过员工序号找到该员工排班的日期
					WorkTime workTime= iWorkTimeDao.findpbid(ygxh);
					if (workTime!=null) {
						System.out.println(workTime.getPbDate());
						String a = format0.format(workTime.getPbDate());
						if (a.equals(good)&&workTime.getPbType().equals("白班")) {
							emps.add(empInformation);
						}
					}
					
				}
			}else if ((good1>180000&&good1<240000)||(0<good1&&good1<60000)) {
				//晚班
				for (EmpInformation empInformation : emps1) {
					String ygxh=empInformation.getYgxh();
					//通过员工序号找到该员工排班的日期
					WorkTime workTime= iWorkTimeDao.findpbid(ygxh);
					if (workTime==null) {
						
					}else {
						String a = format0.format(workTime.getPbDate());
						if (a.equals(good)&&workTime.getPbType().equals("晚班")) {
							emps.add(empInformation);
						}
					}
				}
			}
			if(emps == null) {
				return new ArrayList<EmpInformation>();
			} else {
				//找到emps之后，再判断一次这些员工的状态，有没有正在出车中
				for (EmpInformation empInformation : emps) {
					String ygxh = empInformation.getYgxh();
					String status=iAbuEmpDAO.findjiuhuche(ygxh);
					//如果正在出车就去掉这个
					if (!"出车中".equals(status)||status==null) {
						emps2.add(empInformation);
						
					}
				}
			
			/*
			 * for (EmpInformation empInformation : emps) { String
			 * ygxh=empInformation.getYgxh(); //通过员工序号找到该员工排班的日期 WorkTime workTime=
			 * iWktimeEmpDAO.findpbid(ygxh); String a =
			 * format0.format(workTime.getPbDate()); if
			 * (workTime.getPbType().equals("晚班")&&a.equals(good)) { if
			 * ((good1>180000&&good1<240000)||(0<good1&&good1<60000)) {
			 * //满足条件的话，该员工就可以用，把员工添加进去 emps.add(empInformation); } } if
			 * (workTime.getPbType().equals("白班")&&a.equals(good)) { if
			 * (60000<good1&&good1<180000) { //满足条件的话，该员工就可以用，把员工添加进去
			 * emps.add(empInformation); } } }
			 */
				return emps2;
				}
		}
	}
}
