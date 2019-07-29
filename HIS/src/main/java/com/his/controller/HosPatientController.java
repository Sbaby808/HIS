package com.his.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.HospitalizedPatient;
import com.his.service.HosPatientsService;

@Controller
public class HosPatientController {
	
	@Autowired
	private HosPatientsService hosPatientsService;
	
	@ResponseBody
	@GetMapping("get_hos_patients_byPage")
	public List<HospitalizedPatient> getHosPatientsByPage(){
		return hosPatientsService.getHosPatients();
	}
}
