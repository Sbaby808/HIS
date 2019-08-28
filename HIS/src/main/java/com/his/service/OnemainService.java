package com.his.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.naming.java.javaURLContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IEmpInformationDao;
import com.his.dao.IWktimeEmpDAO;
import com.his.dao.IWorkTimeDao;
import com.his.pojo.EmpInformation;
import com.his.pojo.WorkTime;

/**  
* @ClassName: OnemainService  
* @Description: TODO(个人主页service)  
* @author TRC
* @date 2019年8月22日  上午11:44:45
*    
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class OnemainService {
	@Autowired
	private IEmpInformationDao iEmpInformationDao;
	@Autowired
	private IWorkTimeDao iWorkTimeDao;
	@Autowired
	private IWktimeEmpDAO iWktimeempDao;
	/**
	 * 
	* @Title:getbyid
	* @Description:TODO根据id获得员工信息
	* @param:@param id
	* @param:@return
	* @return:EmpInformation
	* @throws
	* @author:TRC
	* @Date:2019年8月22日 下午5:06:09
	 */
	public EmpInformation getbyid(String id) {
		EmpInformation emp=iEmpInformationDao.findById(id).get();
		return emp;
	}
	/**
	 * 
	* @Title:editemp
	* @Description:TODO修改员工信息个人
	* @param:@param emp
	* @return:void
	* @throws
	* @author:TRC
	* @Date:2019年8月22日 下午5:05:48
	 */
	public void editemp(EmpInformation emp) {
	System.out.println(	emp.getYgxh());
		iEmpInformationDao.save(emp);
	}
	/**
	 * 
	* @Title:getbytime
	* @Description:TODO获得本周的个人排班
	* @param:@param date
	* @param:@return
	* @return:Map
	* @throws
	* @author:TRC
	* @Date:2019年8月22日 下午5:05:23
	 */
	public Map getbytime(List<String> date){
		String id=date.get(0);
		date.remove(0);
		Map map=new HashMap();
		List<WorkTime> zongList=new ArrayList<WorkTime>();
		for (String string : date) {
			Date time=java.sql.Date.valueOf(string);
			List<WorkTime> list=iWktimeempDao.getbytimeandid(id, time);
			zongList.addAll(list);
		}
		List<WorkTime> onelist=new ArrayList<WorkTime>();
		List<WorkTime> twolist=new ArrayList<WorkTime>();
		List<WorkTime> threelist=new ArrayList<WorkTime>();
		List<WorkTime> fourlist=new ArrayList<WorkTime>();
		List<WorkTime> fivelist=new ArrayList<WorkTime>();
		List<WorkTime> sixlist=new ArrayList<WorkTime>();
		List<WorkTime> sevenlist=new ArrayList<WorkTime>();
		for (WorkTime workTime : zongList) {
			if(workTime.getPbDate().compareTo(java.sql.Date.valueOf(date.get(0)))==0) {
				onelist.add(workTime);
			}
			else if(workTime.getPbDate().compareTo(java.sql.Date.valueOf(date.get(1)))==0) {
				twolist.add(workTime);
			}
			else if(workTime.getPbDate().compareTo(java.sql.Date.valueOf(date.get(2)))==0) {
				threelist.add(workTime);
			}
			else if(workTime.getPbDate().compareTo(java.sql.Date.valueOf(date.get(3)))==0) {
				fourlist.add(workTime);
			}
			else if(workTime.getPbDate().compareTo(java.sql.Date.valueOf(date.get(4)))==0) {
				fivelist.add(workTime);
			}
			else if(workTime.getPbDate().compareTo(java.sql.Date.valueOf(date.get(5)))==0) {
				sixlist.add(workTime);
			}
			else if(workTime.getPbDate().compareTo(java.sql.Date.valueOf(date.get(6)))==0) {
				sevenlist.add(workTime);
			}
			else {}
		}
		map.put("onelist", onelist);
		map.put("twolist", twolist);
		map.put("threelist", threelist);
		map.put("fourlist", fourlist);
		map.put("fivelist", fivelist);
		map.put("sixlist", sixlist);
		map.put("sevenlist", sevenlist);
		return map;
	}

}
