package com.his.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.bean.Addmedicbean;
import com.his.bean.Medicinebean;
import com.his.service.OpeDrugDetailsService;

/**  
* @ClassName: OpeDrugDetailController  
* @Description: TODO(手术用药详情controller)  
* @author TRC
* @date 2019年7月30日  上午9:13:02
*    
*/
@Controller
public class OpeDrugDetailController {
	@Autowired
	private OpeDrugDetailsService opeDrugDetailsService;
	/**
	 * 
	* @Title:getMedicinebeans
	* @Description:TODO去除批次不同但属于同一种的药的重复，选择最快过期的批次
	* @param:@return
	* @return:List<Medicinebean>
	* @throws
	* @author:TRC
	* @Date:2019年8月9日 下午3:52:44
	 */
	@ResponseBody
	@GetMapping("get_medic")
    public List<Medicinebean> getMedicinebeans(){
    	return opeDrugDetailsService.getMedicinebeans();
    }
	/**
	 * 
	* @Title:getbyid
	* @Description:TODO给选中的药品一个可以添加数量的字段
	* @param:@param pcid
	* @param:@return
	* @return:List<Addmedicbean>
	* @throws
	* @author:TRC
	* @Date:2019年8月9日 下午3:53:03
	 */
	@ResponseBody
	@GetMapping("get_name_byid")
	public List<Addmedicbean> getbyid(String pcid) {
		return opeDrugDetailsService.getname(pcid);
	}
	/**
	 * 
	* @Title:addopeDrugDetail
	* @Description:TODO添加用药详情
	* @param:@param list
	* @return:void
	* @throws
	* @author:TRC
	* @Date:2019年8月9日 下午3:53:19
	 */
	@ResponseBody
	@PostMapping("add_ope_drug")
	public String addopeDrugDetail(@RequestBody List<Addmedicbean> list) {
		return opeDrugDetailsService.addopdrug(list);
	}
	/**
	 * 
	* @Title:getopemedic
	* @Description:TODO查找手术用药详情
	* @param:@param opeid
	* @param:@return
	* @return:List<Addmedicbean>
	* @throws
	* @author:TRC
	* @Date:2019年8月9日 下午3:53:35
	 */
	@ResponseBody
	@GetMapping("chakan_ope_drug")
	public List<Addmedicbean> getopemedic(String opeid){
		return opeDrugDetailsService.getopemedic(opeid);
	}
}
