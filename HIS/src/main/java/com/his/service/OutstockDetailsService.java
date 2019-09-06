package com.his.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IDrugWarehouseDao;
import com.his.dao.IOutstockDetailsDao;
import com.his.dao.IReqDetailsDao;
import com.his.pojo.DrugWarehouse;
import com.his.pojo.OutstockDetail;
import com.his.pojo.OutstockDetailPK;
import com.his.pojo.ReqDetail;
import com.his.pojo.ReqDetailPK;
import com.his.utils.ServiceException;

/**  
* @ClassName: OutstockDetailsService  
* @Description: 药库出库明细
* @author crazy_long
* @date 2019年7月30日  下午12:17:04
*    
*/

@Service
@Transactional(rollbackFor=Exception.class)
public class OutstockDetailsService {
	
	@Autowired
	private IOutstockDetailsDao outstockDetailsDao;
	@Autowired
	private IDrugWarehouseDao drugWarehouseDao;
	@Autowired
	private IReqDetailsDao reqDetailsDao;
	
	/**
	* @Title:getOutstockDetailByReqId
	* @Description:根据申领id查找对应的明细
	* @param:@param reqId
	* @param:@return
	* @return:List<OutstockDetail>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月5日 下午5:48:17
	 */
	public List<OutstockDetail> getOutstockDetailByReqId(String reqId){
		return outstockDetailsDao.getOutstockDetailByReqId(reqId);
	}
	
	/**
	* @Title:addOutStockByOneDrug
	* @Description:批量插入药库出库明细
	* @param:@param outstockDetail
	* @param:@throws ServiceException
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年9月3日 上午9:41:06
	 */
	public void addOutStockByOneDrug(List<OutstockDetail> outstockDetail) throws ServiceException{
		
		try {
			for (OutstockDetail out : outstockDetail) {
				
				System.out.println("---------------------------------------");
				System.out.println("pckcid:"+out.getDrugWarehouse().getPckcId());
				System.out.println("ckid:"+out.getOutstock().getCkId());
				System.out.println("req_id:"+out.getOutstock().getReq_id());
				System.out.println("ypid:"+out.getDrugWarehouse().getDrugInformation().getYpId());
				System.out.println("number:"+out.getYkoutNum());
				
				OutstockDetail outsd = new OutstockDetail();
				OutstockDetailPK outstockDetailPK = new OutstockDetailPK();
				outstockDetailPK.setCkId(out.getOutstock().getCkId());
				outstockDetailPK.setPckcId(out.getDrugWarehouse().getPckcId());
				outsd.setId(outstockDetailPK);
				outsd.setYkoutNum(out.getYkoutNum());
				outsd.setState("已出库");
				//插入出库单明细
				outstockDetailsDao.save(outsd);
				//修改库存
				BigDecimal outNumber = out.getYkoutNum();
				String kcId = out.getDrugWarehouse().getPckcId();
				DrugWarehouse drugWarehouse =  drugWarehouseDao.findById(kcId).get();
				drugWarehouse.setNowNumber(drugWarehouse.getNowNumber().subtract(outNumber));
				//修改申领单明细状态
				ReqDetailPK reqDetailPK = new ReqDetailPK();
				reqDetailPK.setReqId(out.getOutstock().getReq_id());
				reqDetailPK.setYpId(out.getDrugWarehouse().getDrugInformation().getYpId());
				ReqDetail reqDetail =  reqDetailsDao.findById(reqDetailPK).get();
				reqDetail.setState("已处理");
			} 
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("插入明细失败");
		}
	}

}
