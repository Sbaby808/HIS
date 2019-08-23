package com.his.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IObservationNoticeDao;
import com.his.pojo.ObservationNotice;

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
		observationNoticeDao.save(observationNotice);
		return observationNotice;
	}
	
}
