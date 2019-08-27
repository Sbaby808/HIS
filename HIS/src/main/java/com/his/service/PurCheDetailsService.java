package com.his.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IPurCheDetailsDao;
import com.his.dao.IPurchaseCheckDao;
import com.his.pojo.DrugInformation;
import com.his.pojo.PurCheDetail;
import com.his.pojo.PurCheDetailPK;
import com.his.pojo.PurchaseCheck;
import com.his.utils.ServiceException;

/**  
* @ClassName: PurCheDetailsService  
* @Description: 采购验收明细service
* @author crazy_long
* @date 2019年7月30日  下午2:15:51
*    
*/

@Service
@Transactional(rollbackFor=Exception.class)
public class PurCheDetailsService {
	
	@Autowired
	private IPurchaseCheckDao purchaseCheckDao;
	@Autowired
	private IPurCheDetailsDao purCheDetailsDao;
	
	
	/**
	* @Title:getOnePurchaseCheckNoputById
	* @Description:根据id分页获取未入库的采购验收及其明细
	* @param:@param curPage
	* @param:@param pageSize
	* @param:@param checkId
	* @param:@return
	* @return:Map
	* @throws
	* @author:crazy_long
	* @Date:2019年8月17日 上午9:39:08
	 */
	public Map getOnePurchaseCheckNoputById(int curPage,int pageSize,String checkId) {
		List<PurCheDetail> list = purCheDetailsDao.getOneCheckAndDetial(checkId, PageRequest.of(curPage-1, pageSize));
		int total = purCheDetailsDao.getOneCheckNoputCount(checkId);
		Map map = new HashMap();
		map.put("list", list);
		map.put("total", total);
		return map;
	}
	
	/**
	* @Title:addPurcheDetail
	* @Description:批量添加一个采购验收明细
	* @param:@param purCheDetail
	* @param:@throws ServiceException
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年8月16日 下午12:15:41
	 */
	public void addPurcheDetail(List<PurCheDetail> purCheDetail) throws ServiceException{
		//先获取主表信息
		PurchaseCheck purchaseCheck = purCheDetail.get(0).getPurchaseCheck();
		//设定主键
		String puichaid = UUID.randomUUID().toString().replace("-", "");
		purchaseCheck.setPurChaId(puichaid);
		try {
			//保存采购验收主表
			purchaseCheckDao.save(purchaseCheck);
			//循环把验收明细插入进去
			for (PurCheDetail purChe : purCheDetail) {
				//建立联合主键
				PurCheDetailPK purCheDetailPK = new PurCheDetailPK();
				purCheDetailPK.setPurChaId(puichaid);
				purCheDetailPK.setYpId(purChe.getDrugInformation().getYpId());
				purChe.setId(purCheDetailPK);
				purCheDetailsDao.save(purChe);
			} 
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("插入采购验收明细失败");
		}
	} 

}
