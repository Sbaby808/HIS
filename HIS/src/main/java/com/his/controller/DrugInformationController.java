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
	* @Title:updata_drugInformation
	* @Description:修改 药品信息
	* @param:@param editDrugInfo
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年8月10日 下午2:55:04
	 */
	@ResponseBody
	@PostMapping("updata_drugInformation")
	public JsonResult updata_drugInformation(@RequestBody DrugInformation editDrugInfo) {
		System.out.println("---------------------------------------");
		JsonResult jsonresult = new JsonResult();
		try {
			drugInformationService.updataDrugInformation(editDrugInfo);
			jsonresult.setResult(editDrugInfo);
			jsonresult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonresult.setResult(editDrugInfo);
			jsonresult.setStatus("error");
		}
		return jsonresult;
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
	* @Title:searchNoPriceDrugCount
	* @Description:模糊查询未划价药品信息数量
	* @param:@param searchKey
	* @param:@param searchType
	* @param:@param searchSubclass
	* @param:@param searchGys
	* @param:@param searchEmp
	* @param:@param minPrice
	* @param:@param maxPrice
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月9日 上午11:04:03
	 */
	@GetMapping("/search_no_price_drug_count")
	@ResponseBody
	public JsonResult searchNoPriceDrugCount(String searchKey, String searchType, String searchSubclass, String searchGys,
			BigDecimal minPrice, BigDecimal maxPrice) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(drugInformationService.searchNoPriceCount(searchKey, searchType, searchSubclass, searchGys, minPrice, maxPrice));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:searchPriceDrugCount
	* @Description:搜索已划价药品的数量
	* @param:@param searchKey
	* @param:@param searchType
	* @param:@param searchSubclass
	* @param:@param searchGys
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月21日 下午5:16:22
	 */
	@GetMapping("/search_price_drug_count")
	@ResponseBody
	public JsonResult searchPriceDrugCount(String searchKey, String searchType, String searchSubclass, String searchGys) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(drugInformationService.searchPriceCount(searchKey, searchType, searchSubclass, searchGys));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:searchNoPriceDrug
	* @Description:模糊查询未划价的药品信息
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月9日 上午11:50:41
	 */
	@GetMapping("/search_no_price_drug")
	@ResponseBody
	public JsonResult searchNoPriceDrug(String searchKey, String searchType, String searchSubclass, String searchGys,
			BigDecimal minPrice, BigDecimal maxPrice, int pageNum, int pageSize) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(drugInformationService.searchNoPrice(searchKey, searchType, searchSubclass, searchGys, minPrice, maxPrice, pageNum, pageSize));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:searchNoPriceDrug
	* @Description:分页搜索已划价的药品
	* @param:@param searchKey
	* @param:@param searchType
	* @param:@param searchSubclass
	* @param:@param searchGys
	* @param:@param pageNum
	* @param:@param pageSize
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月21日 下午5:18:01
	 */
	@GetMapping("/search_price_drug")
	@ResponseBody
	public JsonResult searchNoPriceDrug(String searchKey, String searchType, String searchSubclass, String searchGys,
			int pageNum, int pageSize) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(drugInformationService.searchPrice(searchKey, searchType, searchSubclass, searchGys, pageNum, pageSize));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:getNoPriceCount
	* @Description:查询未划价药品的总记录条数
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月10日 下午4:31:39
	 */
	@GetMapping("/get_no_price_count")
	@ResponseBody
	public JsonResult getNoPriceCount() {
		JsonResult result = new JsonResult();
		try {
			result.setResult(drugInformationService.getNoPriceCount());
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:getNoPrice
	* @Description:分页查询未划价的药品
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月7日 上午9:46:08
	 */
	@GetMapping("/get_no_price_drug_by_page")
	@ResponseBody
	public JsonResult getNoPrice(int pageNum, int pageSize) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(drugInformationService.getNoPrice(pageNum, pageSize));
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
	public List <DrugInformation> getAllDrugInformation(String ypName){
		String name = "%"+ypName+"%";
		return drugInformationService.getAllDrugInformation(name);
	}
	
	@ResponseBody
	@GetMapping("get_all_drug_typeName")
	public List<String> get_all_drug_typeName(){
		return drugInformationService.getAllDrugInformationType();
	}
	
	/**
	* @Title:get_alldrug_by_subclassid
	* @Description:根据小类id查找对应的所有药品信息
	* @param:@param subclassid
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年8月12日 下午2:22:32
	 */
	@ResponseBody
	@GetMapping("get_alldrug_by_subclassid")
	public JsonResult get_alldrug_by_subclassid(String subclassid) {
		JsonResult jsonresult = new JsonResult();
		try {
			jsonresult.setResult(drugInformationService.getAllDrugBySubclassId(subclassid));
			jsonresult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonresult.setResult(subclassid);
			jsonresult.setStatus("error");
		}
		return jsonresult;
	}

	
}
