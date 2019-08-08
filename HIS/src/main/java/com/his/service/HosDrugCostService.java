package com.his.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IHosDrugCostDao;
import com.his.pojo.HosDrugCost;
/**
 * 
* @ClassName: HosDrugCostService  
* @Description: 住院扣费记录 
* @author Hamster
* @date 2019年8月7日  下午7:48:47
*
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class HosDrugCostService {

	@Autowired
	private IHosDrugCostDao hosDrugCostDao;
	/**
	 * 
	* @Title:getAllDrugCostsByPage
	* @Description:分页查询
	* @param:@param curpage
	* @param:@param pagesize
	* @param:@return
	* @return:Map
	* @throws
	* @author:Hamster
	* @Date:2019年8月7日 下午7:49:01
	 */
	public Map getAllDrugCostsByPage(int curpage,int pagesize){
		List <HosDrugCost> list = hosDrugCostDao.getAllDrugCostsByPage(PageRequest.of(curpage-1, pagesize));
		long total = hosDrugCostDao.count();
		Map map = new HashMap<>();
		map.put("list", list);
		map.put("total", total);
		return map;
	}
	
	/**
	 * 
	* @Title:getHosDrugCost
	* @Description:无分页
	* @param:@return
	* @return:List<HosDrugCost>
	* @throws
	* @author:Hamster
	* @Date:2019年8月7日 下午7:49:46
	 */
	public List <HosDrugCost> getHosDrugCost(){
		return hosDrugCostDao.getAllDrugCosts();
	}
}
