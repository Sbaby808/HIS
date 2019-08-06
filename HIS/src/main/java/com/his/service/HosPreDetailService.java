package com.his.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IHosPreDetailDao;
import com.his.pojo.HosPrescriptionDetail;

/**
 * 
* @ClassName: HosPreDetailService  
* @Description:住院处方明细 
* @author Hamster
* @date 2019年8月6日  上午10:49:34
*
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class HosPreDetailService {
	
	@Autowired
	private IHosPreDetailDao hosPreDetailDao;
	
	/**
	 * 
	* @Title:getHosPreDetailByPage
	* @Description:分页查询
	* @param:@param curpage
	* @param:@param pagesize
	* @param:@return
	* @return:Map
	* @throws
	* @author:Hamster
	* @Date:2019年8月6日 上午10:50:34
	 */
	public Map getHosPreDetailByPage(int curpage,int pagesize){
		List <HosPrescriptionDetail> list = hosPreDetailDao.getHosPreDetailsByPage(PageRequest.of(curpage-1, pagesize));
		long total = hosPreDetailDao.count();
		Map map = new HashMap<>();
		map.put("list", list);
		map.put("total", total);
		return map;
	}
	
	/**
	 * 
	* @Title:getHosPreDetail
	* @Description:无分页查询
	* @param:@return
	* @return:List<HosPrescriptionDetail>
	* @throws
	* @author:Hamster
	* @Date:2019年8月6日 上午10:51:20
	 */
	public List <HosPrescriptionDetail> getHosPreDetail(){
		return hosPreDetailDao.getHosPreDetail();
	}
	
	/**
	 * 
	* @Title:getHosPreDetailByPid
	* @Description:根据处方id获取处方明细
	* @param:@param pid
	* @param:@return
	* @return:List<HosPrescriptionDetail>
	* @throws
	* @author:Hamster
	* @Date:2019年8月6日 上午11:31:08
	 */
	public List <HosPrescriptionDetail> getHosPreDetailByPid(String pid){
		return hosPreDetailDao.getHosPreDetailByPid(pid);
	}
}
