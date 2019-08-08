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
	* @Description:TODO根据员工id查询所有手术记录
	* @param:@return
	* @return:List<OperationRecordbean>
	* @throws
	* @author:TRC
	* @Date:2019年8月1日 下午5:19:25
	 */
	@Query(value="select new com.his.bean.OperationRecordbean(o.operationRecord.opeId,p.medicalCard.cardName,p.operationPay.operPayName, o.operationRecord.opeJg, o.operationRecord.opeStatus,o.operationRecord.opeTime,o.operationRecord.opeType,o.operationRecord.opeDiagnose) from OpeEmp o,OperPayRecord p where o.operationRecord.opeId=p.operationRecord.opeId and p.medicalCard.cardName like ?1 and o.empInformation.ygxh=?2")
	public List<OperationRecordbean> getallOperationRecordbeans(String sou,String ygxh,Pageable page);
	@Query(value="select count(*) from OpeEmp o,OperPayRecord p where o.operationRecord.opeId=p.operationRecord.opeId and p.medicalCard.cardName like ?1 and o.empInformation.ygxh=?2")
	public long getcount(String sou,String ygxh);

}
