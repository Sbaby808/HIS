package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.Dept;

/**  
* @ClassName: DeptDAO  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author jack
* @date 2019年8月13日  上午9:37:38
*    
*/
public interface DeptDAO extends CrudRepository<Dept, String>{
	@Query("from Dept t")
	public List<Dept> find(Pageable pageable);
	@Query("select count(*) from Dept t where t.deptName = ?1")
	public long countname(String deptName);
	@Query("from Dept t where t.deptName like ?1")
	public List<Dept> findDepts(String deptName,Pageable pageable);
	//根据部门名字模糊查询有多少
	@Query("select count(*) from Dept t where t.deptName like ?1")
	public long countnames(String deptName);

}
