package com.his.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IHosInNoticeDao;
import com.his.pojo.HospitalNotice;

/**
 * 
* @ClassName: HosInNoticeService  
* @Description: TODO住院通知记录 
* @author Hamster
* @date 2019年8月1日  上午11:08:25
*
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class HosInNoticeService {

	@Autowired
	private IHosInNoticeDao hosInNoticeDao;
	
	/**
	 * 
	* @Title:getAllHosInNotice
	* @Description:查询所有通知单
	* @param:@return
	* @return:List<HospitalNotice>
	* @throws
	* @author:Hamster
	* @Date:2019年8月1日 下午8:00:36
	 */
	public List <HospitalNotice> getAllHosInNotice(){
		return hosInNoticeDao.getAllHosInNotice();
	}
	
}
