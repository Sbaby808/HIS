package com.his.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IHosDrugCostDao;
import com.his.dao.IHosPreDetailDao;
import com.his.dao.IHosPrescriptionDao;
import com.his.pojo.HosDrugCost;
import com.his.pojo.HosPrescription;
import com.his.pojo.HosPrescriptionDetail;
import com.his.pojo.HosPrescriptionDetailPK;

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
	@Autowired
	private IHosPreDetailDao hosPreDetailDao;
	@Autowired
	private IHosDrugCostDao hosDrugCostDao;
	
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
	
	/**
	 * 
	* @Title:addHosPrescription
	* @Description:新增处方和处方明细
	* @param:@param hosPrescription
	* @param:@throws ParseException
	* @return:void
	* @throws
	* @author:Hamster
	* @Date:2019年8月8日 下午8:03:41
	 */
	public void addHosPrescription(HosPrescription hosPrescription) throws ParseException{
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = dateFormat.format(new Date());
		
		HosDrugCost hosDrugCost = new HosDrugCost();
		hosDrugCost.setHosPrescription(hosPrescription);
		hosDrugCost.setHostCtime(dateFormat.parse(time));
		hosDrugCostDao.save(hosDrugCost);
		
		hosPrescription.setHosCid(hosDrugCost.getHosCid());
		hosPrescription.setHosPreTime(dateFormat.parse(time));
		hosPrescriptionDao.save(hosPrescription);
		
		
		List <HosPrescriptionDetail> details = hosPrescription.getHosPrescriptionDetails();
		for(int i=0;i<details.size();i++){
			HosPrescriptionDetailPK pk = new HosPrescriptionDetailPK();
			pk.setHosPreId(hosPrescription.getHosPreId());	
			pk.setYpId(details.get(i).getDrugInformation().getYpId());		
			details.get(i).setId(pk);
			hosPreDetailDao.save(details.get(i));
		}
		
	}
}
