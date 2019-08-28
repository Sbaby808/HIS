package com.his.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IReqDetailsDao;
import com.his.pojo.OutpatientRequestionMedicine;
import com.his.pojo.ReqDetail;
import com.his.utils.ServiceException;

/**  
* @ClassName: ReqDetailsService  
* @Description: 药品申领明细service
* @author crazy_long
* @date 2019年7月30日  下午2:20:10
*    
*/
@Service
@Transactional(rollbackFor=Exception.class)
public class ReqDetailsService {
	
	@Autowired
	private IReqDetailsDao reqDetailsDao;
	
	/**
	* @Title:addRequestMedicine
	* @Description:添加药品申请及其明细
	* @param:@param ReqDetails
	* @param:@throws ServiceException
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年8月28日 下午6:27:21
	 */
	public void addRequestMedicine(List<ReqDetail> ReqDetails) throws ServiceException {
		
		//取出第一个的明细信息
		//OutpatientRequestionMedicine
	}

}
