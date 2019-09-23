package com.his.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IEmpInformationDao;
import com.his.dao.IPurCheDetailsDao;
import com.his.dao.IPurchaseCheckDao;
import com.his.pojo.EmpInformation;
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
	@Autowired
	private IEmpInformationDao empInformationDao;
	
	/**
	* @Title:getAnCheckAndDetialByPurchaId
	* @Description:分页获取采购验收和明细
	* @param:@param checkId
	* @param:@param curPage
	* @param:@param pageSize
	* @param:@return
	* @return:Map
	* @throws
	* @author:crazy_long
	* @Date:2019年9月21日 上午1:22:05
	 */
	public Map getAnCheckAndDetialByPurchaId(String checkId,int curPage,int pageSize) {
		List<PurCheDetail> list = purCheDetailsDao.getAnCheckAndDetialByPurchaId(checkId, PageRequest.of(curPage-1, pageSize));
		int total = purCheDetailsDao.getAnCheckAndDetialCount(checkId);
		Map map = new HashMap();
		map.put("list", list);
		map.put("total", total);
		return map;
	}
	
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
		try {
			//先获取主表信息
			PurchaseCheck purchaseCheck = new PurchaseCheck();
			String ygxh = purCheDetail.get(0).getPurchaseCheck().getEmpInformation().getYgxh();
			EmpInformation emp = empInformationDao.findById(ygxh).get();
			//设定主键
			String puichaid = UUID.randomUUID().toString().replace("-", "");
			purchaseCheck.setPurChaId(puichaid);
			purchaseCheck.setEmpInformation(emp);
			purchaseCheck.setPurChaTime(new Date());
			purchaseCheck.setState("否");
			//保存采购验收主表
			purchaseCheckDao.save(purchaseCheck);
			//循环把验收明细插入进去
			for (PurCheDetail purChe : purCheDetail) {
				PurCheDetail purCheDet = new PurCheDetail();
				//建立联合主键
				PurCheDetailPK purCheDetailPK = new PurCheDetailPK();
				purCheDetailPK.setPurChaId(puichaid);
				purCheDetailPK.setYpId(purChe.getDrugInformation().getYpId());
				purCheDet.setId(purCheDetailPK);
				purCheDet.setPurCheDetid(purChe.getPurCheDetid());
				purCheDet.setPruCheDetreason(purChe.getPruCheDetreason());
				purCheDet.setPurCheDetdeal(purChe.getPurCheDetdeal());
				purCheDet.setPruCheDetprod(purChe.getPruCheDetprod());
				purCheDetailsDao.save(purCheDet);
			} 
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("插入采购验收明细失败");
		}
	} 

}
