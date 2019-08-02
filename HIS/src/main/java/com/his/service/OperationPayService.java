package com.his.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.his.bean.OperationPaybean;
import com.his.dao.IEmpInformationDao;
import com.his.dao.IOperationPayDao;

import com.his.pojo.OperationPay;
import com.his.utils.UUIDGenerator;


/**  
* @ClassName: OperationPayService  
* @Description: TODO(手术划价Service)  
* @author TRC
* @date 2019年7月30日  上午9:02:40
*    
*/
@Service
public class OperationPayService {
	@Autowired
	private IOperationPayDao iOperationPayDao;
	@Autowired
	private IEmpInformationDao iEmpInformationDao;
	
	private UUIDGenerator uuid;
	/**
	 * 
	* @Title:addOperationPay
	* @Description:TODO添加手术收费项
	* @param:@param operationPay
	* @return:void
	* @throws
	* @author:TRC
	* @Date:2019年7月30日 下午3:44:59
	 */
	public void addOperationPay(String name,String desc,BigDecimal price,String ygxh) {
		OperationPay operationPay=new OperationPay();
		operationPay.setEmpInformation(iEmpInformationDao.findById(ygxh).get());
		operationPay.setOperPayDesc(desc);
		operationPay.setOperPayName(name);
		operationPay.setOperPayPrice(price);
		operationPay.setOperPayTime(new Date());
		operationPay.setOperPayId(uuid.getUUID());
		iOperationPayDao.save(operationPay);
	}
	/**
	 * 
	* @Title:modificationOperationPay
	* @Description:TODO修改手术收费项
	* @param:@param operationPay
	* @return:void
	* @throws
	* @author:TRC
	* @Date:2019年7月30日 下午3:48:22
	 */
    public void modificationOperationPay(OperationPaybean operationPaybean) {
    	OperationPay operationPay=new OperationPay();
    	operationPay.setEmpInformation(iEmpInformationDao.findById(operationPaybean.getYgxh()).get());
    	operationPay.setOperPayDesc(operationPaybean.getOperPayDesc());
    	operationPay.setOperPayId(operationPaybean.getOperPayId());
    	operationPay.setOperPayName(operationPaybean.getOperPayName());
    	operationPay.setOperPayPrice(operationPaybean.getOperPayPrice());
    	operationPay.setOperPayTime(new Date());
    	iOperationPayDao.save(operationPay);
    }
    /**
     * 
    * @Title:getallOperationPays
    * @Description:TODO查询所有的手术收费项和数量
    * @param:@return
    * @return:List<OperationPay>
    * @throws
    * @author:TRC
    * @Date:2019年7月30日 下午3:50:50
     */
    public Map getallOperationPays(int curpage, int pagesize,String sou){
    	List<OperationPaybean> list=iOperationPayDao.getall_ope_pays(sou, PageRequest.of(curpage - 1,
		  pagesize));
    	  long total=iOperationPayDao.getcount(sou);
    	  Map map = new HashMap();
		  map.put("total", total);
		  map.put("list", list);
    	return map;
    }
    /**
     * 
    * @Title:delOperationPay
    * @Description:TODO删除指定手术收费项
    * @param:@param id
    * @return:void
    * @throws
    * @author:TRC
    * @Date:2019年7月30日 下午3:55:32
     */
    public void delOperationPay(String id) {
    	iOperationPayDao.deleteById(id);
    }
}
