package com.his.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.DrugMinorDefect;
import com.his.pojo.JsonResult;
import com.his.service.DrugMinorDefectsService;

/**  
* @ClassName: DrugMinorDefectController  
* @Description: 药品中类controller 
* @author crazy_long
* @date 2019年8月8日  上午9:00:29
*    
*/
@Controller
public class DrugMinorDefectController {
	
	@Autowired
	private  DrugMinorDefectsService drugMinorDefectsService;
	
	@ResponseBody
	@PostMapping("add_DrugMinorDefect")
	private JsonResult add_DrugMinorDefect(@RequestBody DrugMinorDefect drugMinorDefect) {
		try {
			System.out.println("--------------------"+drugMinorDefect.getMinorDefectsName());
			drugMinorDefectsService.addDrugMinorDefects(drugMinorDefect);
			JsonResult jsonresult = new JsonResult();
			jsonresult.setResult(drugMinorDefect);
			jsonresult.setStatus("ok");
			return jsonresult;
		} catch (Exception e) {
			JsonResult jsonresult = new JsonResult();
			jsonresult.setResult(drugMinorDefect);
			jsonresult.setStatus("error");
			return jsonresult;
		}
	}

}
