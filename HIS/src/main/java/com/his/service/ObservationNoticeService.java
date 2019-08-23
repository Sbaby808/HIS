package com.his.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IObservationNoticeDao;
import com.his.dao.ISolveSchemeDao;
import com.his.pojo.ObservationNotice;
import com.his.pojo.SolveScheme;

/**  
* @ClassName: ObservationNoticeService  
* @Description: 留观通知Service
* @author Sbaby
* @date 2019年8月23日  下午2:41:48
*    
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class ObservationNoticeService {

	@Autowired
	private IObservationNoticeDao observationNoticeDao;
	@Autowired
	private ISolveSchemeDao schemeDao;
	
	/**
	* @Title:addObs
	* @Description:添加或编辑留观通知
	* @param:@param observationNotice
	* @param:@return
	* @return:ObservationNotice
	* @throws
	* @author:Sbaby
	* @Date:2019年8月23日 下午2:47:12
	 */
	public ObservationNotice addObs(ObservationNotice observationNotice) {
		if(observationNotice.getObservaId() == null) {
			observationNotice.setObservaId(UUID.randomUUID().toString().replaceAll("-", ""));
		}
		SolveScheme scheme = schemeDao.findById(observationNotice.getSolveScheme().getScheId()).get();
		observationNotice.setSolveScheme(scheme);
		observationNoticeDao.save(observationNotice);
		return observationNotice;
	}
	
	/**
	* @Title:delObsById
	* @Description:根据id删除留观建议
	* @param:@param id
	* @return:void
	* @throws
	* @author:Sbaby
	* @Date:2019年8月23日 下午4:57:59
	 */
	public void delObsById(String id) {
		observationNoticeDao.deleteById(id);
	}

	/**
	* @Title:findBySolveId
	* @Description:根据医嘱查询留观通知单
	* @param:@param solveId
	* @param:@return
	* @return:ObservationNotice
	* @throws
	* @author:Sbaby
	* @Date:2019年8月23日 下午5:08:44
	 */
	public ObservationNotice findBySolveId(String solveId) {
		return observationNoticeDao.getBySolveId(solveId);
	}
	
}
