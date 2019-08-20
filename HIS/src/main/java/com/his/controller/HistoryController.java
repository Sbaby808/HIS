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


    @GetMapping("/init_history")
    @ResponseBody
    public JsonResult initHistory(String regId) {
        JsonResult result = new JsonResult();
        try {
            result.setResult(historyService.initHistory(regId));
            result.setStatus("ok");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus("error");
        }
        return result;
    }

}
