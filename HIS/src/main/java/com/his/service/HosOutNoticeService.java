package com.his.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
	public Map getHosOutNoticeByPage(int curpage,int pagesize){
		List <HosOutNotice> list = hosOutNoticeDao.getHosOutNoticeByPage(PageRequest.of(curpage-1, pagesize));
		long total = hosOutNoticeDao.count();
		Map map = new HashMap<>();
		map.put("list", list);
		map.put("total", total);
		return map;
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
