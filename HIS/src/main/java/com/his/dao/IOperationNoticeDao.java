package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.bean.OpeNoticebean;
import com.his.pojo.OpeNotice;
import com.his.pojo.OperationPay;

/**  
* @ClassName: IOperationNotice  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author TRC
* @date 2019年8月2日  上午11:48:40
*    
*/
public interface IOperationNoticeDao extends CrudRepository<OpeNotice, String>{
	@Query(value="select new com.his.bean.OpeNoticebean(o.moperId, o.moperComment,o.moperTime, o.operationPay.operPayName,o.solveScheme.history.outpatientRegistration.medicalCard.cardName,o.solveScheme.history.outpatientRegistration.medicalCard.cardId,o.operationPay.operPayId)  from OpeNotice o where o.solveScheme.history.outpatientRegistration.medicalCard.cardId=?1")
	public List<OpeNoticebean> getopenoticebysou(String sou);
	@Query(value="select count(*) from OpeNotice o where o.solveScheme.history.outpatientRegistration.medicalCard.cardId=?1")
	public long getcountbysou(String sou);
	@Query(value="select new com.his.bean.OpeNoticebean(o.moperId, o.moperComment,o.moperTime, o.operationPay.operPayName,o.solveScheme.history.outpatientRegistration.medicalCard.cardName,o.solveScheme.history.outpatientRegistration.medicalCard.cardId,o.operationPay.operPayId)  from OpeNotice o where o.solveScheme.history.outpatientRegistration.medicalCard.cardId=?1")
    public List<OpeNoticebean> getNoticebeans(String sou,Pageable page);
	@Query(value="select count(*) from OpeNotice o")
    public long getcount();
	@Query(value="select new com.his.pojo.OperationPay(o.operPayName,o.operPayPrice) from OperationPay o where o.operPayId=?1")
	public OperationPay getPaybyid(String id);
	@Query(value="from OpeNotice o where o.moperId=?1")
	public List<OpeNotice> getbyid(String id);
}
