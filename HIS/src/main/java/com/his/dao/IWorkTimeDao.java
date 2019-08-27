package com.his.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.EmpInformation;
import com.his.pojo.WktimeEmp;
import com.his.pojo.WorkTime;

/**  
* @ClassName: IWorkTimeDao  
* @Description: 排班时间安排dao
* @author crazy_long
* @date 2019年7月30日  上午9:36:50
*    
*/
public interface IWorkTimeDao extends CrudRepository<WorkTime, String>{

	/**
	* @Title:getDocById
	* @Description:根据医生id查询排班（当日与明日）
	* @param:@param empId
	* @param:@return
	* @return:List<WorkTime>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月10日 上午11:29:35
	 */
	@Query(value = "select w.* from ((emp_information e left outer join wktime_emp we on e.ygxh = we.ygxh) "
       + " left outer join work_time w on we.pb_id = w.pb_id )  where e.ygxh = 'sunwukong' and we.wktime_duty = '坐诊' "
       + " and w.pb_date >  trunc(SYSDATE-1) and w.pb_date < trunc(sysdate + 2)", nativeQuery = true)
	public List<WorkTime> getDocById(String empId);
	@Query(value="select u.role.department.ksId  from UserRole u where u.empInformation.ygxh=?1 and u.role.rolePosition='排班人员'")
	public String getksid(String empid);
	@Query(value="from WorkTime w where w.pbType=?1 and w.pbDate=?2")
	public List<WorkTime> getbydateandtype(String type,Date date);
	@Query(value="select w.empInformation from WktimeEmp w,UserRole u where w.empInformation=u.empInformation and u.role.department.ksId=?1 and w.workTime.pbDate=?2 and w.workTime.pbType=?3 and w.wktimeDuty=?4")
	public List<EmpInformation> getemps(String ksid,Date date,String type,String duty);
	@Query(value="select w from WktimeEmp w,UserRole u where w.empInformation=u.empInformation and u.role.department.ksId=?1 and w.workTime.pbDate=?2 and w.workTime.pbType=?3")
	public List<WktimeEmp> getwkemps(String ksid,Date date,String type);
	@Query(value="select w.workTime.pbId from WktimeEmp w,UserRole u where w.empInformation=u.empInformation and u.role.department.ksId=?1 and w.workTime.pbDate=?2 and w.workTime.pbType=?3")
	public String getpbid(String ksid,Date date,String type);
	
	//通过员工序号 找到该员工的排班时间
	@Query("select we.workTime from WktimeEmp we where we.empInformation.ygxh=?1")
	public WorkTime findpbid(String ygxh);
}
