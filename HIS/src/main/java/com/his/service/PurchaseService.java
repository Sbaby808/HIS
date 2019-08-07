package com.his.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IPurchaseDao;
import com.his.pojo.Purchase;
import com.his.utils.CreateUUID;
import com.his.utils.ServiceException;

/**  
* @ClassName: PurchaseService  
* @Description: 药品采购service
* @author crazy_long
* @date 2019年7月30日  下午2:13:29
*    
*/

@Service
@Transactional(rollbackFor=Exception.class)
public class PurchaseService {
	
	@Autowired
	private IPurchaseDao purchasedao;
	
	/**
	 * 
	* @Title:addPruchase
	* @Description:插入一个采购清单
	* @param:@param purchase
	* @param:@throws ServiceException
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年8月1日 下午4:40:19
	 */
	public void addPruchase(Purchase purchase) throws ServiceException{
		try {
			//生成主键id
			String pid = CreateUUID.getUUID_16();
			purchase.setCgId(pid);
			purchasedao.save(purchase);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("123");
		}
	}
	
	

}
