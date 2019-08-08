package com.his.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.his.dao.IHosPrescriptionDao;
import com.his.pojo.HosPrescription;

/**
 * 
* @ClassName: HosPrescriptionService  
* @Description: 住院处方  
* @author Hamster
* @date 2019年8月5日  下午7:21:36
*
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class HosPrescriptionService {
	
	@Autowired
	private IHosPrescriptionDao hosPrescriptionDao;
	
	/**
	 * 
	* @Title:getAllHosPrescription
	* @Description:分页查询所有处方
	* @param:@param curpage
	* @param:@param pagesize
	* @param:@return
	* @return:Map
	* @throws
	* @author:Hamster
	* @Date:2019年8月5日 下午7:21:52
	 */
	public Map getHosPrescriptionByPage(int curpage,int pagesize){
		List <HosPrescription> list =hosPrescriptionDao.getHosPrescriptionByPage(PageRequest.of(curpage-1, pagesize));
		long total = hosPrescriptionDao.count();
		Map map = new HashMap<>();
		map.put("list", list);
		map.put("total", total);
		return map;
	}
	
	/**
	 * 
	* @Title:getAllHosPrescription
	* @Description:无分页查询所有处方
	* @param:@return
	* @return:List<HosPrescription>
	* @throws
	* @author:Hamster
	* @Date:2019年8月5日 下午7:22:57
	 */
	public List <HosPrescription> getAllHosPrescription(){
		return hosPrescriptionDao.getAllHosPrescription();
	}
	
	/**
	 * 
	* @Title:getHosPresByDiagId
	* @Description:根据诊断记录id查询处方
	* @param:@param diagId
	* @param:@return
	* @return:HosPrescription
	* @throws
	* @author:Hamster
	* @Date:2019年8月7日 下午9:37:23
	 */
	public HosPrescription getHosPresByDiagId(String diagId){
		return hosPrescriptionDao.getHosPresByDiagId(diagId);
	}
}
