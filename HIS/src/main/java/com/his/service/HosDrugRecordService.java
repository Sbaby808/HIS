package com.his.service;

import java.math.BigDecimal;
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

import com.his.dao.IDrugWarehouseDao;
import com.his.dao.IHosDrugDetailDao;
import com.his.dao.IHosDrugRecordDao;
import com.his.dao.IMedicineDao;
import com.his.pojo.DrugWarehouse;
import com.his.pojo.HosDrugDetail;
import com.his.pojo.HosDrugDetailPK;
import com.his.pojo.HosDrugRecord;
import com.his.pojo.Medicine;

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
	@Autowired
	private IDrugWarehouseDao drugWarehouseDao;
	@Autowired
	private IMedicineDao medicineDao;
	
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
			
			String ypId = details.get(i).getDrugInformation().getYpId();
			
			List <DrugWarehouse> list = drugWarehouseDao.getDrugsOrderByExpireDate(ypId);
			if(list.size()>0){
				String pcId = list.get(0).getPckcId();
				Medicine medicine = medicineDao.getMedBypcId(pcId);
				if(medicine.getMedicineName().compareTo(details.get(i).getDrugUseNum()) > -1){
					BigDecimal num1 = medicine.getMedicineName();
					BigDecimal num2 = details.get(i).getDrugUseNum();
					medicine.setMedicineName(num1.subtract(num2));
					medicineDao.save(medicine);
				}	
				else{
					BigDecimal total = details.get(i).getDrugUseNum();
					System.out.println("total:"+total);
					for(int j=0;j<list.size();j++){	
						String pcId2 = list.get(j).getPckcId();
						Medicine medicine2 = medicineDao.getMedBypcId(pcId2);
						
						if(total.compareTo(BigDecimal.ZERO)==1){
							if(medicine2.getMedicineName().compareTo(total) > -1){
								medicine2.setMedicineName(medicine2.getMedicineName().subtract(total));		
								medicineDao.save(medicine2);
							}
							else{
								total = total.subtract(medicine2.getMedicineName());
								System.out.println(total);
								medicine2.setMedicineName(BigDecimal.ZERO);
								medicineDao.save(medicine2);
							}
						}
					}
				}
			}
			
		}
		
	}
	

	
}
