package com.his.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	 * @throws ParseException 
	* @throws
	* @author:Hamster
	* @Date:2019年8月14日 下午5:54:46
	 */
	public Map getHosDrugDetailbyPage(String start,String end,String cardName,String ksName,String roomName,int curpage,int pagesize) throws ParseException{
		List <HosDrugDetail> list;
		long total;
		if(start==null||end==null){
			list = hosDrugDetailDao.getHosDrugDetailbyPage(cardName, ksName, roomName, PageRequest.of(curpage-1, pagesize));
			total = hosDrugDetailDao.countNum1(cardName, ksName, roomName);
		}
		else{
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			list = hosDrugDetailDao.getHosDrugDetailbyPageandTime(format.parse(start), format.parse(end), cardName, ksName, roomName, PageRequest.of(curpage-1, pagesize));
			total = hosDrugDetailDao.countNum2(format.parse(start), format.parse(end), cardName, ksName, roomName);
		}
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
