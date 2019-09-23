package com.his.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	/**
	* @Title:update_subclass_name
	* @Description:修改中类名称
	* @param:@param subclassId
	* @param:@param name
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月18日 下午2:29:15
	 */
	@GetMapping("/update_minorDefect_name")
	@ResponseBody
	public JsonResult update_minorDefect_name(String minorDefectsId,String name) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(drugMinorDefectsService.updateminorDefectName(minorDefectsId, name));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:add_DrugMinorDefect
	* @Description:添加一个中类
	* @param:@param drugMinorDefect
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月18日 下午2:29:00
	 */
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
	
	/**
	* @Title:get_all_drugMinorDefect
	* @Description:获取所有的中类
	* @param:@return
	* @return:List<DrugMinorDefect>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月18日 下午2:28:44
	 */
	@ResponseBody
	@GetMapping("get_all_drugMinorDefect")
	public List<DrugMinorDefect> get_all_drugMinorDefect(){
		return drugMinorDefectsService.queryAllDrugMinorDefect();
	}

}
