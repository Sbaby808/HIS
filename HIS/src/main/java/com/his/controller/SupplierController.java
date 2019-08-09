package com.his.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.JsonResult;
import com.his.pojo.Supplier;
import com.his.service.SupplierService;
import com.his.utils.Result;

/**  
* @ClassName: SupplierController  
* @Description: 供应商Controller
* @author crazy_long
* @date 2019年8月6日  下午10:43:39
*    
*/
@Controller
public class SupplierController {
	
	@Autowired
	private SupplierService supplierservice;
	
	@ResponseBody
	@PostMapping("add_supplier")
	public Result add_supplier(@RequestBody Supplier supplier) {
		try {
			supplierservice.addSupplier(supplier);
			return new Result();
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("添加失败");
		}
	}
	
	@ResponseBody
	@PostMapping("updata_supplier")
	public Result updata_supplier(@RequestBody Supplier editsupplier) {
		try {
			supplierservice.updataSupplier(editsupplier);
			return new Result();
		} catch (Exception e) {
			return new Result("供应商修改失败");
		}
	}
	
	@ResponseBody
	@GetMapping("get_allsuplier_by_page")
	public Map get_allsuplier_by_page(int curpage,int pageSize){
		return supplierservice.getSupplierByPage(curpage, pageSize);
	}
	
	/**
	* @Title:getAllGYS
	* @Description:查询所有供应商
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月7日 下午3:47:11
	 */
	@GetMapping("/get_all_gys")
	@ResponseBody
	public JsonResult getAllGYS() {
		JsonResult result = new JsonResult();
		try {
			result.setResult(supplierservice.getAll());
			result.setStatus("ok");
		} catch (Exception e) {
			result.setStatus("error");
		}
		return result;
	}
}
