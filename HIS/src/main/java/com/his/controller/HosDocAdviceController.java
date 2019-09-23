package com.his.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.HosDoctorAdvice;
import com.his.pojo.JsonResult;
import com.his.service.HosDocAdviceService;


/**
 * 
* @ClassName: HosDocAdviceController  
* @Description: 住院医嘱  
* @author Hamster
* @date 2019年8月7日  上午9:34:27
*
 */
@Controller
public class HosDocAdviceController {

	@Autowired
	private HosDocAdviceService hosDocAdviceService;
	
	/**
	 * 
	* @Title:getHosDocAdvice
	* @Description:查询所有住院医嘱
	* @param:@return
	* @return:List<HosDoctorAdvice>
	* @throws
	* @author:Hamster
	* @Date:2019年8月7日 上午10:48:22
	 */
	@ResponseBody
	@GetMapping("/get_all_hos_doc_advice")
	public List <HosDoctorAdvice> getHosDocAdvice(){
		return hosDocAdviceService.getHosDocAdvice();
	}
	
	/**
	 * 
	* @Title:getHosDocAdviceByDid
	* @Description:根据诊断记录id查询医嘱
	* @param:@param diagId
	* @param:@return
	* @return:HosDoctorAdvice
	* @throws
	* @author:Hamster
	* @Date:2019年8月7日 上午9:35:24
	 */
	@ResponseBody
	@GetMapping("/get_hos_advice_byDid")
	public HosDoctorAdvice getHosDocAdviceByDid(String diagId){
		return hosDocAdviceService.getHosDocAdviceByDid(diagId);
	}
	
	/**
	 * 
	* @Title:getHosDocAdviceByPage
	* @Description:分页查询住院医嘱
	* @param:@param curpage
	* @param:@param pagesize
	* @param:@return
	* @return:Map
	 * @throws ParseException 
	* @throws
	* @author:Hamster
	* @Date:2019年8月13日 下午9:15:27
	 */
	@ResponseBody
	@GetMapping("/get_hos_advice_byPage")
	public Map getHosDocAdviceByPage(String start,String end,String text1,String text2,int curpage,int pagesize) throws ParseException{
		String cardName = "%"+text1+"%";
		String ksName = "%"+text2+"%";
		return hosDocAdviceService.getHosDocAdviceByPage(start,end,cardName,ksName,curpage, pagesize);
	}
	
	/**
	 * 
	* @Title:addHosDocAdvice
	* @Description:新增住院医嘱
	* @param:@param advice
	* @param:@throws ParseException
	* @return:void
	* @throws
	* @author:Hamster
	* @Date:2019年8月7日 上午10:22:12
	 */
	@ResponseBody
	@PostMapping("/add_hos_doc_advice")
	public JsonResult addHosDocAdvice(@RequestBody HosDoctorAdvice advice) throws ParseException{
		JsonResult result = new JsonResult();
		try{
			result.setResult(hosDocAdviceService.addHosDocAdvice(advice));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	 * 
	* @Title:getDocAdviceByDocId
	* @Description:根据医嘱id查询医嘱
	* @param:@param docId
	* @param:@return
	* @return:HosDoctorAdvice
	* @throws
	* @author:Hamster
	* @Date:2019年8月7日 上午11:48:39
	 */
	@ResponseBody
	@GetMapping("/get_hos_advice_byDocid")
	public HosDoctorAdvice getDocAdviceByDocId(String docId){
		return hosDocAdviceService.getDocAdviceByDocId(docId);
	}
	
	/**
	 * 
	* @Title:changeHosDocAdvice
	* @Description:修改医嘱
	* @param:@param advice
	* @return:void
	* @throws
	* @author:Hamster
	* @Date:2019年8月7日 下午12:01:30
	 */
	@ResponseBody
	@GetMapping("/change_hos_doc_advice")
	public void changeHosDocAdvice(String docId,String docText){
		hosDocAdviceService.changeHosDocAdvice(docId,docText);
	}
	
	/**
	 * 
	* @Title:endHosDocAdvice
	* @Description:医嘱终止
	* @param:@param advice
	* @param:@throws ParseException
	* @return:void
	* @throws
	* @author:Hamster
	* @Date:2019年8月7日 下午7:19:02
	 */
	@ResponseBody
	@PostMapping("/end_hos_doc_advice")
	public void endHosDocAdvice(String docId) throws ParseException{
		hosDocAdviceService.endHosDocAdvice(docId);
	}
	
	
}
