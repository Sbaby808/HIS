package com.his.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IHosDrugDetailDao;
import com.his.dao.IHosDrugRecordDao;
import com.his.dao.IHosPreDetailDao;
import com.his.dao.IHosPrescriptionDao;
import com.his.pojo.HosDrugDetail;
import com.his.pojo.HosDrugRecord;
import com.his.pojo.HosPrescription;
import com.his.pojo.HosPrescriptionDetail;
import com.his.pojo.LastDrugDetail;

import oracle.net.aso.i;


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
	@Autowired
	private IHosPrescriptionDao hosPrescriptionDao;
	@Autowired
	private IHosDrugRecordDao hosDrugRecordDao;
	@Autowired
	private IHosDrugDetailDao hosDrugDetailDao;
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
	
	/**
	 * 
	* @Title:getHosPreDetailByDiagId
	* @Description:根据诊断记录id获取处方明细(此处明细集合已减去用药明细)
	* @param:@param diagId
	* @param:@return
	* @return:List<HosPrescriptionDetail>
	* @throws
	* @author:Hamster
	* @Date:2019年8月14日 上午11:34:21
	 */
	public List <LastDrugDetail>  getHosPreDetailByDiagId(String diagId){
		List <LastDrugDetail>  list = new ArrayList<LastDrugDetail>();
		HosPrescription prescription = hosPrescriptionDao.getHosPresByDiagId(diagId);
		List <HosPrescriptionDetail> details = hosPreDetailDao.getHosPreDetailByPid(prescription.getHosPreId());		
		List <HosDrugRecord> records = prescription.getHosDrugRecords();
		
		for(int i=0;i<details.size();i++){
			list.add(new LastDrugDetail(details.get(i).getHosPreDnum(),details.get(i).getHosPreDunit(),
					details.get(i).getDrugInformation(),details.get(i).getHosPrescription()));
		}
		
		List <HosDrugDetail> drugDetails = hosDrugDetailDao.getHosDrugDetailBypreId(prescription.getHosPreId());
		
		Map <String,BigDecimal> map = new HashMap<String,BigDecimal>();
			
		for(HosDrugDetail h:drugDetails){
			if(map.get(h.getDrugInformation().getYpId())==null){
				map.put(h.getDrugInformation().getYpId(), h.getDrugUseNum());
			}
			else{
				map.put(h.getDrugInformation().getYpId(),h.getDrugUseNum().add(map.get(h.getDrugInformation().getYpId())));
			}
		}
		
		
		Set <String> set =map.keySet();
		
		for(int i=0;i<list.size();i++){
			for(String key:set){
				if(list.get(i).getDrugInformation().getYpId().equals(key)){
					BigDecimal usedNum = map.get(key);
					BigDecimal last = list.get(i).getHosPreDnum().subtract(usedNum);
					list.get(i).setHosPreDnum(last);
				}
			}
		}
		
		return list;
		
	}
}
