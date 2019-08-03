package com.his.service;

import java.util.List;

import org.hibernate.dialect.unique.DB2UniqueDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.his.dao.ICheckPayDao;
import com.his.pojo.CheckPay;
import com.sun.org.apache.regexp.internal.recompile;
import com.sun.org.apache.xerces.internal.dom.DeepNodeListImpl;

/**  
* @ClassName: CheckPayService  
* @Description: TODO(检查划价，缴费Service)  
* @author TRC
* @date 2019年7月30日  上午9:42:28
*    
*/
@Service
public class CheckPayService {
	@Autowired
	private ICheckPayDao iCheckPayDao;
	
/**
 * 
* @Title:AddCheckPay
* @Description:TODO添加检查收费项
* @param:@param checkPay
* @return:void
* @throws
* @author:TRC
* @Date:2019年7月30日 下午4:57:23
 */
	public void AddCheckPay(CheckPay checkPay) {
		iCheckPayDao.save(checkPay);
	}
	/**
	 * 
	* @Title:modificationCheckPay
	* @Description:TODO修改检查收费项
	* @param:@param checkPay
	* @return:void
	* @throws
	* @author:TRC
	* @Date:2019年7月30日 下午4:59:42
	 */
	public void modificationCheckPay(CheckPay checkPay) {
		iCheckPayDao.save(checkPay);
	}
	/**
	 * 
	* @Title:getallCheckPays
	* @Description:TODO查询所有检查收费项
	* @param:@return
	* @return:List<CheckPay>
	* @throws
	* @author:TRC
	* @Date:2019年7月30日 下午5:03:08
	 */
	public List<CheckPay> getallCheckPays(){
		return (List<CheckPay>) iCheckPayDao.findAll();
	}
	/**
	 * 
	* @Title:DelCheckPay
	* @Description:TODO删除指定检查收费项
	* @param:@param id
	* @return:void
	* @throws
	* @author:TRC
	* @Date:2019年7月30日 下午5:07:12
	 */
	public void DelCheckPay(String id) {
		iCheckPayDao.deleteById(id);
	}
}
