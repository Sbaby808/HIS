package com.his.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.his.bean.OperationPaybean;
import com.his.bean.OperationRecordbean;
import com.his.dao.IOperationRecordDao;
import com.his.pojo.OperationRecord;
/**  
* @ClassName: OperationRecordService  
* @Description: TODO(手术记录service)  
* @author TRC
* @date 2019年7月30日  上午9:06:27
*    
*/
@Service
public class OperationRecordService {

	@Autowired
	private IOperationRecordDao iOperationRecordDao;
	/**
	 * 
	* @Title:getallOperationRecords
	* @Description:TODO查询所有的手术记录
	* @param:@return
	* @return:List<OperationRecord>
	* @throws
	* @author:TRC
	* @Date:2019年7月30日 下午3:05:19
	 */
	public Map getallRecordbeans(int curpage, int pagesize,String sou){
		List<OperationRecordbean> list=iOperationRecordDao.getallOperationRecordbeans(sou, PageRequest.of(curpage - 1,
				  pagesize));
		long total=iOperationRecordDao.getcount(sou);
		 Map map = new HashMap();
		 map.put("total", total);
		 map.put("list", list);
		return map;
	}
	/**
	 * 
	* @Title:addOperationRecord
	* @Description:TODO添加手术记录
	* @param:@param operationRecord
	* @return:void
	* @throws
	* @author:TRC
	* @Date:2019年7月30日 下午3:07:38
	 */
	public void addOperationRecord(OperationRecord operationRecord) {
		
		iOperationRecordDao.save(operationRecord);
	}
	
}
