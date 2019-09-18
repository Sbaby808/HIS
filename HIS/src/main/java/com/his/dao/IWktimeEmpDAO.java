package com.his.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.EmpInformation;
import com.his.pojo.WktimeEmp;
import com.his.pojo.WktimeEmpPK;
import com.his.pojo.WorkTime;

/**  
* @ClassName: IWktimeEmpDAO  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author jack
* @date 2019年8月26日  上午11:42:25
*    
*/
public interface IWktimeEmpDAO extends CrudRepository<WktimeEmp, WktimeEmpPK>{
	
	@Query(value="select w.empInformation from WktimeEmp w where w.workTime.pbId=?1 and w.wktimeDuty='主任医生'")
	public List<EmpInformation> getbyidzr(String id);
	@Query(value="select w.empInformation from WktimeEmp w where w.workTime.pbId=?1 and w.wktimeDuty='副主任医生'")
	public List<EmpInformation> getbyidfzr(String id);
	@Query(value="select w.empInformation from WktimeEmp w where w.workTime.pbId=?1 and w.wktimeDuty='护士'")
	public List<EmpInformation> getbyidhs(String id);
	@Query(value="select w from WktimeEmp w where w.workTime.pbId=?1")
	public List<WktimeEmp> getemps(String pbid);
	@Query(value="select w.workTime  from WktimeEmp w where w.empInformation.ygxh=?1 and w.workTime.pbDate=?2")
	public List<WorkTime> getbytimeandid(String id,Date date);
	@Query(value="select w from WktimeEmp w where w.empInformation.ygxh=?1 and w.workTime.pbDate=?2 and w.workTime.pbType=?3")
	public WktimeEmp getbytimeandygxh(String id,Date date,String type);
	@Query(value="select u.role.department.ksId from UserRole u where u.empInformation.ygxh=?1 and u.role.rolePosition='排班人员'")
	public String getksid(String ygxh);
	@Query(value="select w from WktimeEmp w where w.empInformation.ygxh=?1 and w.workTime.pbDate=?2")
	public List<WktimeEmp> getbytimeandempid(String id,Date date);
	@Query(value="select count(1) from WktimeEmp w where w.empInformation.ygxh=?1 and w.earlyleave='早退' and w.workTime.pbDate between ?2 and ?3")
	public int getzaotui(String ygxh,Date starttime,Date endtime);
	@Query(value="select count(1) from WktimeEmp w where w.empInformation.ygxh=?1 and w.state='迟到' and w.workTime.pbDate between ?2 and ?3")
	public int getchidao(String ygxh,Date starttime,Date endtime);
	@Query(value="select count(1) from WktimeEmp w where w.empInformation.ygxh=?1 and w.state='请假' and w.workTime.pbDate between ?2 and ?3")
	public int getqingjia(String ygxh,Date starttime,Date endtime);
	@Query(value="select count(1) from WktimeEmp w where w.empInformation.ygxh=?1 and w.state='旷工' and w.workTime.pbDate between ?2 and ?3")
	public int getkuanggong(String ygxh,Date starttime,Date endtime);
	@Query(value="select count(1) from WktimeEmp w where w.empInformation.ygxh=?1 and w.overtime!=null and w.workTime.pbDate between ?2 and ?3")
	public int getjiaban(String ygxh,Date starttime,Date endtime);
	@Query(value="select w.overtime from WktimeEmp w where w.empInformation.ygxh=?1 and w.overtime!=null and w.workTime.pbDate between ?2 and ?3")
	public List<Integer> getjiabantime(String ygxh,Date starttime,Date endtime);
}