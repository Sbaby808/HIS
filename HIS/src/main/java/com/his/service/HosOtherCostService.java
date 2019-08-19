package com.his.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IHosOtherCostDao;
import com.his.pojo.HosOtherCost;
import com.his.pojo.HosOutNotice;
/**
 * 
* @ClassName: HosOtherCostService  
* @Description: 住院其他扣费记录  
* @author Hamster
* @date 2019年8月17日  上午11:03:50
*
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class HosOtherCostService {

	@Autowired
	private IHosOtherCostDao hosOtherCostDao;
	
	/**
	 * 
	* @Title:getAllOtherCostByPage
	* @Description:分页查询
	* @param:@param curpage
	* @param:@param pagesize
	* @param:@return
	* @return:Map
	* @throws
	* @author:Hamster
	* @Date:2019年8月17日 上午11:05:06
	 */
	public Map getAllOtherCostByPage(int curpage,int pagesize){
		List <HosOtherCost> list = hosOtherCostDao.getAllOtherCostByPage(PageRequest.of(curpage-1, pagesize));
		long total = hosOtherCostDao.count();
		Map map = new HashMap<>();
		map.put("list", list);
		map.put("total", total);
		return map;
	}
	
	/**
	 * 
	* @Title:getHosOtherCost
	* @Description:无分页查询
	* @param:@return
	* @return:List<HosOtherCost>
	* @throws
	* @author:Hamster
	* @Date:2019年8月17日 上午11:04:52
	 */
	public List <HosOtherCost> getHosOtherCost(){
		return hosOtherCostDao.getHosOtherCost();
	}
	
	public List <HosOtherCost> getAllHosOtherCosts(HosOutNotice outNotice){
		String recordId = outNotice.getHosDoctorAdvice().getHosDiagnosticRecord().getMedicalRecord().getMedRid();
		List <HosOtherCost> list = hosOtherCostDao.getAllOtherCostbyRid(recordId);
		return list;
	}
}
