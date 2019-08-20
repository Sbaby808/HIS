package com.his.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.his.pojo.JsonResult;
import com.his.service.OutMedicalRecordService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

    /**
    * @Title:checkForCode
    * @Description:候诊排队检查
    * @param:@param file
    * @param:@param waitingRoomId
    * @param:@return
    * @return:JsonResult
    * @throws
    * @author:Sbaby
    * @Date:2019年8月19日 下午3:11:18
     */
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
    
    /**
    * @Title:getWaitingQueue
    * @Description:根据候诊厅编号查询排队队列
    * @param:@param roomId
    * @param:@return
    * @return:JsonResult
    * @throws
    * @author:Sbaby
    * @Date:2019年8月19日 下午3:13:33
     */
    @GetMapping("get_queue_by_room_id")
    @ResponseBody
    public JsonResult getWaitingQueue(String roomId) {
    	JsonResult result = new JsonResult();
    	try {
			result.setResult(outMedicalRecordService.getQueueByRoomId(roomId));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(roomId);
			result.setStatus("error");
		}
    	return result;
    }

    @GetMapping("/call_patient_by_roomId")
    @ResponseBody
    public JsonResult callPatient(String roomId) {
        JsonResult result = new JsonResult();
        try {
            result.setResult(outMedicalRecordService.callPatient(roomId));
            result.setStatus("ok");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus("error");
        }
        return result;  
    }

    @GetMapping("/call_next")
    @ResponseBody
    public JsonResult callNext(String roomId) {
        JsonResult result = new JsonResult();
        try {
            outMedicalRecordService.callNext(roomId);
            result.setStatus("ok");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus("error");
        }
        return result;
    }

    @GetMapping("/check_call")
    @ResponseBody
    public JsonResult checkCall(String roomId) {
        JsonResult result = new JsonResult();
        try {
            result.setResult(outMedicalRecordService.checkCall(roomId));
            result.setStatus("ok");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus("error");
        }
        return  result;
    }

}
