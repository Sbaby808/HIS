package com.his;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.his.pojo.TechnicalPost;
import com.his.service.TechnicalPosService;
import com.his.utils.CreateUUID;

/**  
* @ClassName: TechnicalPostTest  
* @Description: 职称 test
* @author crazy_long
* @date 2019年8月3日  上午12:43:47
*    
*/
@RunWith(SpringRunner.class)
@SpringBootTest
public class TechnicalPostTest {
	
	private TechnicalPosService technicalPosService;
	
	@Test
	public void addTechnicalPost() {
		/* TechnicalPost tp = new TechnicalPost();
		 tp.setTpName("医生");
		 technicalPosService.addTechnicalPost(tp);*/
		System.out.println(technicalPosService.queryAllTechnicalPost().size());
	}

}
