package com.his.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IPurchaseDetailsDao;
import com.his.pojo.Purchase;
import com.his.pojo.PurchaseDetail;
import com.his.pojo.PurchaseDetailPK;
import com.his.utils.ServiceException;

/**  
* @ClassName: PurchaseDetailsService  
* @Description:药品采购明细Service
* @author crazy_long
* @date 2019年7月30日  下午2:14:29
*    
*/

@Service
@Transactional(rollbackFor=Exception.class)
public class PurchaseDetailsService {
	
	@Autowired
	private IPurchaseDetailsDao purchaseDetailsDao;
	
	/**
	* @Title:addPurchaseAndpurchaseDetail
	* @Description:往已经存在的采购计划中添加药品
	* @param:@param purchaseDetail
	* @param:@throws ServiceException
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年8月14日 下午10:10:59
	 */
	public void appendToDetail(List<PurchaseDetail> purchaseDetail) throws ServiceException{
		//获取采购单id
		Purchase purchase = purchaseDetail.get(0).getPurchase();
		String cgId = purchase.getCgId();
		try {
			//循环插入采购明细
			for (PurchaseDetail p : purchaseDetail) {
				//建立联合主键
				PurchaseDetailPK purchaseDetailPK = new PurchaseDetailPK();
				purchaseDetailPK.setCgId(cgId);
				purchaseDetailPK.setYpId(p.getDrugInformation().getYpId());
				p.setId(purchaseDetailPK);
				purchaseDetailsDao.save(p);
			} 
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("追加采购计划明细失败");
		}
	}
	
	/**
	* @Title:getAllPurCheDetailsByCgid
	* @Description:根据采购id查找对应的明细
	* @param:@param cgid
	* @param:@return
	* @return:List<PurchaseDetail>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月14日 下午3:34:28
	 */
	public List<PurchaseDetail> getAllPurCheDetailsByCgid(String cgid){
		return purchaseDetailsDao.getForCgid(cgid);
	}
	
	/**
	* @Title:getDetailByCgidForPage
	* @Description:根据采购id分页查找对应的明细
	* @param:@param curPage
	* @param:@param pageSize
	* @param:@param cgId
	* @param:@return
	* @return:Map
	* @throws
	* @author:crazy_long
	* @Date:2019年8月15日 下午5:42:32
	 */
	public Map getDetailByCgidForPage(int curPage,int pageSize,String cgId) {
		Map map = new HashMap();
		List<PurchaseDetail> list = purchaseDetailsDao.getForCgidByPage(cgId,PageRequest.of(curPage - 1, pageSize));
		int total = purchaseDetailsDao.getForCgidByPageCount(cgId);
		map.put("list", list);
		map.put("total", total);
		return map;
	}
	
	/**
	* @Title:delOneDetail
	* @Description:删除一个采购明细
	* @param:@param ypId
	* @param:@param cgId
	* @param:@throws ServiceException
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年8月14日 下午4:58:07
	 */
	public void delOneDetail(String ypId,String cgId) throws ServiceException{
		PurchaseDetailPK purchaseDetailPK = new PurchaseDetailPK();
		purchaseDetailPK.setCgId(cgId);
		purchaseDetailPK.setYpId(ypId);
		try {
			purchaseDetailsDao.deleteById(purchaseDetailPK);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("删除采购明细失败");
		}
	}
	
	/**
	* @Title:delOneDetail
	* @Description:修改某一个采购明细的数量
	* @param:@param ypId
	* @param:@param cgId
	* @param:@throws ServiceException
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年8月14日 下午4:58:07
	 */
	public void updateOneDetailNumber(String ypId,String cgId,int purchaseNumber) throws ServiceException{
		PurchaseDetailPK purchaseDetailPK = new PurchaseDetailPK();
		purchaseDetailPK.setCgId(cgId);
		purchaseDetailPK.setYpId(ypId);
		try {
			PurchaseDetail purchaseDetail = purchaseDetailsDao.findById(purchaseDetailPK).get();
			if(purchaseDetail!=null) {
				purchaseDetail.setPurchaseNum(new BigDecimal(purchaseNumber));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("修改采购明细数量失败");
		}
	}

}
