package com.his.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IHosOutNoticeDao;
import com.his.pojo.HosOutNotice;

@Service
@Transactional(rollbackFor=Exception.class)
public class HosOutNoticeService {

	@Autowired
	private IHosOutNoticeDao hosOutNoticeDao;
	
	/**
	 * 
	* @Title:getAllHosOutNotic
	* @Description:查询所有出院通知单
	* @param:@return
	* @return:List<HosOutNotice>
	* @throws
	* @author:Hamster
	* @Date:2019年8月3日 上午11:32:51
	 */
	public List <HosOutNotice> getAllHosOutNotic(){
		return hosOutNoticeDao.getAllHosOutNotic();
	}
	
	/**
	 * 
	* @Title:delHosOutNotice
	* @Description:删除出院通知单
	* @param:@param outNotice
	* @return:void
	* @throws
	* @author:Hamster
	* @Date:2019年8月4日 上午9:57:09
	 */
	public void delHosOutNotice(HosOutNotice outNotice){
		hosOutNoticeDao.delete(outNotice);
	}
}
