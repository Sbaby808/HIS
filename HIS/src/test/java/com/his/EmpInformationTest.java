package com.his;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.his.dao.IEmpInformationDao;
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
	public void page() {
		List<Object[]> list = (List<Object[]>) empInformationService.queryEmpByPage(1, 10);
		for (Object[] ob : list) {
			for(int i=0;i<ob.length;i++) {
				System.out.println(ob[i]);
				System.out.println("-------------");
			}
		}
	}
	
	@Test
	public void queryByGH() {
		for (EmpInformation emp : empInformationService.queryByGH("1000001810811234")) {
			System.out.println(emp.getYgName()+";"+emp.getYgNation());
		}
	}
	
	@Test
	public void queryByName() {
		System.out.println("-----------------------");
		for (EmpInformation emp : empInformationService.queryEmpByYgname("张三5")) {
			System.out.println(emp.getYgName()+";"+emp.getYgNation());
		}
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
