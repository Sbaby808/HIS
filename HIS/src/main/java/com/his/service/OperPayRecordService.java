package com.his.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.his.bean.Opepayrecordbean;
import com.his.bean.OperationRecordbean;
import com.his.dao.IOperPayRecordDao;
import com.his.pojo.OperPayRecord;
/**  
* @ClassName: OperPayRecordService  
* @Description: TODO(手术缴费记录service)  
* @author TRC
* @date 2019年7月30日  上午9:06:27
*    
*/
@Service
public class OperPayRecordService {

	@Autowired
	private IOperPayRecordDao iOperPayRecord;
	/**
	 * 
	* @Title:getallOperPayRecords
	* @Description:TODO查询所有手术缴费记录
	* @param:@return
	* @return:List<OperPayRecord>
	* @throws
	* @author:TRC
	* @Date:2019年7月30日 下午3:09:39
	 */
	public List<OperPayRecord> getallOperPayRecords(){
		return (List<OperPayRecord>) iOperPayRecord.findAll();
	} 
	/**
	 * 
	* @Title:addrecord
	* @Description:TODO添加手术缴费记录
	* @param:@param operPayRecord
	* @return:void
	* @throws
	* @author:TRC
	* @Date:2019年7月30日 下午3:10:01
	 */
	public void addrecord(OperPayRecord operPayRecord) {
		iOperPayRecord.save(operPayRecord);
	}
	
	/**
	 * 
	* @Title:getopepayrecord
	* @Description:TODO查询手术缴费记录by病人name
	* @param:@param curpage
	* @param:@param pagesize
	* @param:@param sou
	* @param:@return
	* @return:Map
	* @throws
	* @author:TRC
	* @Date:2019年8月5日 上午10:48:02
	 */
	public Map getopepayrecord(int curpage, int pagesize,String sou) {
		List<Opepayrecordbean> list=iOperPayRecord.getOperationRecordbeans(sou, PageRequest.of(curpage - 1,
		  pagesize));
		long total=iOperPayRecord.getpayrecordcount(sou);
		  Map map = new HashMap();
		  map.put("total", total);
		  map.put("list", list);
		return map;
	}
}
