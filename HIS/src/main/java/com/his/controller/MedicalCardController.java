package com.his.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.JsonResult;
import com.his.pojo.MedicalCard;
import com.his.service.MedicalCardService;


/**
 * 
* @ClassName: MedicalCardController  
* @Description: 就诊卡 
* @author Hamster
* @date 2019年8月1日  下午8:39:53
*
 */
@Controller
public class MedicalCardController {
	
	private static Logger log = LoggerFactory.getLogger(MedicalCardController.class);

	@Autowired
	private MedicalCardService medicalCardService;
	
	/**
	 * 
	* @Title:getCardByCid
	* @Description:根据就诊卡id获取就诊卡信息
	* @param:@param cardId
	* @param:@return
	* @return:MedicalCard
	* @throws
	* @author:Hamster
	* @Date:2019年8月3日 上午10:37:43
	 */
	@ResponseBody
	@GetMapping("/get_medical_card_by_Cid")
	public MedicalCard getCardByCid(String cardId){
		return medicalCardService.getCardByCid(cardId);
	}
	
	/**
	* @Title:addCard
	* @Description:添加就诊卡信息
	* @param:@param medicalCard
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月2日 上午9:43:06
	 */
	@PostMapping("/add_medical_card")
	@ResponseBody
	public JsonResult addCard(@RequestBody MedicalCard medicalCard) {
		JsonResult result = new JsonResult();
		try {
			medicalCardService.addMedicalCard(medicalCard);
			result.setResult(medicalCard);
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(medicalCard);
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	 * 
	* @Title:checkCardTimes
	* @Description:检查身份证号是否已办理就诊卡
	* @param:@param person_id
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月2日 上午9:55:40
	 */
	@GetMapping("/check_person_card")
	@ResponseBody
	public JsonResult checkCardTimes(String person_id) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(medicalCardService.checkCardTimes(person_id));
			result.setStatus("ok");
		} catch (Exception e) {
			result.setResult(person_id);
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:getCardByPersonId
	* @Description:根据身份证号查询就诊卡
	* @param:@param person_id
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月3日 上午8:41:05
	 */
	@GetMapping("/get_card_by_person_id")
	@ResponseBody
	public JsonResult getCardByPersonId(String person_id) {
		JsonResult result = new JsonResult();
		try {
			MedicalCard card = medicalCardService.queryByPersonId(person_id);
			result.setResult(card);
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(person_id);
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:getCardQrCode
	* @Description:获取办理就诊卡支付二维码
	* @param:@param ygxh
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月3日 上午8:44:57
	 */
	@GetMapping("get_card_qr_code")
	@ResponseBody
	public JsonResult getCardQrCode() {
		JsonResult result = new JsonResult();
		try {
			Map<String, String> res = medicalCardService.getCardQrCode();
			result.setResult(res);
			result.setStatus("ok");
		} catch (Exception e) {
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:CheckPay
	* @Description:查询订单是否支付
	* @param:@param outTradeNo
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月3日 上午9:57:30
	 */
	@GetMapping("/check_pay")
	@ResponseBody
	public JsonResult CheckPay(String outTradeNo, String ygxh, String personId) {
		JsonResult result = new JsonResult();
		try {
			boolean flag = medicalCardService.checkPay(outTradeNo, ygxh, personId);
			result.setResult(flag);
			result.setStatus("ok");
		} catch (Exception e) {
			result.setResult(outTradeNo);
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:addMoneyPay
	* @Description:添加现金缴费记录控制器
	* @param:@param ygxh
	* @param:@param personId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月3日 下午12:00:15
	 */
	@GetMapping("/add_card_money_pay")
	@ResponseBody
	public JsonResult addMoneyPay(String ygxh, String personId) {
		JsonResult result = new JsonResult();
		try {
			medicalCardService.addMoneyPay(ygxh, personId);
			result.setResult(personId);
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(personId);
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:getCardByPage
	* @Description:分页查询所有的就诊卡信息
	* @param:@param pageNum
	* @param:@param pageSize
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月3日 下午2:16:13
	 */
	@GetMapping("/query_card_by_page")
	@ResponseBody
	public JsonResult getCardByPage(int pageNum, int pageSize) {
		log.debug("分页查询就诊卡信息");
		JsonResult result = new JsonResult();
		try {
			List<MedicalCard> list = medicalCardService.getByPage(pageNum, pageSize);
			result.setResult(list);
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(pageNum);
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:getAllPages
	* @Description:查询就诊卡信息的总页数
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月3日 下午2:40:46
	 */
	@GetMapping("/get_card_pages")
	@ResponseBody
	public JsonResult getAllPages(int pageSize) {
		JsonResult result = new JsonResult();
		try {
			int pages = medicalCardService.getAllPages(pageSize);
			result.setResult(pages);
			result.setStatus("ok");
		} catch (Exception e) {
			result.setResult(pageSize);
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:getALLCount
	* @Description:查询就诊卡总记录条数
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月3日 下午3:03:25
	 */
	@GetMapping("/get_card_count")
	@ResponseBody
	public JsonResult getALLCount() {
		JsonResult result = new JsonResult();
		try {
			int count = medicalCardService.getAllCount();
			result.setResult(count);
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:searchByKey
	* @Description:按条件查询就诊卡信息
	* @param:@param searchKey 关键字（姓名、职业、工作单位、联系电话、身份证号码）
	* @param:@param searchGender
	* @param:@param searchMarried
	* @param:@param searchCountry
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月5日 上午9:39:49
	 */
	@GetMapping("/search_card_by_key")
	@ResponseBody
	public JsonResult searchByKey(String searchKey, String searchGender, String searchMarried, String searchCountry, int pageNum, int pageSize) {
		JsonResult result = new JsonResult();
		try {
			List<MedicalCard> list = medicalCardService.searchByKey(searchKey, searchGender, searchMarried, searchCountry, pageNum, pageSize);
			result.setResult(list);
			result.setStatus("ok");
		} catch (Exception e) {
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:searchCount
	* @Description:根据条件搜索记录的总条数
	* @param:@param searchKey
	* @param:@param searchGender
	* @param:@param searchMarried
	* @param:@param searchCountry
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月5日 下午2:11:15
	 */
	@GetMapping("/get_search_card_count")
	@ResponseBody
	public JsonResult searchCount(String searchKey, String searchGender, String searchMarried, String searchCountry) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(medicalCardService.getAllSearchCount(searchKey, searchGender, searchMarried, searchCountry));
			result.setStatus("ok");
		} catch (Exception e) {
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:updateCard
	* @Description:修改就诊卡信息
	* @param:@param medicalCard
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月5日 下午5:40:19
	 */
	@PostMapping("/update_card")
	@ResponseBody
	public JsonResult updateCard(@RequestBody MedicalCard medicalCard) {
		JsonResult result = new JsonResult();
		try {
			medicalCardService.addMedicalCard(medicalCard);
			result.setResult(medicalCard);
			result.setStatus("ok");
		} catch (Exception e) {
			result.setResult(medicalCard);
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:getAllCard
	* @Description:查询所有就诊卡信息
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月5日 下午9:52:47
	 */
	@GetMapping("/get_all_card")
	@ResponseBody
	public JsonResult getAllCard() {
		JsonResult result = new JsonResult();
		try {
			result.setResult(medicalCardService.getAllCard());
			result.setStatus("ok");
		} catch (Exception e) {
			result.setStatus("error");
		}
		return result;
	}
	
}
