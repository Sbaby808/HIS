package com.his.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

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
	
	//通过员工序号 找到该员工的排班时间
	@Query("select we.workTime from WktimeEmp we where we.empInformation.ygxh=?1")
	public WorkTime findpbid(String ygxh);
}
