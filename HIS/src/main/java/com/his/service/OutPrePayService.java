package com.his.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IOutPrePayDao;
import com.his.dao.IPrescriptionDao;
import com.his.pojo.Prescription;

/**  
* @ClassName: OutPrePayService  
* @Description: 门诊处方缴费记录Service
* @author Sbaby
* @date 2019年8月26日  上午11:51:30
*    
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class OutPrePayService {

	@Autowired
	private IOutPrePayDao outPrePayDao;
	@Autowired
	private IPrescriptionDao prescriptionDao;
	
	/**
	* @Title:getPriscriptionCountByCardId
	* @Description:根据就诊卡编号查询未缴费处方数量
	* @param:@param cardId
	* @param:@return
	* @return:List<Prescription>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月26日 下午2:09:26
	 */
	public int getPriscriptionCountByCardId(String cardId) {
		return prescriptionDao.getPriscriptionCountByCardId(cardId);
	}
	
	/**
	* @Title:getPriscriptionByCardId
	* @Description:根据就诊卡编号查询未缴费处方
	* @param:@param cardId
	* @param:@return
	* @return:List<Prescription>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月26日 下午2:47:19
	 */
	public List<Prescription> getPriscriptionByCardId(String cardId) {
		return prescriptionDao.getPriscriptionByCardId(cardId);
	}
	
}
