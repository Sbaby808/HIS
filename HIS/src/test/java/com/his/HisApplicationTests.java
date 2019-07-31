package com.his;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.his.pojo.EmpInformation;
import com.his.service.EmpInformationService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HisApplicationTests {

	@Autowired
	private EmpInformationService empInformationService;
	
	@Test
	public void contextLoads() {
	}
	
	/**
	 * 
	* @Title:addTestEmp
	* @Description:添加测试员工信息
	* @param:
	* @return:void
	* @throws
	* @author:Sbaby
	* @Date:2019年7月31日 下午2:03:37
	 */
	@Test
	public void addTestEmp() {
		EmpInformation admin = new EmpInformation();
		admin.setYgxh("ADMINTST");
		admin.setYgName("王富贵");
		admin.setYgSex("男");
		admin.setYgPassword("admin123");
		empInformationService.addTestEmp(admin);
	}

}
