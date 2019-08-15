package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.EmpInformation;

/**  
* @ClassName: IEmpInformationDao  
* @Description: 员工信息dao
* @author crazy_long
* @date 2019年7月30日  上午9:52:00
*    
*/
public interface IEmpInformationDao extends CrudRepository<EmpInformation, String>{
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

}
