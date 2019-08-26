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

import com.his.dao.IHosDrugDetailDao;
import com.his.dao.IHosDrugRecordDao;
import com.his.pojo.HosDrugDetail;
import com.his.pojo.HosDrugDetailPK;
import com.his.pojo.HosDrugRecord;

/**
 * 
* @ClassName: HosDrugRecordService  
* @Description: 住院用药记录  
* @author Hamster
* @date 2019年8月13日  上午10:26:22
*
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class HosDrugRecordService {

	@Autowired
	private IHosDrugRecordDao hosDrugRecordDao;
	@Autowired
	private IHosDrugDetailDao hosDrugDetailDao;
	
	public Map getAllDrugRecords(int curpage,int pagesize){
		List <HosDrugRecord> list = hosDrugRecordDao.getAllDrugRecords(PageRequest.of(curpage-1, pagesize));
		long total = hosDrugRecordDao.count();
		Map map = new HashMap<>();
		map.put("list", list);
		map.put("total", total);
		return map;
	}
	
	/**
	 * 
	* @Title:addHosDrugRecord
	* @Description:新增用药记录和用药明细
	* @param:@param record
	* @param:@throws ParseException
	* @return:void
	* @throws
	* @author:Hamster
	* @Date:2019年8月13日 上午10:35:25
	 */
	public void addHosDrugRecord(HosDrugRecord record) throws ParseException{
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = dateFormat.format(new Date());
		
		record.setHosDrugTime(dateFormat.parse(time));
		hosDrugRecordDao.save(record);
		
		List <HosDrugDetail> details = record.getHosDrugDetails();
		for(int i=0;i<details.size();i++){
			HosDrugDetailPK pk = new HosDrugDetailPK();
			pk.setHosDrugRid(record.getHosDrugRid());
			pk.setYpId(details.get(i).getDrugInformation().getYpId());
			details.get(i).setId(pk);
			hosDrugDetailDao.save(details.get(i));
		}
		
	}
	

	
}
