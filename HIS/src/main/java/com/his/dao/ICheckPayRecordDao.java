package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.CheckPay;
import com.his.pojo.CheckPayRecord;

/**  
* @ClassName: ICheckPayRecordDao  
* @Description: TODO(检查缴费记录)  
* @author TRC
* @date 2019年7月30日  上午9:55:31
*    
*/
public interface ICheckPayRecordDao extends CrudRepository<CheckPayRecord, String>{
	@Query(value="from CheckPayRecord c where c.medicalCard.cardId=?1 and c.checkPay.checkId=?2 and c.checkResultForm.checkResultId=null order by c.checkJfTime")
	public List<CheckPayRecord> getPayRecord(String card_id,String cheid);
	@Query(value="from CheckPayRecord c where c.medicalCard.cardName like?1")
	public List<CheckPayRecord> getpayrecord(String sou,Pageable page);
	@Query(value="select count(1) from CheckPayRecord c where c.medicalCard.cardName like?1")
	public long getgount(String sou);
	
}
