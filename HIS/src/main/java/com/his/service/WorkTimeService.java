package com.his.service;





import static org.hamcrest.CoreMatchers.nullValue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.bean.Five;
import com.his.bean.Four;
import com.his.bean.Night;
import com.his.bean.One;
import com.his.bean.Seven;
import com.his.bean.Six;
import com.his.bean.Three;
import com.his.bean.Two;
import com.his.bean.White;
import com.his.bean.Zong;
import com.his.dao.IRoleDao;
import com.his.dao.IWktimeEmpDAO;
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
	private IWktimeEmpDAO iWktimeempDao;
	
	
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
	public Zong getworktimezhou(Date time,String ygxh) {
		Zong zong=new Zong();
		One one=new One();
		Two two=new Two();
		Three three=new Three();
		Four four=new Four();
		Five five=new Five();
		Six six=new Six();
		Seven seven=new Seven();
		Calendar c = Calendar.getInstance();  
        c.setTime(time);
        c.add(Calendar.DATE, 1); 
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
        String ksid=iWktimeempDao.getksid(ygxh);
        //周一
        White onewhite=new White();
		Night onenight=new Night();
		onewhite.setZrlist(iWorkTimeDao.getemps(ksid, list.get(0), "白班", "主任医生"));
		onewhite.setFzrlist(iWorkTimeDao.getemps(ksid, list.get(0), "白班", "副主任医生"));
		onewhite.setHslist(iWorkTimeDao.getemps(ksid, list.get(0), "白班", "护士"));
		onenight.setZrlist(iWorkTimeDao.getemps(ksid, list.get(0), "晚班", "主任医生"));
		onenight.setFzrlist(iWorkTimeDao.getemps(ksid, list.get(0), "晚班", "副主任医生"));
		onenight.setHslist(iWorkTimeDao.getemps(ksid, list.get(0), "晚班", "护士"));
        one.setNight(onenight);
        one.setWhite(onewhite);
        //周二
        White twowhite=new White();
		Night twonight=new Night();
		twowhite.setZrlist(iWorkTimeDao.getemps(ksid, list.get(1), "白班", "主任医生"));
		twowhite.setFzrlist(iWorkTimeDao.getemps(ksid, list.get(1), "白班", "副主任医生"));
		twowhite.setHslist(iWorkTimeDao.getemps(ksid, list.get(1), "白班", "护士"));
		twonight.setZrlist(iWorkTimeDao.getemps(ksid, list.get(1), "晚班", "主任医生"));
		twonight.setFzrlist(iWorkTimeDao.getemps(ksid, list.get(1), "晚班", "副主任医生"));
		twonight.setHslist(iWorkTimeDao.getemps(ksid, list.get(1), "晚班", "护士"));
     
        two.setNight(twonight);
        two.setWhite(twowhite);
        //周三
        White threewhite=new White();
		Night threenight=new Night();
		threewhite.setZrlist(iWorkTimeDao.getemps(ksid, list.get(2), "白班", "主任医生"));
		threewhite.setFzrlist(iWorkTimeDao.getemps(ksid, list.get(2), "白班", "副主任医生"));
		threewhite.setHslist(iWorkTimeDao.getemps(ksid, list.get(2), "白班", "护士"));
		threenight.setZrlist(iWorkTimeDao.getemps(ksid, list.get(2), "晚班", "主任医生"));
		threenight.setFzrlist(iWorkTimeDao.getemps(ksid, list.get(2), "晚班", "副主任医生"));
		threenight.setHslist(iWorkTimeDao.getemps(ksid, list.get(2), "晚班", "护士"));
        
        three.setNight(threenight);
        three.setWhite(threewhite);
        //周四
        White fourwhite=new White();
		Night fournight=new Night();
		fourwhite.setZrlist(iWorkTimeDao.getemps(ksid, list.get(3), "白班", "主任医生"));
		fourwhite.setFzrlist(iWorkTimeDao.getemps(ksid, list.get(3), "白班", "副主任医生"));
		fourwhite.setHslist(iWorkTimeDao.getemps(ksid, list.get(3), "白班", "护士"));
		fournight.setZrlist(iWorkTimeDao.getemps(ksid, list.get(3), "晚班", "主任医生"));
		fournight.setFzrlist(iWorkTimeDao.getemps(ksid, list.get(3), "晚班", "副主任医生"));
		fournight.setHslist(iWorkTimeDao.getemps(ksid, list.get(3), "晚班", "护士"));
        
        four.setNight(fournight);
        four.setWhite(fourwhite);
        //周五
        White fivewhite=new White();
		Night fivenight=new Night();
		fivewhite.setZrlist(iWorkTimeDao.getemps(ksid, list.get(4), "白班", "主任医生"));
		fivewhite.setFzrlist(iWorkTimeDao.getemps(ksid, list.get(4), "白班", "副主任医生"));
		fivewhite.setHslist(iWorkTimeDao.getemps(ksid, list.get(4), "白班", "护士"));
		fivenight.setZrlist(iWorkTimeDao.getemps(ksid, list.get(4), "晚班", "主任医生"));
		fivenight.setFzrlist(iWorkTimeDao.getemps(ksid, list.get(4), "晚班", "副主任医生"));
		fivenight.setHslist(iWorkTimeDao.getemps(ksid, list.get(4), "晚班", "护士"));
        
        five.setNight(fivenight);
        five.setWhite(fivewhite);
        //周六
        White sixwhite=new White();
		Night sixnight=new Night();
		sixwhite.setZrlist(iWorkTimeDao.getemps(ksid, list.get(5), "白班", "主任医生"));
		sixwhite.setFzrlist(iWorkTimeDao.getemps(ksid, list.get(5), "白班", "副主任医生"));
		sixwhite.setHslist(iWorkTimeDao.getemps(ksid, list.get(5), "白班", "护士"));
		sixnight.setZrlist(iWorkTimeDao.getemps(ksid, list.get(5), "晚班", "主任医生"));
		sixnight.setFzrlist(iWorkTimeDao.getemps(ksid, list.get(5), "晚班", "副主任医生"));
		sixnight.setHslist(iWorkTimeDao.getemps(ksid, list.get(5), "晚班", "护士"));
        six.setNight(sixnight);
        six.setWhite(sixwhite);
        //周日
        White sevenwhite=new White();
		Night sevennight=new Night();
		sevenwhite.setZrlist(iWorkTimeDao.getemps(ksid, list.get(6), "白班", "主任医生"));
		sevenwhite.setFzrlist(iWorkTimeDao.getemps(ksid, list.get(6), "白班", "副主任医生"));
		sevenwhite.setHslist(iWorkTimeDao.getemps(ksid, list.get(6), "白班", "护士"));
		sevennight.setZrlist(iWorkTimeDao.getemps(ksid, list.get(6), "晚班", "主任医生"));
		sevennight.setFzrlist(iWorkTimeDao.getemps(ksid, list.get(6), "晚班", "副主任医生"));
		sevennight.setHslist(iWorkTimeDao.getemps(ksid, list.get(6), "晚班", "护士"));
        seven.setNight(sevennight);
        seven.setWhite(sevenwhite);
        zong.setOnelist(one);
        zong.setTwolist(two);
        zong.setThreelist(three);
        zong.setFourlist(four);
        zong.setFivelist(five);
        zong.setSixlist(six);
        zong.setSevenlist(seven);
        
        
        return zong;
	}
	
	public String addworkzhou(Zong zong) {
		String ygxh=zong.getYgxh();
		UUIDGenerator uuid=new UUIDGenerator();
		Calendar c = Calendar.getInstance();  
        c.setTime(zong.getTime());
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
       
        //删除
       
        String ksid=iWktimeempDao.getksid(ygxh);
        System.err.println(ygxh);
        System.err.println(ksid);
        String onepbid[]=iWorkTimeDao.getpbid(ksid,list.get(0), "白班");
        
        if(onepbid.length!=0) {
        
        List<WktimeEmp> oneEmps=iWktimeempDao.getemps(onepbid[0]);
        for (WktimeEmp wktimeEmp : oneEmps) {
			iWktimeempDao.delete(wktimeEmp);
        }
        iWorkTimeDao.deleteById(onepbid[0]);
        }
        else {}
        String onepbidwan[]=iWorkTimeDao.getpbid(ksid,list.get(0), "晚班");
        if(onepbidwan.length!=0) {
        List<WktimeEmp> oneEmpswan=iWktimeempDao.getemps(onepbidwan[0]);
        for (WktimeEmp wktimeEmp : oneEmpswan) {
			iWktimeempDao.delete(wktimeEmp);
		}
        iWorkTimeDao.deleteById(onepbidwan[0]);
        }
        else {}
        //周二
        String  twopbid[]=iWorkTimeDao.getpbid(ksid,list.get(1), "白班");
        if(twopbid.length!=0) {
        List<WktimeEmp> twoEmps=iWktimeempDao.getemps(twopbid[0]);
        for (WktimeEmp wktimeEmp : twoEmps) {
			iWktimeempDao.delete(wktimeEmp);
		}
        iWorkTimeDao.deleteById(twopbid[0]);
        }
        else {}
        
        String twopbidwan[]=iWorkTimeDao.getpbid(ksid,list.get(1), "晚班");
        if(twopbidwan.length!=0) {
        List<WktimeEmp> twoEmpswan=iWktimeempDao.getemps(twopbidwan[0]);
        for (WktimeEmp wktimeEmp : twoEmpswan) {
			iWktimeempDao.delete(wktimeEmp);
		}
        iWorkTimeDao.deleteById(twopbidwan[0]);
        }
        else {}
        //周三
        String threepbid[]=iWorkTimeDao.getpbid(ksid,list.get(2), "白班");
        if(threepbid.length!=0) {
        List<WktimeEmp> sanEmps=iWktimeempDao.getemps(threepbid[0]);
        for (WktimeEmp wktimeEmp : sanEmps) {
			iWktimeempDao.delete(wktimeEmp);
		}
        iWorkTimeDao.deleteById(threepbid[0]);
        }
        else {}
        String threepbidwan[]=iWorkTimeDao.getpbid(ksid,list.get(2), "晚班");
        if(threepbidwan.length!=0) {
        List<WktimeEmp> sanEmpswan=iWktimeempDao.getemps(threepbidwan[0]);
        for (WktimeEmp wktimeEmp : sanEmpswan) {
			iWktimeempDao.delete(wktimeEmp);
		}
        iWorkTimeDao.deleteById(threepbidwan[0]);
        }
        else {}
        //周四
        String fourpbid[]=iWorkTimeDao.getpbid(ksid,list.get(3), "白班");
        System.err.println(fourpbid);
        if(fourpbid.length!=0) {
        List<WktimeEmp> fourEmps=iWktimeempDao.getemps(fourpbid[0]);
        for (WktimeEmp wktimeEmp : fourEmps) {
			iWktimeempDao.delete(wktimeEmp);
		}
        iWorkTimeDao.deleteById(fourpbid[0]);
        }
        else {}
        String fouurpbidwan[]=iWorkTimeDao.getpbid(ksid,list.get(3), "晚班");
        System.err.println(fouurpbidwan);
        if(fouurpbidwan.length!=0) {
        List<WktimeEmp> fourEmpswan=iWktimeempDao.getemps(fouurpbidwan[0]);
        for (WktimeEmp wktimeEmp : fourEmpswan) {
			iWktimeempDao.delete(wktimeEmp);
		}
        iWorkTimeDao.deleteById(fouurpbidwan[0]);
        }
        else {}
        //周五
        String fivepbid[]=iWorkTimeDao.getpbid(ksid,list.get(4), "白班");
        if(fivepbid.length!=0) {
        List<WktimeEmp> fiveEmps=iWktimeempDao.getemps(fivepbid[0]);
        for (WktimeEmp wktimeEmp : fiveEmps) {
			iWktimeempDao.delete(wktimeEmp);
		}
        iWorkTimeDao.deleteById(fivepbid[0]);
        }
        else {}
        String fivepbidwan[]=iWorkTimeDao.getpbid(ksid,list.get(4), "晚班");
        if(fivepbidwan.length!=0) {
        List<WktimeEmp> fiveEmpswan=iWktimeempDao.getemps(fivepbidwan[0]);
        for (WktimeEmp wktimeEmp : fiveEmpswan) {
			iWktimeempDao.delete(wktimeEmp);
		}
        iWorkTimeDao.deleteById(fivepbidwan[0]);
        }
        else {}
        //周六
        String sixpbid[]=iWorkTimeDao.getpbid(ksid,list.get(5), "白班");
        if(sixpbid.length!=0) {
        List<WktimeEmp> sixEmps=iWktimeempDao.getemps(sixpbid[0]);
        for (WktimeEmp wktimeEmp : sixEmps) {
			iWktimeempDao.delete(wktimeEmp);
		}
        iWorkTimeDao.deleteById(sixpbid[0]);
        }
        else {}
        String sixpbidwan[]=iWorkTimeDao.getpbid(ksid,list.get(5), "晚班");
        if(sixpbidwan.length!=0) {
        List<WktimeEmp> sixEmpswan=iWktimeempDao.getemps(sixpbidwan[0]);
        for (WktimeEmp wktimeEmp : sixEmpswan) {
			iWktimeempDao.delete(wktimeEmp);
		}
        iWorkTimeDao.deleteById(sixpbidwan[0]);
        }
        else {}
        //周日
        String sevenpbid[]=iWorkTimeDao.getpbid(ksid,list.get(6), "白班");
        if(sevenpbid.length!=0) {
        List<WktimeEmp> sevenEmps=iWktimeempDao.getemps(sevenpbid[0]);
        for (WktimeEmp wktimeEmp : sevenEmps) {
			iWktimeempDao.delete(wktimeEmp);
		}
        iWorkTimeDao.deleteById(sevenpbid[0]);
        }
        else {}
        String sevenpbidwan[]=iWorkTimeDao.getpbid(ksid,list.get(6), "晚班");
        if(sevenpbidwan.length!=0) {
        List<WktimeEmp> sevenEmpswan=iWktimeempDao.getemps(sevenpbidwan[0]);
        for (WktimeEmp wktimeEmp : sevenEmpswan) {
			iWktimeempDao.delete(wktimeEmp);
		}
        iWorkTimeDao.deleteById(sevenpbidwan[0]);
        }
        else {}
        //添加
        //周一
       addtimework(zong.getOnelist().getWhite().getZrlist(), zong.getOnelist().getWhite().getFzrlist(), zong.getOnelist().getWhite().getHslist(), uuid, list.get(0), "白班",ygxh);
       addtimework(zong.getOnelist().getNight().getZrlist(), zong.getOnelist().getNight().getFzrlist(), zong.getOnelist().getNight().getHslist(), uuid, list.get(0), "晚班",ygxh);
       //周二
       addtimework(zong.getTwolist().getWhite().getZrlist(), zong.getTwolist().getWhite().getFzrlist(), zong.getTwolist().getWhite().getHslist(), uuid, list.get(1), "白班",ygxh);
       addtimework(zong.getTwolist().getNight().getZrlist(), zong.getTwolist().getNight().getFzrlist(), zong.getTwolist().getNight().getHslist(), uuid, list.get(1), "晚班",ygxh);
       //周三
       addtimework(zong.getThreelist().getWhite().getZrlist(), zong.getThreelist().getWhite().getFzrlist(), zong.getThreelist().getWhite().getHslist(), uuid, list.get(2), "白班",ygxh);
       addtimework(zong.getThreelist().getNight().getZrlist(), zong.getThreelist().getNight().getFzrlist(), zong.getThreelist().getNight().getHslist(), uuid, list.get(2), "晚班",ygxh);
       //周四
       addtimework(zong.getFourlist().getWhite().getZrlist(), zong.getFourlist().getWhite().getFzrlist(), zong.getFourlist().getWhite().getHslist(), uuid, list.get(3), "白班",ygxh);
       addtimework(zong.getFourlist().getNight().getZrlist(), zong.getFourlist().getNight().getFzrlist(), zong.getFourlist().getNight().getHslist(), uuid, list.get(3), "晚班",ygxh);
	   //周五
       addtimework(zong.getFivelist().getWhite().getZrlist(), zong.getFivelist().getWhite().getFzrlist(), zong.getFivelist().getWhite().getHslist(), uuid, list.get(4), "白班",ygxh);
       addtimework(zong.getFivelist().getNight().getZrlist(), zong.getFivelist().getNight().getFzrlist(), zong.getFivelist().getNight().getHslist(), uuid, list.get(4), "晚班",ygxh);
       //周六
       addtimework(zong.getSixlist().getWhite().getZrlist(), zong.getSixlist().getWhite().getFzrlist(), zong.getSixlist().getWhite().getHslist(), uuid, list.get(5), "白班",ygxh);
       addtimework(zong.getSixlist().getNight().getZrlist(), zong.getSixlist().getNight().getFzrlist(), zong.getSixlist().getNight().getHslist(), uuid, list.get(5), "晚班",ygxh);
       //周日
       addtimework(zong.getSevenlist().getWhite().getZrlist(), zong.getSevenlist().getWhite().getFzrlist(), zong.getSevenlist().getWhite().getHslist(), uuid, list.get(6), "白班",ygxh);
       addtimework(zong.getSevenlist().getNight().getZrlist(), zong.getSevenlist().getNight().getFzrlist(), zong.getSevenlist().getNight().getHslist(), uuid, list.get(6), "晚班",ygxh);
       
        
       return "添加成功";
       
	}
	
	public void addtimework(List<EmpInformation> zrlist,List<EmpInformation> fzrlist,List<EmpInformation> hslist,UUIDGenerator uuid,Date date,String type,String ygxh) {
		if(zrlist.isEmpty()&&fzrlist.isEmpty()&&hslist.isEmpty()) {}
		else {
		String pbid=uuid.getUUID();
		WorkTime workTime=new WorkTime();
		workTime.setPbDate(date);
		workTime.setPbType(type);
		workTime.setPbId(pbid);
		iWorkTimeDao.save(workTime);
		if(!zrlist.isEmpty()) {
			for (EmpInformation empInformation : zrlist) {
				WktimeEmp wEmp=new WktimeEmp();
				wEmp.setState("矿工");
				wEmp.setWktimeDuty("主任医生");
				WktimeEmpPK pk=new WktimeEmpPK();
				pk.setPbId(pbid);
				pk.setYgxh(empInformation.getYgxh());
				wEmp.setId(pk);
				iWktimeempDao.save(wEmp);
			}
		}
		else {}
		if(!fzrlist.isEmpty()) {
			for (EmpInformation empInformation : fzrlist) {
				WktimeEmp wEmp=new WktimeEmp();
				wEmp.setState("矿工");
				wEmp.setWktimeDuty("副主任医生");
				WktimeEmpPK pk=new WktimeEmpPK();
				pk.setPbId(pbid);
				pk.setYgxh(empInformation.getYgxh());
				wEmp.setId(pk);
				iWktimeempDao.save(wEmp);
			}
		}
		else {}
		if(!hslist.isEmpty()) {
			for (EmpInformation empInformation : hslist) {
				WktimeEmp wEmp=new WktimeEmp();
				wEmp.setState("矿工");
				wEmp.setWktimeDuty("护士");
				WktimeEmpPK pk=new WktimeEmpPK();
				pk.setPbId(pbid);
				pk.setYgxh(empInformation.getYgxh());
				wEmp.setId(pk);
				iWktimeempDao.save(wEmp);
			}
		}
		else {}
		}
		
	}
	
	
	

}
