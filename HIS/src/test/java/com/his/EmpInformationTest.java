package com.his;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.his.pojo.EmpInformation;
import com.his.pojo.UserRole;
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
	public void edittest() {
		empInformationService.edittest();
	}
	
	@Test
	public void page() {
		Map map = empInformationService.queryEmpByPage(1, 10);
		List<EmpInformation> list=(List<EmpInformation>) map.get("list");
		for (EmpInformation e : list) {
			System.out.println(e.getYgName());
			for (UserRole ur : e.getUserRoles()) {
				System.out.println(ur.getRole().getRolePosition());
			}
			
		}
	}
	
	@Test
	public void queryByGH() {
		
	}
	
	@Test
	public void queryByName() {
		
	}
	
	@Test
	public void addAllEmpInformation() {
		
		for(int i=0;i<20;i++) {
			EmpInformation e = new EmpInformation();
			e.setYgxh(CreateUUID.getUUID_32());
			//e.setWaitingRoomId(CreateUUID.getUUID_32());
			e.setYgName("张三"+i);
			e.setYgSex("男");
			e.setYgBirth(new Date());
			e.setYgNation("汉族");
			e.setYgEducation("本科");
			e.setYgTel("15589897056");
			e.setYgPlace("株洲"+i);
			e.setYgEmail("2221@qq.com");
			e.setYgPassword("123456");
			e.setYgGh(CreateUUID.getUUID_16());
			empInformationService.addTestEmp(e);;
		}
		
		
	}

}
