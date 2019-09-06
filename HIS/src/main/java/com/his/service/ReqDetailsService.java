package com.his.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IOutpatientRequestionMedicineDao;
import com.his.dao.IReqDetailsDao;
import com.his.pojo.OutpatientRequestionMedicine;
import com.his.pojo.ReqDetail;
import com.his.pojo.ReqDetailPK;
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
	@Autowired
	private IOutpatientRequestionMedicineDao outpatientRequestionMedicineDao;
	
	/**
	* @Title:IsHaveNoDo
	* @Description:查看是否有没有未处理的药品
	* @param:@param reqId
	* @param:@return
	* @return:boolean
	* @throws
	* @author:crazy_long
	* @Date:2019年9月3日 下午3:52:45
	 */
	public boolean IsHaveNoDo(String reqId) {
		int count = reqDetailsDao.isHaveNoOut(reqId);
		if(count>0){
			return true;
		}else {
			return false;
		}
	}
	
	/**
	* @Title:qeuryRequestDetail
	* @Description:根据申领单号查找对应的明细
	* @param:@param reqId
	* @param:@return
	* @return:List<ReqDetail>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月29日 下午7:42:22
	 */
	public List<ReqDetail> qeuryRequestDetail(String reqId){
		return reqDetailsDao.qeuryRequestDetail(reqId);
	}
	
	/**
	* @Title:qeuryForNo
	* @Description:查找未申领中的药品
	* @param:@param curPage
	* @param:@param pageSize
	* @param:@return
	* @return:List<ReqDetail>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月29日 上午12:19:19
	 */
	public Map qeuryForNoRequest(int curPage,int pageSize){
		Map map = new HashMap();
		List<ReqDetail> list = null;
		int total = 0;
		try {
			list = reqDetailsDao.queryForNoRequest(PageRequest.of(curPage - 1, pageSize));
			total = reqDetailsDao.queryForNoRequestCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("list", list);
		map.put("total", total);
		return map;
	}
	
	/**
	* @Title:qeuryForNo
	* @Description:查找已申领的药品
	* @param:@param curPage
	* @param:@param pageSize
	* @param:@return
	* @return:List<ReqDetail>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月29日 上午12:20:36
	 */
	public Map qeuryForYesRequest(int curPage,int pageSize){
		Map map = new HashMap();
		List<ReqDetail> list = null;
		int total = 0;
		try {
			list = reqDetailsDao.queryForYesRequest(PageRequest.of(curPage - 1, pageSize));
			total = reqDetailsDao.queryForYesRequestCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("list", list);
		map.put("total", total);
		return map;

	}
	
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
	public void addRequestMedicine(List<ReqDetail> reqDetails) throws ServiceException {
		
		//取出第一个的明细表信息
		OutpatientRequestionMedicine orm = reqDetails.get(0).getOutpatientRequestionMedicine();
		String reqId = UUID.randomUUID().toString().replace("-", "");
		orm.setReqId(reqId);
		orm.setReqStatus("未申领");
		try {
			outpatientRequestionMedicineDao.save(orm);
			//循环添加明细
			for (int i = 1; i < reqDetails.size(); i++) {
				System.out.println("--------------------");
				System.out.println("reqId:"+reqId);
				System.out.println("ypid:"+reqDetails.get(i).getDrugInformation().getYpId());
				System.out.println("number:"+reqDetails.get(i).getReqNum());
				ReqDetail reqDetail = reqDetails.get(i);
				ReqDetailPK reqDetailPK = new ReqDetailPK();
				reqDetailPK.setReqId(reqId);
				reqDetailPK.setYpId(reqDetails.get(i).getDrugInformation().getYpId());
				reqDetail.setId(reqDetailPK);
				reqDetail.setState("未处理");
				reqDetailsDao.save(reqDetail);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("添加失败");
		}
		
	}

}
