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
	
	public List<Department> findallDepartments(){
		return (List<Department>) departmentDAO.findAll();
	}
	
	public List<Department> finDepartments(String ksname,int currentpage){
		PageRequest pageRequest = PageRequest.of(currentpage-1, 5);
		ksname="%"+ksname+"%";
		return departmentDAO.findDepartments(ksname, pageRequest);
	}
	//添加的时候
	public void addorupdateDepartment(Department department) {
		//根据部门名字查询部门
		Dept dept=deptDAO.finddept(department.getDept().getDeptName());
		   String uuid = UUID.randomUUID().toString().replaceAll("-", "");
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
	public long findcount(String ksname) {
		ksname="%"+ksname+"%";
		return departmentDAO.countnames(ksname);
	}
	
	public long countbyname(String name) {
		return departmentDAO.countbynames(name);
	}
	//修改ks
	public void updateks(Department department) {
		departmentDAO.save(department);
	}
}
