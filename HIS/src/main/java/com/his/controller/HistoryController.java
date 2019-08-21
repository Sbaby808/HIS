package com.his.controller;

import com.his.pojo.JsonResult;
import com.his.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    

}
