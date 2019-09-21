package com.his.service;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.w3c.dom.ls.LSInput;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.his.bean.Piebean;
import com.his.dao.IAskleaveRecordDao;
import com.his.dao.IEmpInformationDao;
import com.his.dao.IWktimeEmpDAO;
import com.his.dao.IWorkTimeDao;
import com.his.pojo.AskleaveRecord;
import com.his.pojo.EmpInformation;
import com.his.pojo.WktimeEmp;
import com.his.pojo.WktimeEmpPK;
import com.his.pojo.WorkTime;

import com.his.utils.UUIDGenerator;

import oracle.net.aso.a;
import oracle.net.aso.d;


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
	@Autowired
	private IAskleaveRecordDao iAskleaveRecordDao;
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
	public Map getbytime(String time,String ygxh){
		if(time.equals("本周")) {
		Calendar c = Calendar.getInstance();  
        c.setTime(new Date());
        Date date = c.getTime();
        int day=date.getDay();
        int hou=7-day;
        int qian=day-1;
        List<Date> list=new ArrayList<Date>();
        for(int i=qian;i>0;i--) {
        	c.setTime(date);
        	c.add(Calendar.DATE, -i);
        	list.add(c.getTime());
        }
        for(int j=0;j<=hou;j++) {
        	c.setTime(date);
        	c.add(Calendar.DATE, j);
        	list.add(c.getTime());
        }
       return getgrwork(ygxh,list);
		}
		else {
			Calendar c = Calendar.getInstance();  
	        c.setTime(new Date());
	        Date date = c.getTime();
	        int day=date.getDay();
	        int hou=7-day;
	        c.add(Calendar.DATE, hou);
	        List<Date> list=new ArrayList<Date>();
	        for(int i=1;i<=7;i++) {
	        	c.add(Calendar.DATE, 1);
	        	list.add(c.getTime());
	        }
	       return getgrwork(ygxh,list);
		}
		
	}
	/**
	 * 
	* @Title:getgrwork
	* @Description:TODO获得本周的个人排班
	* @param:@param ygxh
	* @param:@param list
	* @param:@return
	* @return:Map
	* @throws
	* @author:TRC
	* @Date:2019年9月3日 上午9:42:27
	 */
	public Map getgrwork(String ygxh,List<Date> list) {
		Map map=new HashMap();
		List<WorkTime> oneList=iWktimeempDao.getbytimeandid(ygxh, list.get(0));
        List<WorkTime> twoList=iWktimeempDao.getbytimeandid(ygxh, list.get(1));
        List<WorkTime> threeList=iWktimeempDao.getbytimeandid(ygxh, list.get(2));
        List<WorkTime> fourList=iWktimeempDao.getbytimeandid(ygxh, list.get(3));
        List<WorkTime> fiveList=iWktimeempDao.getbytimeandid(ygxh, list.get(4));
        List<WorkTime> sixList=iWktimeempDao.getbytimeandid(ygxh, list.get(5));
        List<WorkTime> sevenList=iWktimeempDao.getbytimeandid(ygxh, list.get(6));
        map.put("oneList", oneList);
        map.put("twoList", twoList);
        map.put("threeList", threeList);
        map.put("fourList", fourList);
        map.put("fiveList", fiveList);
        map.put("sixList", sixList);
        map.put("sevenList", sevenList);
		return map;
	}
	/**
	 * 
	* @Title:askqinjia
	* @Description:TODO申请请假
	* @param:@param askleaveRecord
	* @param:@return
	* @return:String
	* @throws
	* @author:TRC
	* @Date:2019年9月3日 上午9:58:34
	 */
	public String askqinjia(AskleaveRecord askleaveRecord) {
		Date date=new Date();
		Calendar c = Calendar.getInstance();  
        c.setTime(date);
        c.add(Calendar.DATE, 1);
        Date time = c.getTime();
		if(askleaveRecord.getAskLeaveTime().compareTo(time)==-1) {
			return "你只能请明天之后的假";
		}
		else {
		UUIDGenerator  uuid=new UUIDGenerator();
		askleaveRecord.setOffId(uuid.getUUID());
		askleaveRecord.setEmpInformation(iEmpInformationDao.findById(askleaveRecord.getStatus()).get());
		askleaveRecord.setStatus("未处理");
		askleaveRecord.setWhetheragree("未批准");
		iAskleaveRecordDao.save(askleaveRecord);
		return "请假申请已发送";
		}
	}
	/**
	 * 
	* @Title:getoneask
	* @Description:TODO获得个人请假申请
	* @param:@param ygxh
	* @param:@return
	* @return:Map
	* @throws
	* @author:TRC
	* @Date:2019年9月4日 下午4:22:58
	 */
	public Map getoneask(String ygxh) {
		List<AskleaveRecord> listwei=iAskleaveRecordDao.getwei(ygxh);
		List<AskleaveRecord> listyi=iAskleaveRecordDao.getyi(ygxh);
		Map map=new HashMap();
		map.put("listwei", listwei);
		map.put("listyi", listyi);
		return map;
	}
	/**
	 * 
	* @Title:getksask
	* @Description:TODO根据排班人员的id查该科室的未处理的请假记录
	* @param:@param ygxh
	* @param:@return
	* @return:Map
	* @throws
	* @author:TRC
	* @Date:2019年9月4日 下午10:18:37
	 */
	public Map getksask(String ygxh) {
		String ksid=iWorkTimeDao.getksid(ygxh);
		List<AskleaveRecord> list=iAskleaveRecordDao.getksask(ksid);
		int total=list.size();
		Map map=new HashMap();
		map.put("kslist", list);
		map.put("total", total);
		return map;
	}
	/**
	 * 
	* @Title:agreeask
	* @Description:TODO批准请假
	* @param:@param ygxh
	* @param:@param time
	* @param:@param type
	* @param:@param askid
	* @return:void
	* @throws
	* @author:TRC
	* @Date:2019年9月4日 下午11:25:13
	 */
	public void agreeask(String ygxh,long time,String type,String askid) {
		Date date=new Date(time);
	AskleaveRecord aRecord=iAskleaveRecordDao.findById(askid).get();
		aRecord.setStatus("已处理");
		aRecord.setWhetheragree("批准");
        iAskleaveRecordDao.save(aRecord);
        WktimeEmp wktimeEmp=iWktimeempDao.getbytimeandygxh(ygxh, date, type);
        wktimeEmp.setState("请假");
        iWktimeempDao.save(wktimeEmp);
	}
	/**
	 * 
	* @Title:noagreeask
	* @Description:TODO不批准请假
	* @param:@param ygxh
	* @param:@param time
	* @param:@param type
	* @param:@param askid
	* @return:void
	* @throws
	* @author:TRC
	* @Date:2019年9月4日 下午11:25:41
	 */
	public void noagreeask(String ygxh,long time,String type,String askid) {
		Date date=new Date(time);
	AskleaveRecord aRecord=iAskleaveRecordDao.findById(askid).get();
		aRecord.setStatus("已处理");
		aRecord.setWhetheragree("不批准");
        iAskleaveRecordDao.save(aRecord);
	}
	/**
	 * 
	* @Title:getdealask
	* @Description:TODO获得已处理的请假请求
	* @param:@param curpage
	* @param:@param pagesize
	* @param:@param name
	* @param:@param starttimea
	* @param:@param endtimea
	* @param:@param ygxh
	* @param:@return
	* @return:Map
	* @throws
	* @author:TRC
	* @Date:2019年9月10日 下午3:41:39
	 */
	public Map getdealask(int curpage,int pagesize,String name,Date starttimea,Date endtimea,String ygxh) {
		String ksid=iWorkTimeDao.getksid(ygxh);
		Map map=new HashMap();
		if(name.equals("")&&starttimea==null) {
		List<AskleaveRecord> list=iAskleaveRecordDao.getdealask(ksid, PageRequest.of(curpage - 1,
				  pagesize));
		int total=iAskleaveRecordDao.getdealaskcount(ksid).size();
		map.put("list", list);
		map.put("total", total);
		
		}
		else if (!name.equals("")&&starttimea==null) {
			List<AskleaveRecord> list=iAskleaveRecordDao.getdealaskbyname(ksid,"%"+name+"%",PageRequest.of(curpage - 1,
					  pagesize));
			int total=iAskleaveRecordDao.getdealaskbynamecount(ksid, "%"+name+"%").size();
			map.put("list", list);
			map.put("total", total);
			
		}
		else if (name.equals("")&&starttimea!=null) {
			Calendar c = Calendar.getInstance();  
	        c.setTime(starttimea);
	        c.add(Calendar.DATE, 1);
	        Date starttime = c.getTime();
	        Calendar c1 = Calendar.getInstance();  
	        c1.setTime(endtimea);
	        c1.add(Calendar.DATE, 1);
	        Date endtime = c1.getTime();
			List<AskleaveRecord> list=iAskleaveRecordDao.getdealaskbytime(ksid,starttime,endtime,PageRequest.of(curpage - 1,
					  pagesize));
			int total=iAskleaveRecordDao.getdealaskbytimecount(ksid, starttime,endtime).size();
			map.put("list", list);
			map.put("total", total);
			
		}
		else if (!name.equals("")&&starttimea!=null) {
			Calendar c = Calendar.getInstance();  
	        c.setTime(starttimea);
	        c.add(Calendar.DATE, 1);
	        Date starttime = c.getTime();
	        Calendar c1 = Calendar.getInstance();  
	        c1.setTime(endtimea);
	        c1.add(Calendar.DATE, 1);
	        Date endtime = c1.getTime();
			List<AskleaveRecord> list=iAskleaveRecordDao.getdealaskbytimeandname(ksid,"%"+name+"%",starttime,endtime,PageRequest.of(curpage - 1,
					  pagesize));
			int total=iAskleaveRecordDao.getdealaskbytimeandnamecount(ksid, "%"+name+"%",starttime,endtime).size();
			map.put("list", list);
			map.put("total", total);
			
		}
		else {
			
		}
		return map;
	}
	/**
	 * 
	* @Title:checkwork
	* @Description:TODO考勤
	* @param:@param empInformation
	* @return:void
	* @throws
	* @author:TRC
	* @Date:2019年9月6日 下午6:11:49
	 */
	public String  checkwork(String ygxh) {
		String string="";
		Date date=new Date();
		int hours=date.getHours();
		Date whiteDate=new Date();
		whiteDate.setHours(8);
		whiteDate.setMinutes(10);
		Date whDateenDate=new Date();
		whDateenDate.setHours(18);
		Date nightDate=new Date();
		nightDate.setHours(18);
		nightDate.setMinutes(10);
		Date nenDate=new Date();
		nenDate.setHours(23);
		nenDate.setMinutes(59);
		nenDate.setSeconds(59);
		Calendar c = Calendar.getInstance();  
        c.setTime(date);
        c.add(Calendar.DATE, -1);
        Date time = c.getTime();
        WktimeEmp wktimeEmp=iWktimeempDao.getbytimeandygxh(ygxh, time, "晚班");
        if(wktimeEmp!=null&&wktimeEmp.getEarlyleave()==null&&hours<12&&(wktimeEmp.getState().equals("迟到")||wktimeEmp.getState().equals("正在上班"))) {
        	if(date.compareTo(whiteDate)==-1) {
        		wktimeEmp.setEarlyleave("早退");
        		iWktimeempDao.save(wktimeEmp);
        		string="你早退了哦！";
        	}
        	else {
        		wktimeEmp.setEarlyleave("正常下班");
        		BigDecimal over=new BigDecimal(hours-8);
        		wktimeEmp.setOvertime(over);
        		iWktimeempDao.save(wktimeEmp);
        		string="又是充实的一天，回家好好休息！";
        	}
        }
        else {
		List<WktimeEmp> list=iWktimeempDao.getbytimeandempid(ygxh, date);
		if(!list.isEmpty()) {
			String type=list.get(0).getWorkTime().getPbType();
			if(type.equals("白班")) {
				if(list.get(0).getState().equals("旷工")||list.get(0).getState().equals("请假")) {
					if(date.compareTo(whiteDate)==-1) {
						list.get(0).setState("正在上班");
						iWktimeempDao.save(list.get(0));
						string="你很准时";
					}
					else {
						list.get(0).setState("迟到");
						iWktimeempDao.save(list.get(0));
						string="你迟到了";
					}
				}
				else if((list.get(0).getState().equals("正在上班")||list.get(0).getState().equals("迟到"))&&list.get(0).getEarlyleave()==null){
					if(date.compareTo(whDateenDate)==-1) {
						list.get(0).setEarlyleave("早退");
						iWktimeempDao.save(list.get(0));
						string="你早退了哦！";
					}
					else {
						list.get(0).setEarlyleave("正常下班");
						BigDecimal over=new BigDecimal(date.getHours()-18);
						list.get(0).setOvertime(over);
						iWktimeempDao.save(list.get(0));
						string="又是充实的一天，回家好好休息！";
					}
				}
				else {
					string="你已经打过卡了";
				}
			}
			else {
				if(list.get(0).getState().equals("旷工")||list.get(0).getState().equals("请假")) {
					if(date.compareTo(nightDate)==-1) {
						list.get(0).setState("正在上班");
						iWktimeempDao.save(list.get(0));
						string="你很准时";
					}
					else {
						list.get(0).setState("迟到");
						iWktimeempDao.save(list.get(0));
						string="你迟到了";
					}
			}
				else if(list.get(0).getState().equals("正在上班")||list.get(0).getState().equals("迟到")){
					if(date.compareTo(nenDate)==-1) {
						list.get(0).setEarlyleave("早退");
						iWktimeempDao.save(list.get(0));
						string="你早退了哦！";
					}
					else {
						
					}
				}
				else {
					
				}
		}
	}
		else {
			string="你今天不用上班！快去休息吧";
		}
      }
		return string;
   }
	/**
	 * 
	* @Title:chaban
	* @Description:TODO打卡按钮内容
	* @param:@param ygxh
	* @param:@return
	* @return:String
	* @throws
	* @author:TRC
	* @Date:2019年9月10日 下午3:43:01
	 */
	public String chaban(String ygxh) {
		String string="";
		Date date=new Date();
		Calendar c = Calendar.getInstance();  
        c.setTime(date);
        c.add(Calendar.DATE, -1);
        Date time = c.getTime();
        WktimeEmp wktimeEmp=iWktimeempDao.getbytimeandygxh(ygxh, time, "晚班");
        if(wktimeEmp!=null&&(wktimeEmp.getState().equals("迟到")||wktimeEmp.getState().equals("正在上班"))&&wktimeEmp.getEarlyleave()==null&&date.getHours()<10) {
        	string="点我打卡下班";
        }
        else {
        	
        	List<WktimeEmp> list=iWktimeempDao.getbytimeandempid(ygxh, date);
        	if(!list.isEmpty()) {
        		if(list.get(0).getState().equals("正在上班")||list.get(0).getState().equals("迟到")) {
        			string="点我打卡下班";
        		}
        		else {
        			string="点我打卡上班";
        		}
        	}
        	else {
        		string="你今天没有班哦！";
        	}
        }
        return string;
	}
	/**
	 * 
	* @Title:getpie
	* @Description:TODO统计迟到，早退，旷工，加班的天数
	* @param:@param ygxh
	* @param:@return
	* @return:List<Piebean>
	* @throws
	* @author:TRC
	* @Date:2019年9月10日 下午5:19:48
	 */
	public List<Piebean> getpie(String ygxh) {
		Calendar c = Calendar.getInstance();    
	       c.set(Calendar.DAY_OF_MONTH,1);
        Date date=c.getTime();
        Calendar ca = Calendar.getInstance();    
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));  
        Date lastDate=ca.getTime();
        int zaotui=iWktimeempDao.getzaotui(ygxh, date, lastDate);
        int chidao=iWktimeempDao.getchidao(ygxh, date, lastDate);
        int kuanggong=iWktimeempDao.getkuanggong(ygxh, date, new Date());
        int jiaban=iWktimeempDao.getjiaban(ygxh, date, lastDate);
        List<Piebean> pielist=new ArrayList<Piebean>();
        Piebean onePiebean=new Piebean(zaotui,"早退");
        Piebean twoPiebean=new Piebean(chidao,"迟到");
        Piebean threePiebean=new Piebean(kuanggong,"旷工");
        Piebean fourPiebean=new Piebean(jiaban,"加班");
        pielist.add(onePiebean);
        pielist.add(twoPiebean);
        pielist.add(threePiebean);
        pielist.add(fourPiebean);
        return pielist;
	}
	

}
