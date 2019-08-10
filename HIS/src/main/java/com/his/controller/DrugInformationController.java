package com.his.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	* @Title:add_drugInformation_by_batch
	* @Description:批量添加药品信息
	* @param:@param drugInformation
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年8月8日 下午4:18:57
	 */
	@ResponseBody
	@PostMapping("add_drugInformation_by_batch")
	public JsonResult add_drugInformation_by_batch(@RequestBody List<DrugInformation> drugInformation) {
		JsonResult result = new JsonResult();
		try {
			drugInformationService.addDrugInformationSByBatch(drugInformation);
			result.setResult(drugInformation);
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:add_rugInformation
	* @Description:添加单个药品
	* @param:@param singleDrug
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年8月9日 下午3:30:47
	 */
	@ResponseBody
	@PostMapping("add_drugInformation")
	public JsonResult add_rugInformation(@RequestBody DrugInformation singleDrug) {
		System.out.println("-------------------------------");
		JsonResult result = new JsonResult();
		try {
			drugInformationService.addDrugInformation(singleDrug);
			result.setResult(singleDrug);
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:search_all_information_by_page
	* @Description:分页查询搜索条件
	* @param:@param searchKey
	* @param:@param searchType
	* @param:@param searchSubclass
	* @param:@param searchGys
	* @param:@param searchMinorDefect
	* @param:@param minPrice
	* @param:@param maxPrice
	* @param:@param pageNum
	* @param:@param pageSize
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年8月9日 下午11:27:29
	 */
	@ResponseBody
	@GetMapping("search_all_information_by_page")
	public JsonResult search_all_information_by_page(String searchKey, String searchType, String searchSubclass, String searchGys, String searchMinorDefect,
			BigDecimal minPrice, BigDecimal maxPrice, int pageNum, int pageSize) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(drugInformationService.searchAllInformationByPage(searchKey, searchType, searchSubclass, searchGys, searchMinorDefect, minPrice, maxPrice, pageNum, pageSize));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	
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
	
	@ResponseBody
	@GetMapping("get_all_drug_typeName")
	public List<String> get_all_drug_typeName(){
		return drugInformationService.getAllDrugInformationType();
	}
	
}
