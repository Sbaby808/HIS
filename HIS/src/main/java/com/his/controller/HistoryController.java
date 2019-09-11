package com.his.controller;

import com.his.pojo.History;
import com.his.pojo.JsonResult;
import com.his.service.HistoryService;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author Sbaby
 * @Date 2019/08/20 11:55
 * @Version 1.0
 */
@Controller
public class HistoryController {

    @Autowired
    private HistoryService historyService;


    /**
    * @Title:initHistory
    * @Description:初始化病历
    * @param:@param regId
    * @param:@return
    * @return:JsonResult
    * @throws
    * @author:Sbaby
    * @Date:2019年8月21日 上午11:25:31
     */
    @GetMapping("/init_history")
    @ResponseBody
    public JsonResult initHistory(String cardNum, String roomId) {
        JsonResult result = new JsonResult();
        try {
            result = historyService.initHistory(cardNum, roomId);
            System.out.println(result.getResult());
        } catch (Exception e) {
            e.printStackTrace();
            result.setResult("获取挂号信息失败！");
            result.setStatus("error");
        }
        return result;
    }
    
    /**
    * @Title:checkDiagnose
    * @Description:检查当前是否在诊断患者
    * @param:@param roomId
    * @param:@return
    * @return:JsonResult
    * @throws
    * @author:Sbaby
    * @Date:2019年8月21日 下午2:06:59
     */
    @GetMapping("/check_diagnose")
    @ResponseBody
    public JsonResult checkDiagnose(String roomId) {
    	JsonResult result = new JsonResult();
    	try {
			result = historyService.checkDiagnose(roomId);
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult("查询就诊患者失败！");
			result.setStatus("error");
		}
    	return result;
    }
    
    /**
    * @Title:searchIllness
    * @Description:搜索疾病
    * @param:@param searchKey
    * @param:@return
    * @return:JsonResult
    * @throws
    * @author:Sbaby
    * @Date:2019年8月21日 下午3:41:36
     */
    @GetMapping("/search_illness")
    @ResponseBody
    public JsonResult searchIllness(String searchKey) {
    	JsonResult result = new JsonResult();
    	try {
			result.setResult(historyService.searchIllness(searchKey));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(searchKey);
			result.setStatus("error");
		}
    	return result;
    }
    
    /**
    * @Title:getOwnHistory
    * @Description:查询员工的诊断记录
    * @param:@param ygxh
    * @param:@return
    * @return:JsonResult
    * @throws
    * @author:Sbaby
    * @Date:2019年8月24日 下午5:04:03
     */
    @GetMapping("/get_history_by_ygxh")
    @ResponseBody
    public JsonResult getOwnHistory(String ygxh, int pageNum, int pageSize) {
    	JsonResult result = new JsonResult();
    	try {
			result.setResult(historyService.getHistoryByYgxh(ygxh, pageNum, pageSize));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
    	return result;
    }
    
    /**
    * @Title:getOwnHistoryCount
    * @Description： 查询员工诊断记录的条数
    * @param:@param ygxh
    * @param:@return
    * @return:JsonResult
    * @throws
    * @author:Sbaby
    * @Date:2019年8月24日 下午5:19:16
     */
    @GetMapping("/get_own_history_count")
    @ResponseBody
    public JsonResult getOwnHistoryCount(String ygxh) {
    	JsonResult result = new JsonResult();
    	try {
			result.setResult(historyService.getHistoryCountByYgxh(ygxh));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
    	return result;
    }
    
    /**
    * @Title:addHistory
    * @Description:诊断结束
    * @param:@param history
    * @param:@return
    * @return:JsonResult
    * @throws
    * @author:Sbaby
    * @Date:2019年8月24日 上午11:20:02
     */
    @PostMapping("/add_history")
    @ResponseBody
    public JsonResult addHistory(@RequestBody History history) {
    	JsonResult result = new JsonResult();
    	try {
			result.setResult(historyService.addHistory(history));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
    	return result;
    }
    
    /**
    * @Title:searchHistoryCount
    * @Description:搜索诊断记录条数
    * @param:@param ygxh
    * @param:@param illnessKey
    * @param:@param searchStartTime
    * @param:@param searchEndTime
    * @param:@return
    * @return:JsonResult
    * @throws
    * @author:Sbaby
    * @Date:2019年8月25日 下午2:51:09
     */
    @GetMapping("/search_history_count")
    @ResponseBody
    public JsonResult searchHistoryCount(String ygxh, String nameKey, String illnessKey, String searchStartTime, String searchEndTime) {
    	JsonResult result = new JsonResult();
    	try {
			result.setResult(historyService.searchHistoryCount(ygxh, nameKey, illnessKey, searchStartTime, searchEndTime));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
    	return result;
    }
    
    /**
    * @Title:searchHistoryCountByCardId
    * @Description:根据就诊卡编号查询诊断记录条数
    * @param:@param cardId
    * @param:@param searchStartTime
    * @param:@param searchEndTime
    * @param:@return
    * @return:JsonResult
    * @throws
    * @author:Sbaby
    * @Date:2019年8月26日 下午3:29:28
     */
    @GetMapping("/search_history_count_by_cardId")
    @ResponseBody
    public JsonResult searchHistoryCountByCardId(String cardId, String searchStartTime, String searchEndTime) {
    	JsonResult result = new JsonResult();
    	try {
			result.setResult(historyService.searchHistoryCountByCardId(cardId, searchStartTime, searchEndTime));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
    	return result;
    }
    
    /**
    * @Title:searchHisPreCountByCardId
    * @Description:查询门诊带处方诊断记录 条数
    * @param:@param cardId
    * @param:@param searchStartTime
    * @param:@param searchEndTime
    * @param:@return
    * @return:JsonResult
    * @throws
    * @author:Sbaby
    * @Date:2019年9月3日 上午9:00:11
     */
    @GetMapping("/search_his_pre_count_by_card_id")
    @ResponseBody
    public JsonResult searchHisPreCountByCardId(String cardId, String searchStartTime, String searchEndTime ) {
    	JsonResult result = new JsonResult();
    	try {
			result.setResult(historyService.searchHisPreCountByCardId(cardId, searchStartTime, searchEndTime));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
    	return result;
    }
    
    /**
    * @Title:searchHisPreByCardId
    * @Description:查询带处方的门诊诊断记录
    * @param:@param cardId
    * @param:@param searchStartTime
    * @param:@param searchEndTime
    * @param:@return
    * @return:JsonResult
    * @throws
    * @author:Sbaby
    * @Date:2019年9月3日 上午9:08:30
     */
    @GetMapping("/search_his_pre_by_card_id")
    @ResponseBody
    public JsonResult searchHisPreByCardId(String cardId, String searchStartTime, String searchEndTime) {
    	JsonResult result = new JsonResult();
    	try {
			result.setResult(historyService.searchHisPreByCardId(cardId, searchStartTime, searchEndTime));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
    	return result;
    }
    
    /**
    * @Title:searchHistoryByCardId
    * @Description:根据就诊卡编号查询诊断记录
    * @param:@param cardId
    * @param:@param searchStartTime
    * @param:@param searchEndTime
    * @param:@param pageNum
    * @param:@param pageSize
    * @param:@return
    * @return:JsonResult
    * @throws
    * @author:Sbaby
    * @Date:2019年8月26日 下午3:30:13
     */
    @GetMapping("/search_history_by_cardId")
    @ResponseBody
    public JsonResult searchHistoryByCardId(String cardId, String searchStartTime, String searchEndTime, int pageNum, int pageSize) {
    	JsonResult result = new JsonResult();
    	try {
			result.setResult(historyService.searchHistoryByCardId(cardId, searchStartTime, searchEndTime, pageNum, pageSize));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
    	return result;
    }
    
    /**
    * @Title:searchHistoryCount
    * @Description:搜索诊断记录
    * @param:@param ygxh
    * @param:@param illnessKey
    * @param:@param searchStartTime
    * @param:@param searchEndTime
    * @param:@param pageNum
    * @param:@param pageSize
    * @param:@return
    * @return:JsonResult
    * @throws
    * @author:Sbaby
    * @Date:2019年8月25日 下午3:14:48
     */
    @GetMapping("/search_history")
    @ResponseBody
    public JsonResult searchHistoryCount(String ygxh, String nameKey, String illnessKey, String searchStartTime, String searchEndTime, int pageNum, int pageSize) {
    	JsonResult result = new JsonResult();
    	try {
			result.setResult(historyService.searchHistory(ygxh, nameKey, illnessKey, searchStartTime, searchEndTime, pageNum, pageSize));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
    	return result;
    }
    
    /**
    * @Title:getSolveSchemeByHistoryId
    * @Description:根据诊断记录编号查询医嘱
    * @param:@param historyId
    * @param:@return
    * @return:JsonResult
    * @throws
    * @author:Sbaby
    * @Date:2019年8月26日 上午9:47:39
     */
    @GetMapping("/get_solve_scheme_by_historyId")
    @ResponseBody
    public JsonResult getSolveSchemeByHistoryId(String historyId) {
    	JsonResult result = new JsonResult();
    	try {
			result.setResult(historyService.getSolveSchemeByHistoryId(historyId));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
    	return result;
    }
    
    /**
    * @Title:getHistoryCountByCardId
    * @Description:查询门诊诊断记录条数
    * @param:@param cardId
    * @param:@return
    * @return:JsonResult
    * @throws
    * @author:Sbaby
    * @Date:2019年9月11日 下午6:18:09
     */
    @GetMapping("/get_his_count_by_card")
    @ResponseBody
    public JsonResult getHistoryCountByCardId(String cardId) {
    	JsonResult result = new JsonResult();
    	try {
			result.setResult(historyService.getHistoryCountByCardId(cardId));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
    	return result;
    }
    
    /**
    * @Title:getHistoryByCardId
    * @Description:查询门诊诊断记录
    * @param:@param cardId
    * @param:@param pageNum
    * @param:@param pageSize
    * @param:@return
    * @return:JsonResult
    * @throws
    * @author:Sbaby
    * @Date:2019年9月11日 下午6:32:20
     */
    @GetMapping("/get_his_by_card")
    @ResponseBody
    public JsonResult getHistoryByCardId(String cardId, int pageNum, int pageSize) {
    	JsonResult result = new JsonResult();
    	try {
			result.setResult(historyService.getHistoryByCardId(cardId, pageNum, pageSize));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
    	return result;
    }

}
