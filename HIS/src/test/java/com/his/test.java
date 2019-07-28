package com.his;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.his.pojo.Dept;
import com.his.service.DeptService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class test {
	@Autowired
	private DeptService deptService;
	
	@Test
	public void testAll(){
		List <Dept> depts = deptService.getAll();
		for(Dept dept:depts){
			System.out.println(dept.getDeptId()+"\t"+dept.getDeptName());
		}
	}
}
