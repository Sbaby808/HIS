package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.bean.Checknoticebean;
import com.his.bean.Zhuyuanchecknotice;
import com.his.pojo.HosCheckNotice;

/**
 * 
* @ClassName: IHosCheckNoticeDao  
* @Description:住院检查通知单 
* @author Hamster
* @date 2019年8月10日  下午6:33:17
*
 */
public interface IHosCheckNoticeDao extends CrudRepository<HosCheckNotice, String>{
	@Query(value="select new com.his.bean.Checknoticebean(h.hosCheckNid,h.hosDoctorAdvice.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.medicalCard.cardName,h.hosDoctorAdvice.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.medicalCard.cardId,c.checkPayName,h.hosCheckNote,h.state,h.hosCheckNtime) from HosCheckNotice h,CheckPay c where h.hosCheckNote=c.checkId and h.hosDoctorAdvice.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.medicalCard.cardId like?1 and h.state!='已处理' order by h.hosCheckNtime")
	public List<Checknoticebean> getZhuyuanchecknotices(String sou,Pageable page);
	@Query(value="select count(1) from HosCheckNotice h,CheckPay c where h.hosCheckNote=c.checkId and h.hosDoctorAdvice.hosDiagnosticRecord.medicalRecord.hospitalizedPatient.medicalCard.cardId like?1 and h.state!='已处理'")
	public long getcount(String sou);

	
}
