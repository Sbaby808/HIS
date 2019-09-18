package com.his.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.History;

/**
 * @Author Sbaby
 * @Date 2019/08/20 11:56
 * @Version 1.0
 */
public interface IHistoryDao extends CrudRepository<History, String> {

	/**
	* @Title:getHistoryByYgxh
	* @Description:分页查询医生的诊断记录
	* @param:@param ygxh
	* @param:@param pageable
	* @param:@return
	* @return:List<History>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月24日 下午5:37:36
	 */
	@Query(value = "select his.* from history his left outer join reg_emp re on his.reg_id = re.reg_id "
       + "left outer join emp_information emp on re.ygxh = emp.ygxh "
       + "where re.reg_duty = '医生' "
       + "and emp.ygxh = ?1 "
       + "and ill_id is not null "
       + "order by his.his_time desc", nativeQuery = true)
	public List<History> getHistoryByYgxh(String ygxh, Pageable pageable);
	
	/**
	* @Title:getHisotryByYgxhCount
	* @Description:查询医生的诊断记录数量
	* @param:@param ygxh
	* @param:@return
	* @return:int
	* @throws
	* @author:Sbaby
	* @Date:2019年8月24日 下午5:39:23
	 */
	@Query(value = "select count(*) from history his left outer join reg_emp re on his.reg_id = re.reg_id "
       + "left outer join emp_information emp on re.ygxh = emp.ygxh "
       + "where re.reg_duty = '医生' "
       + "and emp.ygxh = ?1 "
       + "and ill_id is not null "
       + "order by his.his_time desc", nativeQuery = true)
	public int getHisotryByYgxhCount(String ygxh);
	
	/**
	* @Title:searchHistoryCount
	* @Description:搜索诊断记录条数
	* @param:@param ygxh
	* @param:@param illnessKey
	* @param:@param specification
	* @param:@return
	* @return:int
	* @throws
	* @author:Sbaby
	* @Date:2019年8月25日 下午3:12:34
	 */
	@Query("select count(*) from RegEmp re "
			+ "where re.empInformation.ygxh = ?1 "
			+ "and re.regDuty = '医生' "
			+ "and re.outpatientRegistration.medicalCard.cardName like ?2 "
			+ "and re.outpatientRegistration.history.illness.illName like ?3 "
			+ "and re.outpatientRegistration.history.hisTime between ?4 and ?5 "
			+ "order by re.outpatientRegistration.history.hisTime desc")
	public int searchHistoryCount(String ygxh, String nameKey, String illnessKey, Date startTime, Date endTime);
	
	/**
	* @Title:searchHistoryCountByCardId
	* @Description:根据就诊卡号查询诊断记录条数
	* @param:@param cardId
	* @param:@param startTime
	* @param:@param endTime
	* @param:@return
	* @return:int
	* @throws
	* @author:Sbaby
	* @Date:2019年8月26日 下午3:34:18
	 */
	@Query("select count(*) from History h "
			+ "where h.outpatientRegistration.medicalCard.cardId = ?1 "
			+ "and h.prescription.outPrePay is null "
			+ "and h.hisTime between ?2 and ?3 "
			+ "order by h.hisTime desc")
	public int searchHistoryCountByCardId(String cardId, Date startTime, Date endTime);
	
	/**
	* @Title:searchHisPreCountByCardId
	* @Description:查询门诊处方诊断记录
	* @param:@param cardId
	* @param:@param startTime
	* @param:@param endTime
	* @param:@return
	* @return:int
	* @throws
	* @author:Sbaby
	* @Date:2019年9月3日 上午8:53:25
	 */
	@Query("select count(*) from History h "
			+ "where h.outpatientRegistration.medicalCard.cardId = ?1 "
			+ "and h.prescriptionId is not null "
			+ "and h.hisTime between ?2 and ?3 "
			+ "order by h.hisTime desc")
	public int searchHisPreCountByCardId(String cardId, Date startTime, Date endTime);
	
	/**
	* @Title:searchHisPreByCardId
	* @Description:查询带处方的门诊诊断记录
	* @param:@param cardId
	* @param:@param startTime
	* @param:@param endTime
	* @param:@return
	* @return:List<History>
	* @throws
	* @author:Sbaby
	* @Date:2019年9月3日 上午9:09:20
	 */
	@Query("from History h "
			+ "where h.outpatientRegistration.medicalCard.cardId = ?1 "
			+ "and h.prescriptionId is not null "
			+ "and h.hisTime between ?2 and ?3 "
			+ "order by h.hisTime desc")
	public List<History> searchHisPreByCardId(String cardId, Date startTime, Date endTime);
	
	/**
	* @Title:searchHistory
	* @Description:搜索诊断记录
	* @param:@param ygxh
	* @param:@param illnessKey
	* @param:@param specification
	* @param:@param pageable
	* @param:@return
	* @return:int
	* @throws 
	* @author:Sbaby
	* @Date:2019年8月25日 下午3:17:49
	 */
	@Query("select re.outpatientRegistration.history from RegEmp re "
			+ "where re.empInformation.ygxh = ?1 "
			+ "and re.regDuty = '医生' "
			+ "and re.outpatientRegistration.medicalCard.cardName like ?2 "
			+ "and re.outpatientRegistration.history.illness.illName like ?3 "
			+ "and re.outpatientRegistration.history.hisTime between ?4 and ?5 "
			+ "order by re.outpatientRegistration.history.hisTime desc")
	public List<History> searchHistory(String ygxh, String nameKey, String illnessKey, Date startTime, Date endTime, Pageable pageable);
	
	/**
	* @Title:searchHistoryByCardId
	* @Description:根据就诊卡编号查询诊断记录
	* @param:@param cardId
	* @param:@param startTime
	* @param:@param endTime
	* @param:@param pageable
	* @param:@return
	* @return:List<History>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月26日 下午3:37:15
	 */
	@Query(" from History h "
			+ "where h.outpatientRegistration.medicalCard.cardId = ?1 "
			+ "and h.prescription.outPrePay is null "
			+ "and h.hisTime between ?2 and ?3 "
			+ "order by h.hisTime desc")
	public List<History> searchHistoryByCardId(String cardId, Date startTime, Date endTime, Pageable pageable);
	
	/**
	* @Title:getHistoryCountByCardId
	* @Description:查询门诊诊断记录数量
	* @param:@param cardId
	* @param:@return
	* @return:List<History>
	* @throws
	* @author:Sbaby
	* @Date:2019年9月11日 下午6:21:52
	 */
	@Query("select count(*) from History h "
			+ "where h.outpatientRegistration.medicalCard.cardId = ?1 "
			+ "and h.illness != NULL")
	public int getHistoryCountByCardId(String cardId);
	
	/**
	* @Title:getHistoryByCardId
	* @Description:查询门诊诊断记录
	* @param:@param cardId
	* @param:@param pageable
	* @param:@return
	* @return:List<History>
	* @throws
	* @author:Sbaby
	* @Date:2019年9月11日 下午6:28:25
	 */
	@Query("from History h "
			+ "where h.outpatientRegistration.medicalCard.cardId = ?1 "
			+ "and h.illness != NULL "
			+ "order by hisTime desc")
	public List<History> getHistoryByCardId(String cardId, Pageable pageable);
}
