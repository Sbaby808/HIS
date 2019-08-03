package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.bean.OperationRecordbean;
import com.his.pojo.OperationRecord;
/**  
* @ClassName: IOperationRecordDao  
* @Description: TODO(手术记录dao)  
* @author TRC
* @date 2019年7月30日  上午9:06:27
*    
*/
public interface IOperationRecordDao extends CrudRepository<OperationRecord, String>{
	/**
	 * 
	* @Title:getallOperationRecordbeans
	* @Description:TODO查询所有手术记录
	* @param:@return
	* @return:List<OperationRecordbean>
	* @throws
	* @author:TRC
	* @Date:2019年8月1日 下午5:19:25
	 */
	@Query(value="select new com.his.bean.OperationRecordbean(o.opeId,p.medicalCard.cardName,p.operationPay.operPayName, o.opeJg, o.opeStatus,o.opeTime,o.opeType,o.opeDiagnose) from OperPayRecord p,OperationRecord o where p.operationRecord = o.opeId and o.opeDiagnose like ?1")
	public List<OperationRecordbean> getallOperationRecordbeans(String sou,Pageable page);
	@Query(value="select count(*) from OperationRecord o where o.opeDiagnose like ?1")
	public long getcount(String sou);

}
