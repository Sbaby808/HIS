package com.his.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
