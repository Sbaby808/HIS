package com.his.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IHosDrugDetailDao;
import com.his.dao.IHosPreDetailDao;
import com.his.dao.IHosPrescriptionDao;
import com.his.pojo.HosDrugDetail;
import com.his.pojo.HosPrescription;
import com.his.pojo.HosPrescriptionDetail;

/**
 * 
* @ClassName: HosDrugDetailService  
* @Description: 住院用药明细  
* @author Hamster
* @date 2019年8月21日  上午9:27:41
*
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class HosDrugDetailService {

	@Autowired
	private IHosDrugDetailDao hosDrugDetailDao;
	@Autowired
	private IHosPreDetailDao hosPreDetailDao;
	@Autowired
	private IHosPrescriptionDao hosPrescriptionDao;
	
	/**
	 * 
	* @Title:getHosDrugDetail
	* @Description:查询用药明细
	* @param:@return
	* @return:List<HosDrugDetail>
	* @throws
	* @author:Hamster
	* @Date:2019年8月14日 下午5:54:46
	 */
	public Map getHosDrugDetailbyPage(String cardName,int curpage,int pagesize){
		List <HosDrugDetail> list = hosDrugDetailDao.getHosDrugDetailbyPage(cardName,PageRequest.of(curpage-1, pagesize));
		long total = hosDrugDetailDao.countInDrugDetail();
		Map map = new HashMap<>();
		map.put("list", list);
		map.put("total", total);
		return map;
	}
	
	/**
	 * 
	* @Title:getHosDrugDetailbyDiagId
	* @Description:根据诊断记录id查询用药明细
	* @param:@param diagId
	* @param:@return
	* @return:List<HosDrugDetail>
	* @throws
	* @author:Hamster
	* @Date:2019年8月24日 下午4:50:30
	 */
	public List <HosDrugDetail> getHosDrugDetailbyDiagId(String diagId){
		return hosDrugDetailDao.getHosDrugDetailbyDiagId(diagId);
	}
	

	
}
