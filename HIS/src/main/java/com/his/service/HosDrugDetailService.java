package com.his.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IHosDrugDetailDao;
import com.his.dao.IHosPreDetailDao;
import com.his.dao.IHosPrescriptionDao;
import com.his.pojo.HosDrugDetail;
import com.his.pojo.HosPrescription;
import com.his.pojo.HosPrescriptionDetail;


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
	public List <HosDrugDetail> getHosDrugDetail(){
		return hosDrugDetailDao.getHosDrugDetail();
	}
	

	
}
