package com.his.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alipay.api.AlipayApiException;
import com.his.dao.IEmpInformationDao;
import com.his.dao.IOutpatientPayDao;
import com.his.dao.IOutpatientRegistrationDao;
import com.his.pojo.JsonResult;
import com.his.pojo.OutpatientPay;
import com.his.pojo.OutpatientRegistration;
import com.his.utils.AliPay;

/**  
* @ClassName: OutpatientPayService  
* @Description: 门诊其他缴费记录Service
* @author Sbaby
* @date 2019年8月3日  上午10:05:40
*    
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class OutpatientPayService {

	@Autowired
	private IOutpatientPayDao outpatientPayDao;
	@Autowired
	private IOutpatientRegistrationDao outpatientRegistrationDao;
	@Autowired
	private IEmpInformationDao empInformationDao;
	
	public void addOutPatientPay(OutpatientPay outpatientPay) {
		outpatientPay.setPayId(UUID.randomUUID().toString().replaceAll("-", ""));
		outpatientPayDao.save(outpatientPay);
	}
	
	/**
	* @Title:checkRegPay
	* @Description:检查门诊挂号是否人工缴费
	* @param:@param regId
	* @param:@return
	* @return:boolean
	* @throws
	* @author:Sbaby
	* @Date:2019年8月13日 下午4:02:43
	 */
	public boolean checkRegPay(String regId) {
		OutpatientPay outpatientPay = outpatientPayDao.checkRegPay(regId);
		if(outpatientPay == null) {
			return false;
		} else if("退号".equals(outpatientPay.getPayType())) {
			return false;
		}
		return true;
	}
	
	/**
	* @Title:addRegPay
	* @Description:门诊现金缴费
	* @param:@param outpatientPay
	* @return:void
	 * @throws AlipayApiException 
	* @throws
	* @author:Sbaby
	* @Date:2019年8月15日 上午9:31:05
	 */
	public void addRegPay(OutpatientPay outpatientPay) throws AlipayApiException {
		// 开启新线程关闭二维码支付
		 Thread t = new Thread(new Runnable(){  
	           public void run(){  
	           // run方法具体重写
	        // 先将支付二维码支付关闭
	       		try {
					AliPay.cancelTrade(outpatientPay.getOutpatientRegistration().getRegId());
				} catch (AlipayApiException e) {
					e.printStackTrace();
				}
	           }
		 });  
	    t.start();  
		// 插入缴费对象
		outpatientPay.setPayId(UUID.randomUUID().toString().replaceAll("-", ""));
		outpatientPay.setPayType("现金");
		outpatientPay.setOutPayTime(new Date());
		outpatientPay.setActStatus("已缴费");
		OutpatientRegistration outpatientRegistration = outpatientPay.getOutpatientRegistration();
		// 修改挂号表
		outpatientRegistration.setPayId(outpatientPay.getPayId());
		outpatientRegistration.setOutpatientPay(outpatientPay);
		outpatientRegistration.setRegStatus("已缴费");
		outpatientRegistrationDao.save(outpatientRegistration);
		outpatientPayDao.save(outpatientPay);
	}
	
	/**
	* @Title:regPayBack
	* @Description:门诊挂号退费
	* @param:@param regId
	* @param:@param ygxh
	* @return:void
	* @throws
	* @author:Sbaby
	* @Date:2019年9月10日 下午2:31:35
	 */
	public JsonResult regPayBack(String regId, String ygxh) {
		JsonResult result = new JsonResult();
		// 获取挂号对象
		OutpatientRegistration outpatientRegistration = outpatientRegistrationDao.findById(regId).get();
		if(outpatientRegistration == null) {
			result.setResult("此挂号对象不存在！");
			result.setStatus("error");
		} else {
			// 判断此挂号单是否已经缴费
			if(outpatientRegistration.getOutpatientPay() != null && "已缴费".equals(outpatientRegistration.getOutpatientPay().getActStatus())) {
				// 判断此挂号是已排队候诊过
				if(outpatientRegistration.getOutMedicalRecord() != null) {
					result.setStatus("error");
					result.setResult("此挂号已排队候诊，无法退费！");
				} else {
					// 判断是否在允许时间内（与挂号时间不超过两小时）
					if((new Date().getTime() - outpatientRegistration.getRegTime().getTime()) <= 1000 * 60 * 60 * 2) {
						// 满足条件
						OutpatientPay outpatientPay = outpatientRegistration.getOutpatientPay();
						outpatientPay.setActStatus("已退费");
						outpatientPay.setEmpInformation(empInformationDao.findById(ygxh).get());
						outpatientPayDao.save(outpatientPay);
						outpatientRegistration.setRegStatus("已退费");
						outpatientRegistrationDao.save(outpatientRegistration);
						result.setResult(outpatientPay);
						result.setStatus("ok");
					} else {
						result.setResult("挂号已超过两小时，无法退费！");
						result.setStatus("error");
					}
				}
			} else {
				result.setStatus("error");
				result.setResult("此挂号还未缴费！");
			}
		}
		return result;
	}
	
	/**
	* @Title:addAliPay
	* @Description:添加扫码缴费记录
	* @param:
	* @return:void
	* @throws
	* @author:Sbaby
	* @Date:2019年8月15日 下午4:33:41
	 */
	public void addAliPay(Map<String, String> params) {
		System.out.println(params.get("out_trade_no"));
		// 获取挂号对象
		OutpatientRegistration outpatientRegistration = outpatientRegistrationDao.findById(params.get("out_trade_no")).get();
		OutpatientPay outpatientPay = new OutpatientPay();
		outpatientPay.setPayId(UUID.randomUUID().toString().replaceAll("-", ""));
		outpatientPay.setPayType("支付宝");
		outpatientPay.setOutPayTime(new Date());
		outpatientPay.setActStatus("已缴费");
		outpatientPay.setPayAmount(new BigDecimal(params.get("receipt_amount")));
		outpatientRegistration.setPayId(outpatientPay.getPayId());
		outpatientRegistration.setOutpatientPay(outpatientPay);
		outpatientRegistration.setRegStatus("已缴费");
		outpatientRegistrationDao.save(outpatientRegistration);
		outpatientPayDao.save(outpatientPay);
	}
	
}
