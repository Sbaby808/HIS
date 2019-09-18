package com.his;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.his.dao.ForChart;
import com.his.pojo.HospitalizedPatient;
import com.his.pojo.MedicalRecord;
import com.his.pojo.TechnicalPost;
import com.his.service.HosPatientsService;
import com.his.service.MedicalRecordService;
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
	@Autowired
	private HosPatientsService hosPatientsService;
	
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
	
	@Test
	public void test(){
		List object = hosPatientsService.countForCharts();
		List<ForChart> list = new ArrayList();
		for (Object row : object) {
			Object[] cells = (Object[]) row;
			ForChart result= new ForChart();
			result.setTime((String) cells[0]);
			result.setNum((BigDecimal) cells[1]);
			list.add(result);
		}
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i).getTime()+"\t"+list.get(i).getNum());
		}
		List<String> tableNames=list.stream().map(ForChart::getTime).collect(Collectors.toList());
        System.out.println("输出第一个："+tableNames);
        List<BigDecimal> orders=list.stream().map(ForChart::getNum).collect(Collectors.toList());
        System.out.println(orders);
	}
	
	
}
