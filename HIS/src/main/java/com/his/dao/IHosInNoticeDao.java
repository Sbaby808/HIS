package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.HospitalNotice;

/**
 * 
* @ClassName: IHosInNoticeDao  
* @Description: 入院通知  
* @author Hamster
* @date 2019年8月15日  上午11:34:49
*
 */

public interface IHosInNoticeDao extends CrudRepository<HospitalNotice,String>{

	@Query("from HospitalNotice h where "
			+ " h.solveScheme.history.outpatientRegistration.medicalCard.cardName like ?1 "
			+ " and h.solveScheme.history.department.dept.deptName like ?2 "
			+ " and h.department.ksName like ?3 ")
	public List <HospitalNotice> getHosInNoticeByPage(String hospName,String sourceText,String departText,Pageable page);
	
	/**
	* @Title:getHosNoticeBySolveId
	* @Description:根据医嘱编号查询入院通知
	* @param:@param solveId
	* @param:@return
	* @return:HospitalNotice
	* @throws
	* @author:Sbaby
	* @Date:2019年8月23日 下午2:04:02
	 */
	@Query("from HospitalNotice h where h.solveScheme.scheId = ?1")
	public HospitalNotice getHosNoticeBySolveId(String solveId);
	
	/**
	* @Title:getHospitalNoticeByHistoryId
	* @Description:根据诊断记录编号查询入院通知单
	* @param:@param historyId
	* @param:@return
	* @return:HospitalNotice
	* @throws
	* @author:Sbaby
	* @Date:2019年8月25日 下午1:15:56
	 */
	@Query("from HospitalNotice h where h.solveScheme.history.historyId = ?1")
	public HospitalNotice getHospitalNoticeByHistoryId(String historyId);
	
}
