package com.his.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.Department;

public interface IDepartmentDao extends CrudRepository<Department, String>{
	
	@Query("from Department d")
	public List <Department> getAllDepartments();
	
	@Query(value="select * from department d where d.dept_id = 'cccccccc'",nativeQuery=true)
	public List <Department> getHosDepartments();
}
