package com.his.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	* @Title:getAllPurchaseCheckByState
	* @Description:获取某一状态的采购验收
	* @param:@param state
	* @param:@param curPage
	* @param:@param pageSize
	* @param:@return
	* @return:Map
	* @throws
	* @author:crazy_long
	* @Date:2019年9月21日 上午1:04:12
	 */
	public Map getAllPurchaseCheckByState(String state,int curPage,int pageSize){
		Map map = new HashMap();
		List<PurchaseCheck> list = purchaseCheckDao.getAllPurchaseCheckByState(state, PageRequest.of(curPage-1, pageSize));
		int total = purchaseCheckDao.getAllPurchaseCheckByStateCount(state);
		map.put("list", list);
		map.put("total", total);
		return map;
	}
	
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
