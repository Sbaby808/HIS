package com.his.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.Department;

public interface IDepartmentDao extends CrudRepository<Department, String>{
	
	/**
	 * 
	* @Title:getAllDepartments
	* @Description:查询所有科室
	* @param:@return
	* @return:List<Department>
	* @throws
	* @author:Hamster
	* @Date:2019年8月2日 下午9:27:40
	 */
	@Query("from Department d")
	public List <Department> getAllDepartments();
	
	/**
	 * 
	* @Title:getHosDepartments
	* @Description:查询住院部门的所有科室
	* @param:@return
	* @return:List<Department>
	* @throws
	* @author:Hamster
	* @Date:2019年8月2日 下午9:27:01
	 */
	@Query(value="select * from department d where d.dept_id = 'inpatient'",nativeQuery=true)
	public List <Department> getHosDepartments();
	
	/**
	* @Title:getKSByOut
	* @Description:根据部门查询科室
	* @param:@param id
	* @param:@return
	* @return:List<Department>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月9日 下午4:31:14
	 */
	@Query("from Department d where d.dept.deptId = ?1")
	public List<Department> getKSByOut(String id);
	
	/**
	 * 
	* @Title:getDepartmentByDid
	* @Description:根据id查询科室信息
	* @param:@param departId
	* @param:@return
	* @return:Department
	* @throws
	* @author:Hamster
	* @Date:2019年8月2日 下午9:26:41
	 */
	@Query("from Department d where d.ksId = ?1")
	public Department getDepartmentByDid(String departId);
	
}
