package com.his.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.his.pojo.JsonResult;
import com.his.service.OutMedicalRecordService;

/**
 * @Author Sbaby
 * @Description 门诊就诊排队控制器
 * @Date 2019/08/17 15:44
 * @Version 1.0
 */
@Controller
public class OutMedicalRecordController {

    @Autowired
    private OutMedicalRecordService outMedicalRecordService;

    @RequestMapping("/check_for_out_wait_code")
    @ResponseBody
    public JsonResult checkForCode(@RequestParam("file") MultipartFile file, String waitingRoomId) {
        System.out.println(file);
        JsonResult result = new JsonResult();
        try{
            if(file == null) {
                result.setResult("上传失败！");
                result.setStatus("error");
            } else {
                return outMedicalRecordService.checkForCode(file, waitingRoomId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setResult("解析错误！");
            result.setStatus("error");
        }
        return result;
    }

}
