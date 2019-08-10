package com.his.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.DaemonThreadFactory;
import com.his.bean.OperationPaybean;
import com.his.pojo.OperationPay;
import com.his.service.OperationPayService;

/**  
* @ClassName: OperationPayController  
* @Description: TODO(手术划价controller)  
* @author TRC
* @date 2019年7月30日  上午9:04:49
*    
*/
@Controller
public class OperationPayController {
	@Autowired
	private OperationPayService operationPayService;
	
	/**
	 * 
	* @Title:getallOperationPays
	* @Description:TODO查询所有手术收费项
	* @param:@return
	* @return:List<OperationPaybean>
	* @throws
	* @author:TRC
	* @Date:2019年7月31日 下午5:54:15
	 */
@ResponseBody
@GetMapping("getall_ope_pays")
    public Map getallOperationPays(int curpage, int pagesize,String sou) throws Exception{
           
    	return operationPayService.getallOperationPays(curpage,pagesize,"%"+sou+"%");
    }
/**
 * 
* @Title:editoperation
* @Description:TODO修改指定的手术收费项
* @param:@param operationPay
* @return:void
* @throws
* @author:TRC
* @Date:2019年7月31日 下午5:59:06
 */
@ResponseBody
@PostMapping("edit_ope_pay")
    public void editoperation(@RequestBody OperationPaybean operationPaybean) {
    	
    	operationPayService.modificationOperationPay(operationPaybean);
    }
/**
 * 
* @Title:addoperation
* @Description:TODO添加手术收费项
* @param:@param name
* @param:@param desc
* @param:@param price
* @param:@param ygxh
* @return:void
* @throws
* @author:TRC
* @Date:2019年8月1日 下午2:12:59
 */
@ResponseBody
@GetMapping("add_ope_pay")
	public void addoperation(String name,String desc,BigDecimal price,String ygxh) {
		operationPayService.addOperationPay(name,desc,price,ygxh);
	}
/**
 * 
* @Title:deloperation
* @Description:TODO删除手术收费项
* @param:@param operid
* @return:void
* @throws
* @author:TRC
* @Date:2019年8月1日 下午2:13:31
 */
@ResponseBody
@GetMapping("del_ope_pay")
    public void deloperation(String operid) {
    	operationPayService.delOperationPay(operid);
    }

	
	/**
	 * 
	* @Title:getAllOperationPay
	* @Description:查询所有手术项
	* @param:@return
	* @return:List<OperationPay>
	* @throws
	* @author:Hamster
	* @Date:2019年8月10日 下午1:44:43
	 */
	@ResponseBody
	@GetMapping("/get_all_opreation_pay")
	public List <OperationPay> getAllOperationPay(){
		return operationPayService.getAllOperationPay();
	}
   
}
