package com.his.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.JsonResult;
import com.his.pojo.PurchaseDetail;
import com.his.service.PurchaseDetailsService;

/**  
* @ClassName: PurchaseDetailController  
* @Description: 采购明细controller 
* @author crazy_long
* @date 2019年8月14日  下午3:52:28
*    
*/
@Controller
public class PurchaseDetailController {
	
	@Autowired
	private PurchaseDetailsService purchaseDetailsService;
	
	/**
	* @Title:append_purchasedetail
	* @Description:往一个已经存在的采购计划中添加明细
	* @param:@param purchaseDetail
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年8月14日 下午10:17:46
	 */
	@ResponseBody
	@PostMapping("append_purchasedetail")
	public JsonResult append_purchasedetail(@RequestBody List<PurchaseDetail> purchaseDetail) {
		JsonResult jsonResult = new JsonResult();
		try {
			purchaseDetailsService.appendToDetail(purchaseDetail);
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}
	
	/**
	* @Title:get_purchase_by_cgid
	* @Description:根据采购id查询明细
	* @param:@param cgid
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年8月14日 下午3:59:59
	 */
	@ResponseBody
	@GetMapping("get_purchase_by_cgid")
	public JsonResult get_purchase_by_cgid(String cgid) {
		JsonResult jsonResult = new JsonResult();
		try {
			jsonResult.setResult(purchaseDetailsService.getAllPurCheDetailsByCgid(cgid));
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}
	
	/**
	* @Title:get_Detail_by_cgidAndPage
	* @Description:根据采购分页id查询明细
	* @param:@param curPage
	* @param:@param pageSize
	* @param:@param cgid
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年8月15日 下午5:45:55
	 */
	@ResponseBody
	@GetMapping("get_Detail_by_cgidAndPage")
	public JsonResult get_Detail_by_cgidAndPage(int curPage,int pageSize,String cgid) {
		JsonResult jsonResult = new JsonResult();
		try {
			jsonResult.setResult(purchaseDetailsService.getDetailByCgidForPage(curPage, pageSize, cgid));
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}
	
	/**
	* @Title:get_purchase_by_cgid
	* @Description:删除一个采购明细
	* @param:@param cgid
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年8月14日 下午5:01:30
	 */
	@ResponseBody
	@GetMapping("del_one_purchasedetail")
	public JsonResult del_one_purchasedetail(String ypId,String cgId) {
		JsonResult jsonResult = new JsonResult();
		try {
			purchaseDetailsService.delOneDetail(ypId, cgId);
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}
	
	/**
	* @Title:updata_one_purchasedetail_number
	* @Description:修改某一个采购明细的数量
	* @param:@param ypId
	* @param:@param cgId
	* @param:@param purchaseNum
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年8月14日 下午5:54:57
	 */
	@ResponseBody
	@GetMapping("updata_one_purchasedetail_number")
	public JsonResult updata_one_purchasedetail_number(String ypId,String cgId,int purchaseNum) {
		JsonResult jsonResult = new JsonResult();
		try {
			purchaseDetailsService.updateOneDetailNumber(ypId, cgId, purchaseNum);;
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}

}
