package com.his.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.his.pojo.EmpInformation;
import com.his.pojo.JsonResult;
import com.his.pojo.OutpatientRegistration;
import com.his.service.OutpatientRegistrationService;
import com.his.utils.SimpleTools;

/**  
* @ClassName: OutpatientRegistrationController  
* @Description: 门诊挂号控制器
* @author Sbaby
* @date 2019年8月9日  下午4:23:32
*    
*/
@Controller
public class OutpatientRegistrationController {

	@Autowired
	private OutpatientRegistrationService outpatientRegistrationService;
	
	/**
	* @Title:getAllKSForOut
	* @Description:查询门诊的所有科室
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月9日 下午4:27:55
	 */
	@GetMapping("/get_all_ks_for_out")
	@ResponseBody
	public JsonResult getAllKSForOut() {
		JsonResult result = new JsonResult();
		try {
			result.setResult(outpatientRegistrationService.getKSbyOut());
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:getAllTPForOut
	* @Description:查询门诊的所有职称
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月9日 下午4:57:13
	 */
	@GetMapping("/get_all_tp_for_out")
	@ResponseBody
	public JsonResult getAllTPForOut() {
		JsonResult result = new JsonResult();
		try {
			result.setResult(outpatientRegistrationService.getTpbyOut());
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:getDocByKsAndTp
	* @Description:根据日期、科室和职称查询门诊医生
	* @param:@param ks
	* @param:@param tp
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月10日 上午8:47:43
	 */
	@GetMapping("/get_doc_by_ks_and_tp")
	@ResponseBody
	public JsonResult getDocByKsAndTp(String ks, String tp, Long doDate) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(outpatientRegistrationService.getDocByKsAndTp(ks, tp, doDate));
			result.setStatus("ok");
		} catch (Exception e) {
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:getWorktime
	* @Description:根据医生编号查询排班时间
	* @param:@param empId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月10日 上午9:24:33
	 */
	@GetMapping("/get_worktime_by_empid")
	@ResponseBody
	public JsonResult getWorktime(String empId) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(outpatientRegistrationService.getWorktimeByEmpid(empId));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:getRegistrationCount
	* @Description:根据医生编号查询当日挂号人数
	* @param:@param empId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月10日 下午2:05:33
	 */
	@GetMapping("/get_registration_by_empId")
	@ResponseBody
	public JsonResult getRegistrationCount(String empId) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(outpatientRegistrationService.getRegistrationCount(empId));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:getOutpatientDoctorNum
	* @Description:查询门诊各科室医生挂号详情
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月10日 下午2:44:28
	 */
	@GetMapping("/get_outpatient_doctor_num")
	@ResponseBody
	public JsonResult getOutpatientDoctorNum() {
		JsonResult result = new JsonResult();
		try {
			result.setResult(outpatientRegistrationService.getOutpatientDoctorCount());
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:addRegistration
	* @Description:生成挂号单
	* @param:@param outpatientRegistration
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月12日 上午11:07:29
	 */
	@PostMapping("/add_registration")
	@ResponseBody
	public JsonResult addRegistration(@RequestBody OutpatientRegistration outpatientRegistration) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(outpatientRegistrationService.addReg(outpatientRegistration));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(outpatientRegistration);
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:getDocByKsAndTp
	* @Description:根据日期、科室和职称随机分配门诊医生
	* @param:@param ks
	* @param:@param tp
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月10日 上午8:47:43
	 */
	@GetMapping("/get_rnd_doc_by_ks_and_tp")
	@ResponseBody
	public JsonResult getRandomDocByKsAndTp(String ks, String tp, Long doDate) {
		JsonResult result = new JsonResult();
		try {
			EmpInformation empInformation = outpatientRegistrationService.getRandomDocByKsAndTp(ks, tp, doDate);
			if(empInformation == null) {
				result.setStatus("error");
			} else {
				result.setResult(empInformation);
				result.setStatus("ok");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:checkEmp
	* @Description:检查医生是否有余号
	* @param:@param ygxh
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月12日 下午3:46:02
	 */
	@GetMapping("/check_emp_reg")
	@ResponseBody
	public JsonResult checkEmp(String ygxh, int total) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(outpatientRegistrationService.checkEmp(ygxh, total));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}

	/**
	* @Title:generatorRegTable
	* @Description:打印挂号单
	* @param:@param response
	* @param:@param regId
	* @param:@return
	* @return:JsonResult
	 * @throws UnsupportedEncodingException 
	* @throws
	* @author:Sbaby
	* @Date:2019年8月12日 下午8:43:06
	 */
	@GetMapping("/generator_reg_table")
	@ResponseBody
	public void generatorRegTable(HttpServletResponse res, String regId, String ygxh) throws UnsupportedEncodingException {
		// 生成支付二维码
		Map<String, String> map = outpatientRegistrationService.getCardQrCode(regId);
		// 生成检查是否缴费二维码
		Map<String, String> checkMap = outpatientRegistrationService.getCheckQrCode(map.get("outTradeNo"), ygxh, regId);
		// 生成挂号单
		String fileName = "挂号单-" + SimpleTools.formatDate(new Date(), "yyyy-MM-dd_HH_mm_ss") + ".docx";
		res = outpatientRegistrationService.generatorRegTable(res, regId, fileName, map, checkMap);
		res.setHeader("content-type", "application/octet-stream;charset=UTF-8");
		res.setCharacterEncoding("utf-8");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment; filename=" +  java.net.URLEncoder.encode(fileName, "UTF-8"));
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        FileInputStream input = null;
     
        try {
          os = res.getOutputStream();
          input = new FileInputStream(new File("d://HIS//reg_table//" + fileName ));
          bis = new BufferedInputStream(input);
          int i = bis.read(buff);
     
          while (i != -1) {
            os.write(buff, 0, buff.length);
            os.flush();
            i = bis.read(buff);
          }
        } catch ( IOException e ) {
          e.printStackTrace();
        } finally {
          if (bis != null) {
            try {
            	res.setHeader("Content-Length", String.valueOf(input.getChannel().size()));
              bis.close();
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        }
	}
	
	/**
	* @Title:checkPay
	* @Description:检查此挂号单是否已经缴费
	* @param:@param tradeNo
	* @param:@param ygxh
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月13日 下午3:45:48
	 */
	@GetMapping("/check_reg_pay")
	@ResponseBody
	public JsonResult checkPay(String outTradeNo, String regId, String ygxh) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(outpatientRegistrationService.checkPay(outTradeNo, ygxh, regId));
			result.setStatus("ok"); 
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:testClosePay
	* @Description:将支付二维码设置为失效
	* @param:@param outTradeNo
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月14日 下午12:09:08
	 */
	@GetMapping("/test_close_pay")
	@ResponseBody
	public JsonResult testClosePay(String outTradeNo) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(outpatientRegistrationService.closeAliPay(outTradeNo));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:getAllRegsCount
	* @Description:根据就诊卡号查询所有挂号记录的数量
	* @param:@param cardNum
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月14日 下午4:04:26
	 */
	@GetMapping("/get_all_reg_count_by_cardnum")
	@ResponseBody
	public JsonResult getAllRegsCount(String cardNum) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(outpatientRegistrationService.getAllRegsCount(cardNum));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:getAllReg
	* @Description:根据就诊卡号查询所有门诊挂号记录
	* @param:@param cardNum
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月14日 下午3:28:15
	 */
	@GetMapping("/get_all_reg_by_cardnum")
	@ResponseBody
	public JsonResult getAllReg(String cardNum, int pageNum, int pageSize) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(outpatientRegistrationService.getAllRegs(cardNum, pageNum, pageSize));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}


	@DeleteMapping("/remove_out_reg")
	@ResponseBody
	public JsonResult removeOutReg(String regId) {
		JsonResult result = new JsonResult();
		try {
			outpatientRegistrationService.removeReg(regId);
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:getLastOutReg
	* @Description:获取最新的挂号记录
	* @param:@param cardId
	* @param:@param roomId
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月24日 下午3:23:07
	 */
	@GetMapping("/get_last_out_reg")
	@ResponseBody
	public JsonResult getLastOutReg(String cardId, String roomId) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(outpatientRegistrationService.getLastOutReg(cardId, roomId));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
}
