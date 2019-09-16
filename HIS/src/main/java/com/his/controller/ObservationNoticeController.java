package com.his.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.dao.IRemainRecordDAO;
import com.his.pojo.JsonResult;
import com.his.pojo.ObservationBed;
import com.his.pojo.ObservationNotice;
import com.his.pojo.RemainRecord;
import com.his.service.ObservationNoticeService;

/**  
* @ClassName: ObservationNoticeController  
* @Description: 留观通知Controlelr
* @author Sbaby
* @date 2019年8月23日  下午2:42:39
*    
*/
@Controller
public class ObservationNoticeController {

	@Autowired
	private ObservationNoticeService observationNoticeService;
	@Autowired
	private IRemainRecordDAO remainrecorddao;
	
	/**
	* @Title:addObsNotice
	* @Description:新增或编辑留观通知单
	* @param:@param observationNotice
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月23日 下午2:44:19
	 */
	@PostMapping("/add_obs_notice")
	@ResponseBody
	public JsonResult addObsNotice(@RequestBody ObservationNotice observationNotice) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(observationNoticeService.addObs(observationNotice));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:delObsById
	* @Description:根据编号删除留观建议
	* @param:@param id
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月23日 下午4:57:11
	 */
	@GetMapping("/del_obs_by_id")
	@ResponseBody
	public JsonResult delObsById(String id) {
		JsonResult result = new JsonResult();
		try {
			observationNoticeService.delObsById(id);
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("ok");
		}
		return result;
	}
	
	/**
	* @Title:findBySolveId
	* @Description:根据医嘱编号查询留观通知
	* @param:@param solveId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月23日 下午5:13:14
	 */
	@GetMapping("/find_obs_by_solve_id")
	@ResponseBody
	public JsonResult findBySolveId(String solveId) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(observationNoticeService.findBySolveId(solveId));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:findByHistoryId
	* @Description:根据诊断记录编号查询留观通知
	* @param:@param historyId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月25日 下午1:32:59
	 */
	@GetMapping("/find_bos_by_history_id")
	@ResponseBody
	public JsonResult findByHistoryId(String historyId) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(observationNoticeService.findByHistoryId(historyId));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	//出院时候删除这条记录 同时置空对应的床位
		@ResponseBody
		@GetMapping("/deleteremain")
		public void deleteremain(String remainId) {
			observationNoticeService.deleteaa(remainId);
		}
		//查询留观登记表有多少条记录
		@ResponseBody
		@GetMapping("/findcountssss")
		public long findcountsss() {
			return observationNoticeService.findcountss();
		}
		@ResponseBody
		@GetMapping("/updateremain")
		public void Update(String remainId) {
			observationNoticeService.update(remainId);
		}
		//登记
		@ResponseBody
		@GetMapping("/addliuguan")
		public int addliuguan(String jzcwid,String cardid,String observaId) {
			return observationNoticeService.addliuguan(jzcwid, cardid, observaId);
		}
		//找到一个空闲的床位
		@ResponseBody
		@GetMapping("/findbed")
		public ObservationBed findonenull() {
			return observationNoticeService.findonenull();
		}
		@ResponseBody
		@GetMapping("/findallremain")
		public List<RemainRecord> findall(){
			return (List<RemainRecord>) remainrecorddao.findAll();
		}
		//分页查询所有的留观登记表
		@ResponseBody
		@GetMapping("findallbypage1")
		public List<RemainRecord> findrr(int currentpage){
			return observationNoticeService.findrr(currentpage);
		}
		//找到所有留观通知表数目
		@ResponseBody
		@GetMapping("/findcountsonotice")
		public long findcounts1() {
			return observationNoticeService.findcount();
		}
		//分页查询留观通知单
		@ResponseBody
		@GetMapping("/findallservationbypage")
		public List<ObservationNotice> findobsbypage(int currentpage){
			return observationNoticeService.findobs(currentpage);
		}
}
