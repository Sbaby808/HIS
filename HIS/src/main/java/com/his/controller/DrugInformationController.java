package com.his.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.DrugInformation;
import com.his.pojo.JsonResult;
import com.his.service.DrugInformationService;

/**  
* @ClassName: DrugInformationController  
* @Description: 药品信息控制器
* @author Sbaby
* @date 2019年8月7日  上午9:13:20
*    
*/
@Controller
public class DrugInformationController {

	@Autowired
	private DrugInformationService drugInformationService;
	
	/**
	* @Title:getNoPrice
	* @Description:查询未划价的药品
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月7日 上午9:46:08
	 */
	@GetMapping("/get_no_price")
	@ResponseBody
	public JsonResult getNoPrice() {
		JsonResult result = new JsonResult();
		try {
			result.setResult(drugInformationService.getNoPrice());
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:getById
	* @Description:根据id查询药品信息
	* @param:@param id
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月7日 上午10:01:21
	 */
	@GetMapping("/get_drug_information_by_id")
	@ResponseBody
	public JsonResult getById(String id) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(drugInformationService.getById(id));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(id);
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	 * 
	* @Title:getAllDrugInformation
	* @Description:查询所有药品信息
	* @param:@return
	* @return:List<DrugInformation>
	* @throws
	* @author:Hamster
	* @Date:2019年8月7日 下午9:20:04
	 */
	@ResponseBody
	@GetMapping("/get_all_drug_information")
	public List <DrugInformation> getAllDrugInformation(){
		return drugInformationService.getAllDrugInformation();
	}
	
}
