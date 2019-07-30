package com.his;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.his.pojo.HospitalizedPatient;
import com.his.service.HosPatientsService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class test2 {
	
	@Autowired
	private HosPatientsService hosPatientsService;
	
	@Test
	public void testGetHosPatients(){
		List <HospitalizedPatient> list = hosPatientsService.getHosPatients();
		for(HospitalizedPatient h:list){
			System.out.println(h.getHospState()+"\t"+h.getHosSource());
		}
	}
}
