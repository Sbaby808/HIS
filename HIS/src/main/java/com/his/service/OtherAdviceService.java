package com.his.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IOtherAdviceDao;
import com.his.pojo.OtherAdvice;
import com.his.pojo.SolveScheme;

/**  
* @ClassName: OtherAdviceService  
* @Description: 门诊常规建议Service
* @author Sbaby
* @date 2019年8月22日  下午5:42:50
*    
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class OtherAdviceService {

	@Autowired
	private IOtherAdviceDao otherAdviceDao;
	
	/**
	* @Title:addOtherAdvice
	* @Description:添加其他建议
	* @param:@param otherAdvice
	* @param:@return
	* @return:List<OtherAdvice>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月23日 上午10:33:05
	 */
	public List<OtherAdvice> addOtherAdvice(OtherAdvice otherAdvice) {
		otherAdvice.setOthAdvId(UUID.randomUUID().toString().replaceAll("-", ""));
		otherAdviceDao.save(otherAdvice);
		return this.findOtherAdvice(otherAdvice.getSolveScheme().getScheId());
	}
	
	/**
	* @Title:findOtherAdvice
	* @Description:根据医嘱查询所有其他建议
	* @param:@param solveScheme
	* @param:@return
	* @return:List<OtherAdvice>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月23日 上午10:32:34
	 */
	public List<OtherAdvice> findOtherAdvice(String solveSchemeId) {
		return otherAdviceDao.findAllOtherAdvice(solveSchemeId);
	}
	
	/**
	* @Title:findOtherAdviceByHistoryId
	* @Description:根据诊断记录编号查询常规医嘱
	* @param:@param historyId
	* @param:@return
	* @return:List<OtherAdvice>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月25日 下午1:01:28
	 */
	public List<OtherAdvice> findOtherAdviceByHistoryId(String historyId) {
		return otherAdviceDao.findOtherAdivceByHistoryId(historyId);
	}
	
	/**
	* @Title:delAdviceById
	* @Description:根据Id删除医嘱其他建议
	* @param:@param advId
	* @return:void
	* @throws
	* @author:Sbaby
	* @Date:2019年8月23日 上午10:56:58
	 */
	public List<OtherAdvice> delAdviceById(String advId) {
		OtherAdvice otherAdvice = otherAdviceDao.findById(advId).get();
		SolveScheme solveScheme = otherAdvice.getSolveScheme();
		otherAdviceDao.delete(otherAdvice);
		return this.findOtherAdvice(solveScheme.getScheId());
	}
}
