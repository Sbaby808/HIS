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

	/**
	* @Title:getAllDrugSubclass
	* @Description:获取所有药品小类以及相应的中类
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月8日 下午5:08:42
	 */
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

	/**
	* @Title:drugSubclass_To_minor
	* @Description:小类转中类
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年8月10日 下午4:05:16
	 */
	@ResponseBody
	@PostMapping("drugSubclass_To_minor")
	public JsonResult drugSubclass_To_minor(@RequestBody DrugSubclass subTominor) {
		JsonResult result = new JsonResult();
		try {
			drugSubclassService.drugSubclassTominor(subTominor);
			result.setResult(subTominor);
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(subTominor);
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:get_all_drugSubclass
	* @Description:获取所有小类信息
	* @param:@return
	* @return:List<DrugSubclass>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月10日 下午4:02:56
	 */
	@ResponseBody
	@GetMapping("get_all_drugSubclass")
	public List<DrugSubclass> get_all_drugSubclass() {
		return drugSubclassService.queryAllDrugSubclass();
	}

	/**
	* @Title:add_DrugSubclass
	* @Description:添加一个小类
	* @param:@param drugSubclass
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年8月10日 下午4:03:27
	 */
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
