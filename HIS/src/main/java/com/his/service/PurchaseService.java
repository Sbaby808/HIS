package com.his.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IPurchaseDao;
import com.his.dao.IPurchaseDetailsDao;
import com.his.pojo.JsonResult;
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
	* @Title:addPurchaseX
	* @Description:生成一个采购计划单
	* @param:@param purchase
	* @param:@throws ServiceException
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年9月4日 上午12:02:09
	 */
	public void addPurchaseX(Purchase purchase) throws ServiceException{
		purchase.setCgId(UUID.randomUUID().toString().replace("-", ""));
		purchase.setState("否");
		try {
			//插入一个采购计划
			purchasedao.save(purchase);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("添加采购计划失败");
		}
	}
	
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
			purchase.setState("否");
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
	* @Title:getAllPurchase
	* @Description:查询所有未执行的采购计划
	* @param:@return
	* @return:List<Purchase>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月15日 上午11:40:15
	 */
	public List<Purchase> getAllPurchaseForNoDo() {
		return purchasedao.getAllForNoDo();
	}
	
	/**
	* @Title:getAllPurchaseForNo
	* @Description:分页获取一个未执行的采购计划
	* @param:@return
	* @return:List<Purchase>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月14日 上午10:01:55
	 */
	public Map getAllPurchaseForNo(int pageSize,int pageNum){
		Map map = new HashMap();
		List<Purchase> list = purchasedao.getAllForNo(PageRequest.of(pageNum - 1, pageSize));
		int total = purchasedao.getAllForNoCount();
		map.put("list", list);
		map.put("total", total);
		return map;
	}
	
	/**
	* @Title:getAllPurchaseForYes
	* @Description:分页获取一个已执行的采购计划
	* @param:@param pageSize
	* @param:@param pageNum
	* @param:@return
	* @return:Map
	* @throws
	* @author:crazy_long
	* @Date:2019年8月14日 下午2:55:15
	 */
	public Map getAllPurchaseForYes(int pageSize,int pageNum){
		Map map = new HashMap();
		List<Purchase> list = purchasedao.getAllForYes(PageRequest.of(pageNum - 1, pageSize));
		int total = purchasedao.getAllForYesCount();
		map.put("list", list);
		map.put("total", total);
		return map;
	}
	
	/**
	* @Title:delPurchaseByCascade
	* @Description:根据id删除采购计划及级联删除对应的明细
	* @param:@param cgId
	* @param:@throws ServiceException
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年8月14日 上午11:45:06
	 */
	public void delPurchaseByCascade(String cgId) throws ServiceException{
		try {
			purchasedao.deleteById(cgId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("删除失败采购计划失败");
		}
	}
	
	/**
	* @Title:updataForState
	* @Description:根据id修改状态
	* @param:@param cgId
	* @param:@throws ServiceException
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年8月14日 下午2:43:40
	 */
	public void updataForState(String cgId) throws ServiceException{
		try {
			Purchase purchase = purchasedao.findById(cgId).get();
			if (purchase != null) {
				purchase.setState("是");
			} 
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("修改状态失败");
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
