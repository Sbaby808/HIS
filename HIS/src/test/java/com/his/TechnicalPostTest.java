package com.his;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.his.pojo.TechnicalPost;
import com.his.service.TechnicalPosService;

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
	
	@Autowired
	private TechnicalPosService technicalPosService;
	
	@Test
	public void query() {
		List<TechnicalPost> list = technicalPosService.queryAllTechnicalPost();
		for (TechnicalPost technicalPost : list) {
			System.out.println(technicalPost.getTpName());
		}
	}
	
	@Test
	public void addTechnicalPost() {
		for(int i=0;i<5;i++) {
			 TechnicalPost tp = new TechnicalPost();
			 tp.setTpName("资深专家"+i);
			 technicalPosService.addTechnicalPost(tp);
		}
		
	}
	
}
