package com.his;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.his.pojo.TechnicalPost;
import com.his.pojo.WaitingRoom;
import com.his.service.TechnicalPosService;
import com.his.service.WaitingRoomService;
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
	
	@Autowired
	private TechnicalPosService technicalPosService;
	
	@Test
	public void addTechnicalPost() {
		for(int i=0;i<5;i++) {
			 TechnicalPost tp = new TechnicalPost();
			 tp.setTpName("资深专家"+i);
			 technicalPosService.addTechnicalPost(tp);
		}
		
	}
	
}
