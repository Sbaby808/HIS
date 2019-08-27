package com.his.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IPurchaseCheckDao;
import com.his.pojo.PurchaseCheck;
import com.his.utils.ServiceException;

/**  
* @ClassName: PurchaseChecService  
* @Description: 采购验收service
* @author crazy_long
* @date 2019年7月30日  下午2:12:16
*    
*/

@Service
@Transactional(rollbackFor=Exception.class)
public class PurchaseChecService {
	
	@Autowired
	private IPurchaseCheckDao purchaseCheckDao; 
	
	/**
	* @Title:updatePurchaseCheckState
	* @Description:根据id采购验收修改状态
	* @param:@param Checkid
	* @param:@throws ServiceException
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年8月17日 下午6:06:27
	 */
	public void updatePurchaseCheckState(String Checkid) throws ServiceException{
		try {
			PurchaseCheck purchaseCheck = purchaseCheckDao.findById(Checkid).get();
			purchaseCheck.setState("是");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("修改状态失败");
		}
	}
	
	/**
	* @Title:getAllNoPut
	* @Description:获取未入库的采购验收
	* @param:@return
	* @return:List<PurchaseCheck>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月17日 上午10:34:04
	 */
	public List<PurchaseCheck> getAllNoPut(){
		return purchaseCheckDao.getAllForNoPut();
	}
	
}
