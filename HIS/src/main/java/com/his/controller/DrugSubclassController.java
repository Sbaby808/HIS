package com.his.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.DrugSubclass;
import com.his.pojo.JsonResult;
import com.his.service.DrugSubclassService;

/**
 * @ClassName: DrugSubclassController
 * @Description: 药品小类Controller
 * @author crazy_long
 * @date 2019年8月8日 上午10:15:30
 * 
 */
@Controller
public class DrugSubclassController {

	@Autowired
	private DrugSubclassService drugSubclassService;

	@GetMapping("/get_all_subclass")
	@ResponseBody
	public JsonResult getAllDrugSubclass() {
		JsonResult result = new JsonResult();
		try {
			result.setResult(drugSubclassService.getAllSubclass());
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}

	@ResponseBody
	@GetMapping("get_all_drugSubclass")
	public List<DrugSubclass> get_all_drugSubclass() {
		return drugSubclassService.queryAllDrugSubclass();
	}

	@ResponseBody
	@PostMapping("add_DrugSubclass")
	public JsonResult add_DrugSubclass(@RequestBody DrugSubclass drugSubclass) {
		JsonResult jsonresult = new JsonResult();
		try {
			drugSubclassService.addDrugSubclass(drugSubclass);
			jsonresult.setResult(drugSubclass);
			jsonresult.setStatus("ok");
			return jsonresult;
		} catch (Exception e) {
			jsonresult.setResult(drugSubclass);
			jsonresult.setStatus("error");
			return jsonresult;
		}
	}
}
