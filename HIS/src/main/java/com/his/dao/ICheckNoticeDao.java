package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.bean.Checknoticebean;
import com.his.pojo.CheckNoticeForm;

/**  
* @ClassName: ICheckNoticeDao  
* @Description: TODO(检查通知单dao)  
* @author TRC
* @date 2019年8月13日  下午4:07:09
*    
*/
public interface ICheckNoticeDao extends CrudRepository<CheckNoticeForm, String>{
	@Query(value="select c.solveScheme.history.outpatientRegistration.medicalCard.cardName,c.solveScheme.history.outpatientRegistration.medicalCard.cardId,c.checkPay.checkPayName,c.checkPay.checkId from CheckNoticeForm c where c.mcheckId=?1")
	public String getbnameandid(String id);
	@Query(value="from CheckNoticeForm c where c.solveScheme.history.outpatientRegistration.medicalCard.cardId=?1 and c.mcheckComment!='已处理'")
    public List<CheckNoticeForm> getbysoufenye(String sou,Pageable page);
	@Query(value="select count(1) from CheckNoticeForm c where c.solveScheme.history.outpatientRegistration.medicalCard.cardId=?1 and c.mcheckComment!='已处理'")
	public long getcount(String sou);
	@Query(value="from CheckNoticeForm c where c.mcheckId=?1")
	public List<CheckNoticeForm> getbyid(String id);
}
