package com.his.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.Department;

public interface IDepartmentDao extends CrudRepository<Department, String>{
	
	/**
	* @Title:queryByYgxh
	* @Description:查找员工对应的科室
	* @param:@param ygxh
	* @param:@return
	* @return:List<Department>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月9日 上午10:49:55
	 */
	@Query(value="select d.ks_id,r.role_position from department d left outer join role r on d.ks_id = r.ks_id " + 
			"left outer join user_role ur on r.role_id = ur.role_id " + 
			"left outer join emp_information e on e.ygxh = ur.ygxh " + 
			"where e.ygxh = ?1 ",nativeQuery = true)
	public List<Object[]> queryByYgxh(String ygxh);
	
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
	@Query(value="select * from department d where d.dept_id = 'cccccccc'",nativeQuery=true)
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
	/**
	 * 
	* @Title:getDepartments
	* @Description:TODO根据部门id获得科室
	* @param:@param deptid
	* @param:@return
	* @return:List<Department>
	* @throws
	* @author:TRC
	* @Date:2019年9月13日 下午2:45:14
	 */
	@Query(value="select d from Department d where d.dept.deptId=?1")
	public List<Department> getDepartments(String deptid);
	
}
