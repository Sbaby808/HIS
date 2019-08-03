package com.his;

import java.util.Date;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.his.pojo.EmpInformation;
import com.his.service.EmpInformationService;
import com.his.utils.CreateUUID;

/**  
* @ClassName: EmpInformationTest  
* @Description: EmpInformation测试
* @author crazy_long
* @date 2019年8月2日  下午11:19:30
*    
*/

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EmpInformationTest {
	
	@Autowired
	private EmpInformationService empInformationService;
	
	@Test
	public void addAllEmpInformation() {
		
		EmpInformation e = new EmpInformation();
		e.setYgxh(CreateUUID.getUUID_32());
		//e.setWaitingRoomId(CreateUUID.getUUID_32());
		e.setYgName("张三1");
		e.setYgSex("男");
		e.setYgBirth(new Date());
		e.setYgNation("汉族1");
		e.setYgEducation("benke1");
		e.setYgTel("15574773727");
		e.setYgPlace("株洲1");
		e.setYgEmail("2221@qq.com");
		e.setYgPassword("123456");
		e.setYgGh(CreateUUID.getUUID_32());
		
		empInformationService.addEmpAllInformation(e);
	}

}
