package com.his.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IPurchaseDao;
import com.his.dao.IPurchaseDetailsDao;
import com.his.pojo.Purchase;
import com.his.pojo.PurchaseDetail;
import com.his.pojo.PurchaseDetailPK;
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
	@Autowired
	private IPurchaseDetailsDao purchaseDetailsDao;
	
	/**
	* @Title:addPurchaseAndpurchaseDetail
	* @Description:添加一个采购计划和其明细
	* @param:@param purchaseDetail
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年8月13日 下午4:51:00
	 */
	public void addPurchaseAndpurchaseDetail(List<PurchaseDetail> purchaseDetail) throws ServiceException{
		//获取主表的信息
		Purchase purchase = purchaseDetail.get(0).getPurchase();
		//生成该采购计划的主键
		String cgId = UUID.randomUUID().toString().replace("-", "");
		purchase.setCgId(cgId);
		try {
			//插入一个采购计划
			purchasedao.save(purchase);
			//循环插入采购明细
			for (PurchaseDetail p : purchaseDetail) {
				if (p.getPurchase() != null) {
					p.setPurchase(null);
				}
				//建立联合主键
				PurchaseDetailPK purchaseDetailPK = new PurchaseDetailPK();
				purchaseDetailPK.setCgId(cgId);
				purchaseDetailPK.setYpId(p.getDrugInformation().getYpId());
				p.setId(purchaseDetailPK);
				purchaseDetailsDao.save(p);
			} 
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("添加采购计划及其明细失败");
		}
	}
	
	/**
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
