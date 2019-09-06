package com.his.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.AskleaveRecord;

/**  
* @ClassName: IAskleaveRecordDao  
* @Description: 请假记录dao 
* @author crazy_long
* @date 2019年7月30日  上午9:43:12
*    
*/
public interface IAskleaveRecordDao extends CrudRepository<AskleaveRecord, String>{
	/**
	 * 
	* @Title:getwei
	* @Description:TODO根据id获得该员工未处理的请假申请
	* @param:@param ygxh
	* @param:@return
	* @return:List<AskleaveRecord>
	* @throws
	* @author:TRC
	* @Date:2019年9月4日 上午9:22:27
	 */
	@Query(value="select a from AskleaveRecord a where a.empInformation.ygxh=?1 and a.status='未处理' order by a.askLeaveTime desc")
	public List<AskleaveRecord> getwei(String ygxh);
	/**
	 * 
	* @Title:getyi
	* @Description:TODO根据id获得该员工已处理的请假申请
	* @param:@param ygxh
	* @param:@return
	* @return:List<AskleaveRecord>
	* @throws
	* @author:TRC
	* @Date:2019年9月4日 上午9:23:15
	 */
	@Query(value="select a from AskleaveRecord a where a.empInformation.ygxh=?1 and a.status='已处理' order by a.askLeaveTime desc")
	public List<AskleaveRecord> getyi(String ygxh);
	/**
	 * 
	* @Title:getksask
	* @Description:TODO获得该排班人员科室的请假记录
	* @param:@param ksid
	* @param:@return
	* @return:List<AskleaveRecord>
	* @throws
	* @author:TRC
	* @Date:2019年9月4日 下午9:53:45
	 */
	@Query(value="select distinct a from AskleaveRecord a,UserRole u where a.empInformation.ygxh=u.empInformation.ygxh and u.role.department.ksId=?1 and a.status='未处理' order by a.askLeaveTime desc")
	public List<AskleaveRecord> getksask(String ksid);
	@Query(value="select distinct a from AskleaveRecord a,UserRole u where a.empInformation.ygxh=u.empInformation.ygxh and u.role.department.ksId=?1 and a.status='已处理' order by a.askLeaveTime desc")
	public List<AskleaveRecord> getdealask(String ksid,Pageable page);
	@Query(value="select distinct a from AskleaveRecord a,UserRole u where a.empInformation.ygxh=u.empInformation.ygxh and u.role.department.ksId=?1 and a.empInformation.ygName like ?2  and a.status='已处理' order by a.askLeaveTime desc")
	public List<AskleaveRecord> getdealaskbyname(String ksid,String name,Pageable page);
	@Query(value="select distinct a from AskleaveRecord a,UserRole u where a.empInformation.ygxh=u.empInformation.ygxh and u.role.department.ksId=?1 and a.status='已处理' and a.askLeaveTime between ?2 and ?3 order by a.askLeaveTime desc")
	public List<AskleaveRecord> getdealaskbytime(String ksid,Date start,Date end,Pageable page);
	@Query(value="select distinct a from AskleaveRecord a,UserRole u where a.empInformation.ygxh=u.empInformation.ygxh and u.role.department.ksId=?1 and a.status='已处理' and a.empInformation.ygName like ?2 and a.askLeaveTime between ?3 and ?4 order by a.askLeaveTime desc")
	public List<AskleaveRecord> getdealaskbytimeandname(String ksid,String name,Date start,Date end,Pageable page);
	@Query(value="select distinct a from AskleaveRecord a,UserRole u where a.empInformation.ygxh=u.empInformation.ygxh and u.role.department.ksId=?1 and a.status='已处理'")
	public List<AskleaveRecord> getdealaskcount(String ksid);
	@Query(value="select distinct a from AskleaveRecord a,UserRole u where a.empInformation.ygxh=u.empInformation.ygxh and u.role.department.ksId=?1 and a.empInformation.ygName like ?2  and a.status='已处理'")
	public List<AskleaveRecord> getdealaskbynamecount(String ksid,String name);
	@Query(value="select distinct a from AskleaveRecord a,UserRole u where a.empInformation.ygxh=u.empInformation.ygxh and u.role.department.ksId=?1 and a.status='已处理' and a.askLeaveTime between ?2 and ?3")
	public List<AskleaveRecord> getdealaskbytimecount(String ksid,Date start,Date end);
	@Query(value="select distinct a from AskleaveRecord a,UserRole u where a.empInformation.ygxh=u.empInformation.ygxh and u.role.department.ksId=?1 and a.status='已处理' and a.empInformation.ygName like ?2 and a.askLeaveTime between ?3 and ?4")
	public List<AskleaveRecord> getdealaskbytimeandnamecount(String ksid,String name,Date start,Date end);

}
