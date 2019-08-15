package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.bean.OpeNoticebean;
import com.his.pojo.HosSurgeryNotice;

/**
 * 
* @ClassName: IHosSurgeryNoticeDao  
* @Description: 住院手术通知单 
* @author Hamster
* @date 2019年8月10日  下午6:59:31
*
 */
public interface IHosSurgeryNoticeDao extends CrudRepository<HosSurgeryNotice, String>{
	@Query(value="select new com.his.bean.OpeNoticebean(h.hosSurId,h.payNote,h.hosSurTime,o.operPayName,h.hosDoctorAdvice.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.medicalCard.cardName,h.hosDoctorAdvice.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.medicalCard.cardId,h.hosSurNote) from HosSurgeryNotice h,OperationPay o where h.hosSurNote=o.operPayId and h.hosDoctorAdvice.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.medicalCard.cardId like?1")
	public List<OpeNoticebean> getzhuyuannotice(String sou,Pageable page);
	@Query(value="select count(1) from HosSurgeryNotice h,OperationPay o where h.hosSurNote=o.operPayId and h.hosDoctorAdvice.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.medicalCard.cardId like?1")
	public long getcount(String sou);

	
}
