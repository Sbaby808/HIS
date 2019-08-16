package com.his.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.DepartmentDAO;
import com.his.dao.DeptDAO;
import com.his.dao.IDepartmentDao;
import com.his.pojo.Department;
import com.his.pojo.Dept;

@Service
@Transactional(rollbackFor=Exception.class)
public class DepartmentService {

	@Autowired
	private DepartmentDAO departmentDAO;
	@Autowired
	private DeptDAO deptDAO;
	
	public List<Department> finDepartments(String ksname,int currentpage){
		PageRequest pageRequest = PageRequest.of(currentpage-1, 5);
		ksname="%"+ksname+"%";
		return departmentDAO.findDepartments(ksname, pageRequest);
	}
	//添加或者修改的时候
	public void addorupdateDepartment(Department department) {
		    Dept dept = new Dept();
		   String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		   String uuid1 = UUID.randomUUID().toString().replaceAll("-", "");
		   dept.setDeptId(uuid1);
		    dept.setDeptName("good");
		    deptDAO.save(dept);
		    department.setDept(dept);
		   department.setKsId(uuid);
		   department.setSetTime(new Date());
		/*
		 * List<Department> a= dept.getDepartments(); dept.setDepartments(departments);
		 */
		departmentDAO.save(department);
	}
	public void deleteDepartment(String ksid) {
		departmentDAO.deleteById(ksid);
	}
}
