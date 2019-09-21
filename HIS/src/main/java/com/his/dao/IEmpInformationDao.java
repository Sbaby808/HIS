package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.Dept;
import com.his.pojo.EmpInformation;

/**  
* @ClassName: IEmpInformationDao  
* @Description: 员工信息dao
* @author crazy_long
* @date 2019年7月30日  上午9:52:00
*    
*/
public interface IEmpInformationDao extends CrudRepository<EmpInformation, String>{
	
	/**
	* @Title:getDetpByYgxh
	* @Description：根据员工序号查找dept
	* @param:@param ygxh
	* @param:@return
	* @return:List<Dept>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月5日 下午2:37:10
	 */
	@Query(value="select d.*,r.role_position from emp_information e left outer join user_role ur on e.ygxh = ur.ygxh " + 
			"left outer join role r on ur.role_id = r.role_id " + 
			"left outer join department dm on r.ks_id = dm.ks_id " + 
			"left outer join dept d on d.dept_id = dm.dept_id " + 
			"where e.ygxh = ?1 ",nativeQuery = true)
	public List<Object[]> getDetpByYgxh(String ygxh);
	
	public EmpInformation findEmpInformationByYgGh(String yggh);

	@Query("from EmpInformation e left outer join e.technicalPost t")
	public List<EmpInformation> queryByPage(Pageable page);
			
	public List<EmpInformation> findByygGh(String ygGh);
	
	@Query("from EmpInformation e where e.ygName = ?1")
	public List<EmpInformation> qureyByygName(String ygName);
	
	@Query("select e.ygxh,e.ygName from EmpInformation e")
	public List<Object[]> queryAllForNameAndXH();
	
	/**
	* @Title:getDocByKsAndTp
	* @Description:根据日期、科室与职称查询门诊医生
	* @param:@return
	* @return:List<EmpInformation>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月10日 上午8:52:15
	 */
//	@Query(value = "select e.* from (((technical_post t left outer join emp_information e on t.tp_id = e.tp_id )" 
//     + " left outer join user_role ur on e.ygxh = ur.ygxh) "
//     + " left outer join role r on ur.role_id = r.role_id) "
//     + " left outer join department d on r.ks_id = d.ks_id "
//     + " where d.dept_id = 'outpatient' "
//     + " and t.tp_id = ?1 "
//     + " and r.ks_id = ?2", nativeQuery = true)
	@Query(value = "select e.* from (((((work_time wt left outer join wktime_emp wke on wt.pb_id = wke.pb_id) "
		       + " left outer join emp_information e on wke.ygxh = e.ygxh) "
		       + " left outer join user_role ur on ur.ygxh = e.ygxh) "
		       + " left outer join role ro on ro.role_id = ur.role_id) "
		       + " left outer join technical_post t on e.tp_id = t.tp_id) "
		       + " where to_char(wt.pb_date, 'yyyy-mm-dd') = TO_CHAR(?3 / (1000 * 60 * 60 * 24) +TO_DATE('1970-01-01', 'YYYY-MM-DD'), 'YYYY-MM-DD') "
		       + " and ro.ks_id = ?2 "
		       + " and t.tp_id = ?1", nativeQuery = true)
	public List<EmpInformation> getDocByKsAndTp(String tp, String ks, Long doDate);
	
	/**
	* @Title:getDoctorsByWkAndKs
	* @Description:根据科室查询当日排班的医生
	* @param:@param ksId
	* @param:@return
	* @return:List<EmpInformation>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月10日 下午4:04:54
	 */
	@Query(value = "select e.* from ((((work_time wt left outer join wktime_emp wke on wt.pb_id = wke.pb_id) "
       + " left outer join emp_information e on wke.ygxh = e.ygxh) "
       + " left outer join user_role ur on ur.ygxh = e.ygxh) "
       + " left outer join role ro on ro.role_id = ur.role_id) "
       + " where to_char(wt.pb_date, 'yyyy-mm-dd') = to_char(sysdate, 'yyyy-mm-dd') "
       + " and ro.ks_id = ?1", nativeQuery = true)
	public List<EmpInformation> getDoctorsByWkAndKs(String ksId);
	/**
	 * 
	* @Title:getsijibyempid
	* @Description:查询所有的司机
	* @param:@return
	* @return:List<EmpInformation>
	* @throws
	* @author:jack
	* @Date:2019年8月26日 上午9:54:45
	 */
	@Query("select ur.empInformation from UserRole ur where ur.role.rolePosition = '司机' ")
	public List<EmpInformation> getsijibyempid();
	
	/**
	* @Title:getEmpByGh
	* @Description:根据工号查询员工
	* @param:@param ygGh
	* @param:@return
	* @return:List<EmpInformation>
	* @throws
	* @author:Sbaby
	* @Date:2019年9月21日 下午5:04:07
	 */
	@Query("from EmpInformation e where e.ygGh = ?1")
	public List<EmpInformation> getEmpByGh(String ygGh);
}
