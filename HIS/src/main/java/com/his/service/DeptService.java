package com.his.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.DeptDAO;
import com.his.pojo.Dept;

/**  
* @ClassName: DeptService  
* @Description:部门表增删该查  
* @author jack
* @date 2019年8月13日  上午9:40:49
*    
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class DeptService {
	@Autowired
	private DeptDAO deptDAO;
//增加部门
	public void addDept(Dept dept) {
		 String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		 dept.setDeptId(uuid);
		deptDAO.save(dept);
	}
	//删除部门
	public void deleteDept(String id) {
		deptDAO.deleteById(id);
	}
	//修改部门
	public void updateDept(Dept dept) {
		deptDAO.save(dept);
	}
	//查询部门
	public List<Dept> findDept() {
		return (List<Dept>) deptDAO.findAll();
	}
	//分页查询部门
	public List<Dept> findDepts(int currentpage){
		PageRequest pageRequest = PageRequest.of(currentpage-1, 5);
		return deptDAO.find(pageRequest);
	}
	//查询数据库有多少数据
	public int findcount() {
		return (int) deptDAO.count();
	}
	//根据名字查询数据库有多少数据
	public long findcounts(String name) {
		return  deptDAO.countname(name);
	}
	//根据id查询部门
	public Dept finDept(String id) {
		return deptDAO.findById(id).get();
	}
	//根据名字模糊查询
	public List<Dept> findDeptss(String name,int currentpage){
		PageRequest pageRequest = PageRequest.of(currentpage-1, 5);
		name="%"+name+"%";
		return deptDAO.findDepts(name,pageRequest);
	}
	//根据部门名字模糊查询有多少
	public long findcountss(String name) {
		name="%"+name+"%";
		return deptDAO.countnames(name);
	}
}
