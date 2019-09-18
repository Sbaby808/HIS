package com.his.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.JsonResult;
import com.his.pojo.Medicine;
import com.his.pojo.PutStockInformation;
import com.his.service.DrugWarehouseService;

/**  
* @ClassName: DrugWarehouseController  
* @Description: 药库批次库存Controller
* @author crazy_long
* @date 2019年8月17日  下午2:56:21
*    
*/
@Controller
public class DrugWarehouseController {
	
	@Autowired
	private DrugWarehouseService drugWarehouseService;
	
	/**
	* @Title:query_drugWarehouse_by_choosenumber
	* @Description:查找特定范围的药品
	* @param:@param chooseNumber
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月4日 下午3:49:04
	 */
	@ResponseBody
	@GetMapping("query_drugWarehouse_by_choosenumber")
	public JsonResult query_drugWarehouse_by_choosenumber(int chooseNumber) {
		JsonResult jsonResult = new JsonResult();
		try {
			jsonResult.setResult(drugWarehouseService.queryWarehouseByChooseNumber(chooseNumber));
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}
	
	/**
	* @Title:update_nownumber_by_id
	* @Description:根据id修改库存
	* @param:@param pckcId
	* @param:@param updateNumber
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月3日 下午9:20:41
	 */
	@ResponseBody
	@GetMapping("update_nownumber_by_id")
	public JsonResult update_nownumber_by_id(String pckcId,int updateNumber) {
		JsonResult result = new JsonResult();
		try {
			drugWarehouseService.updateNowNumberById(pckcId, updateNumber);
			result.setStatus("ok");
		} catch (Exception e) {
			result.setStatus("error");
		}
		return 	result;
	}
	
	/**
	* @Title:get_all_Warehouse
	* @Description:查询某个药品的总批次信息和总库存
	* @param:@param ypId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月1日 下午10:57:56
	 */
	@ResponseBody
	@GetMapping("get_all_Warehouse_and_totalCount")
	public JsonResult get_all_Warehouse(String ypId) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(drugWarehouseService.getAllWarehouseAndTotalCount(ypId));
			result.setStatus("ok");
		} catch (Exception e) {
			result.setStatus("error");
		}
		return 	result;
	}
		
	/**
	* @Title:search_all_warehouse_by_page
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
	* @Date:2019年8月26日 上午11:41:39
	 */
	@ResponseBody
	@GetMapping("search_all_warehouse_by_page")
	public JsonResult search_all_warehouse_by_page(String searchKey, String searchType, String searchSubclass, String searchGys, String searchMinorDefect,
			BigDecimal minPrice, BigDecimal maxPrice, int pageNum, int pageSize) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(drugWarehouseService.searchAllInformationByPage(searchKey, searchType, searchSubclass, searchGys, searchMinorDefect, minPrice, maxPrice, pageNum, pageSize));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:get_drug_for_back
	* @Description:根据药品名和供应商id查找要退还的药品
	* @param:@param ypName
	* @param:@param supplierId
	* @param:@param curPage
	* @param:@param pageSize
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年8月24日 上午10:00:10
	 */
	@ResponseBody
	@GetMapping("get_drug_for_back")
	public JsonResult get_drug_for_back(String ypNameOrVocode,String supplierId,int curPage,int pageSize) {
		JsonResult jsonResult = new JsonResult();
		try {
			jsonResult.setResult(drugWarehouseService.queryDrugForback(ypNameOrVocode, supplierId, curPage, pageSize));
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}
	
	/**
	* @Title:get_drug_to_baofei
	* @Description:查找要处理的报废药品
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年8月22日 下午2:30:45
	 */
	@ResponseBody
	@GetMapping("get_drug_to_baofei_by_page")
	public JsonResult get_drug_to_baofei_by_page(String ypNameOrvocode,long prodeceDate,int curPage,int pageSize) {
		JsonResult jsonResult = new JsonResult();
		try {
			jsonResult.setResult(drugWarehouseService.queryDrugforScrapByPage(ypNameOrvocode, prodeceDate, curPage, pageSize));;
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}
	
	/**
	* @Title:get_all_overdue_drug
	* @Description:查找所有过期的药品
	* @param:@param curPage
	* @param:@param pageSize
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年8月20日 下午4:27:13
	 */
	@ResponseBody
	@GetMapping("get_all_overdue_drug")
	public JsonResult get_all_overdue_drug() {
		JsonResult jsonResult = new JsonResult();
		try {
			jsonResult.setResult(drugWarehouseService.getAllOverdueDrug());
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}
	
	/**
	* @Title:get_all_overdue_drug
	* @Description:分页查找所有过期的药品
	* @param:@param curPage
	* @param:@param pageSize
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年8月20日 下午4:27:13
	 */
	@ResponseBody
	@GetMapping("get_all_overdue_drug_byPage")
	public JsonResult get_all_overdue_drug_byPage(int curPage,int pageSize) {
		JsonResult jsonResult = new JsonResult();
		try {
			jsonResult.setResult(drugWarehouseService.getAllOverdueDrugByPage(curPage, pageSize));
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}

	/**
	* @Title:drug_put_Warehouse
	* @Description:药品一键入库
	* @param:@param psinfo
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年8月17日 下午5:35:46
	 */
	@ResponseBody
	@PostMapping("drug_put_Warehouse")
	public JsonResult drug_put_Warehouse(@RequestBody List<PutStockInformation> psinfo) {
		JsonResult jsonResult = new JsonResult();
		try {
			drugWarehouseService.DrugPutStockBybatch(psinfo);
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}
}
