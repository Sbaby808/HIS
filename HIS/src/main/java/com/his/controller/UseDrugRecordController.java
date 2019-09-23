package com.his.controller;

import java.math.BigDecimal;

import org.apache.tomcat.util.buf.UEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.JsonResult;
import com.his.pojo.UseDrugRecord;
import com.his.service.UseDrugRecordService;

/**  
* @ClassName: UseDrugRecordController  
* @Description: 门诊用药Controller
* @author Sbaby
* @date 2019年9月3日  下午4:48:55
*    
*/
@Controller
public class UseDrugRecordController {

	@Autowired
	private UseDrugRecordService useDrugRecordService;
	
	/**
	* @Title:finish_use_drug_record
	* @Description:根据就诊卡id查找未取药的处方单
	* @param:@param injId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月23日 上午2:01:28
	 */
	@GetMapping("/qurey_priscription_by_CardId")
	@ResponseBody
	public JsonResult qurey_priscription_by_CardId(String cardId) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(useDrugRecordService.qureyPriscriptionByCardId(cardId));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:qurey_priscription_notNull_ByCardId
	* @Description:根据就诊卡id查询"已取药"的处方单
	* @param:@param cardId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月23日 上午3:10:49
	 */
	@GetMapping("/qurey_priscription_notNull_ByCardId")
	@ResponseBody
	public JsonResult qurey_priscription_notNull_ByCardId(String cardId) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(useDrugRecordService.qureyPriscriptionNotNullByCardId(cardId));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:qurey_all_priscription
	* @Description:查找处方单测试数据
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月23日 上午2:32:46
	 */
	@GetMapping("/qurey_all_priscription")
	@ResponseBody
	public JsonResult qurey_all_priscription() {
		JsonResult result = new JsonResult();
		try {
			result.setResult(useDrugRecordService.getAllPriscriptionToTest());
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:finish_use_drug_record
	* @Description:完成用药明细记录
	* @param:@param injId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月10日 上午9:31:49
	 */
	@GetMapping("/finish_use_drug_record")
	@ResponseBody
	public JsonResult finish_use_drug_record(String injId) {
		JsonResult result = new JsonResult();
		try {
			useDrugRecordService.finishUseDrugRecord(injId);
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:add_a_useDrugRecord
	* @Description:添加一个用药记录
	* @param:@param useDrugRecord
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月9日 上午11:06:31
	 */
	@PostMapping("/add_a_useDrugRecord")
	@ResponseBody
	public JsonResult add_a_useDrugRecord(@RequestBody UseDrugRecord useDrugRecord) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(useDrugRecordService.addAnUseDrugRecord(useDrugRecord));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:initUseDrugRecord
	* @Description:添加门诊处方用药记录
	* @param:@param preId
	* @param:@param empId
	* @param:@param ksId
	* @param:@param ypId
	* @param:@param ypNum
	* @param:@param ypUnit
	* @param:@param way
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年9月3日 下午5:14:25
	 */
	@GetMapping("/init_use_drug_record")
	@ResponseBody
	public JsonResult initUseDrugRecord(String preId, String empId, String ypId, BigDecimal ypNum, String ypUnit, String way) {
		JsonResult result = new JsonResult();
		try {
			useDrugRecordService.addUseDrug(preId, empId, ypId, ypNum, ypUnit, way);
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:checkMedicineNum
	* @Description:检查门诊药房库存是否足够
	* @param:@param ypId
	* @param:@param ypNum
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年9月3日 下午9:36:34
	 */
	@GetMapping("/check_medicine_num")
	@ResponseBody
	public JsonResult checkMedicineNum(String ypId, BigDecimal ypNum) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(useDrugRecordService.checkMedicineNum(ypId, ypNum));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
}
