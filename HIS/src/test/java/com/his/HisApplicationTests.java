package com.his;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.his.pojo.EmpInformation;
import com.his.service.EmpInformationService;
import com.his.service.MedicalCardService;
import com.his.utils.MD5Tools;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HisApplicationTests {

	@Autowired
	private EmpInformationService empInformationService;
	@Autowired
	private MedicalCardService medicalCardService;
	
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
	
	/**
	* @Title:testCheckCardTimes
	* @Description:测试检查就诊卡
	* @param:
	* @return:void
	* @throws
	* @author:Sbaby
	* @Date:2019年8月2日 上午10:04:10
	 */
	@Test
	public void testCheckCardTimes() {
		String pid = "430922199808088518";
		System.out.println(medicalCardService.checkCardTimes(pid));
	}
	
	/**
	* @Title:testUUID
	* @Description:测试UUID
	* @param:
	* @return:void
	* @throws
	* @author:Sbaby
	* @Date:2019年8月2日 上午11:00:54
	 */
	@Test
	public void testUUID() {
		String id = UUID.randomUUID().toString();
		System.out.println(id);
	}

}
