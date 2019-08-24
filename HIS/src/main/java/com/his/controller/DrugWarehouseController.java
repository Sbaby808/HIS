package com.his.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.JsonResult;
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
