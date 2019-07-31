package com.his;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.his.pojo.EmpInformation;
import com.his.service.EmpInformationService;
import com.his.utils.MD5Tools;

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
		admin.setYgPassword(MD5Tools.KL(MD5Tools.Md5("admin123")));
		empInformationService.addTestEmp(admin);
	}
	
	/**
	 * 
	* @Title:testMD5Tools
	* @Description:测试MD5加密工具
	* @param:
	* @return:void
	* @throws
	* @author:Sbaby
	* @Date:2019年7月31日 下午2:24:56
	 */
	@Test
	public void testMD5Tools() {
		String passwd = new String("admin123");
		String md5pwd = MD5Tools.Md5(passwd);
		if(!"".equals(md5pwd)) {
			System.out.println("md5:\t" + md5pwd);
			String md5spwd = MD5Tools.KL(md5pwd);
			System.out.println("md5s:\t" + md5spwd);
			String md5dpwd = MD5Tools.JM(md5spwd);
			System.out.println("md5d:\t" + md5dpwd);
		}
	}

}
