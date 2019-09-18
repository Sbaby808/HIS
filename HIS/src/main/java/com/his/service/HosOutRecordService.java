package com.his.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.ForChart;
import com.his.dao.IHosOutRecordDao;
import com.his.pojo.OutHospitaiRecord;

/**
 * 
* @ClassName: HosOutRecordService  
* @Description: 出院记录
* @author Hamster
* @date 2019年8月23日  下午8:11:17
*
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class HosOutRecordService {

	@Autowired
	private IHosOutRecordDao hosOutRecordDao;
	
	public Map getHosOutRecord(String cardName,String ksName,int curpage,int pagesize){
		List <OutHospitaiRecord> list = hosOutRecordDao.getOutRecord(cardName,ksName,PageRequest.of(curpage-1, pagesize));
		Long total = hosOutRecordDao.countNum(cardName, ksName);
		Map map = new HashMap<>();
		map.put("list", list);
		map.put("total", total);
		return map;
	}
	
	/**
	 * 
	* @Title:countForCharts
	* @Description:出院记录图表统计
	* @param:@return
	* @return:List<BigDecimal>
	* @throws
	* @author:Hamster
	* @Date:2019年9月1日 下午8:05:08
	 */
	public List<BigDecimal> countForCharts(){
		List object = hosOutRecordDao.outRecordForCharts();
		List<ForChart> list = new ArrayList();
		for (Object row : object) {
			Object[] cells = (Object[]) row;
			ForChart result= new ForChart();
			result.setTime((String) cells[0]);
			result.setNum((BigDecimal) cells[1]);
			list.add(result);
		}
        List<BigDecimal> numbers=list.stream().map(ForChart::getNum).collect(Collectors.toList());
        return numbers;
	}
}
