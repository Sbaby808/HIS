package com.his.dao;

import com.his.pojo.OutMedicalRecord;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * @Author Sbaby
 * @Description 门诊就诊排队Dao
 * @Date 2019/08/17 15:46
 * @Version 1.0
 */
public interface IOutMedicalRecordDao extends CrudRepository<OutMedicalRecord, String> {
	
	/**
	* @Title:getQueueByRoomId
	* @Description:根据候诊厅编号查询候诊排队记录
	* @param:@param roomId
	* @param:@return
	* @return:List<OutMedicalRecord>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月19日 下午3:22:02
	 */
	@Query(value = "select omr.* from out_medical_records omr left outer join outpatient_registration ore on omr.reg_id = ore.reg_id " +
            "       left outer join reg_emp re on ore.reg_id = re.reg_id " +
            "       left outer join emp_information emp on re.ygxh = emp.ygxh " +
            "       left outer join waiting_room wr on emp.waiting_room_id = wr.waiting_room_id " +
            "       where re.reg_duty = '医生' and wr.waiting_room_id = ?1" +
			"		and to_char(sysdate, 'yyyy-MM-dd') = to_char(ore.do_date, 'yyyy-MM-dd') " +
			"		and omr.out_status != '已就诊' and omr.out_status != '挂号作废'" +
			"		order by omr.out_mtime ", nativeQuery = true)
	public List<OutMedicalRecord> getQueueByRoomId(String roomId);

    /**
     * @Title:checkForQueue
     * @Description:检查此号是否已经排队
     * @param:@param regId
     * @param:@return
     * @return:int
     * @throws
     * @author:Sbaby
     * @Date:2019年8月19日 下午3:22:02
     */
	@Query("select count(omr) from OutMedicalRecord  omr where omr.outpatientRegistration.regId = ?1")
	public int checkForQueue(String regId);

	@Query("select o.outStatus  from OutMedicalRecord o where o.outpatientRegistration.regId = ?1")
	public String findStatus(String regId);

	@Query(value = "select count(*) from out_medical_records omr left outer join outpatient_registration ore on omr.reg_id = ore.reg_id " +
			"       left outer join reg_emp re on ore.reg_id = re.reg_id " +
			"       left outer join emp_information emp on re.ygxh = emp.ygxh " +
			"       left outer join waiting_room wr on emp.waiting_room_id = wr.waiting_room_id " +
			"       where wr.waiting_room_id = ? " +
			"       and to_char(ore.do_date, 'yyyy-MM-dd') = to_char(sysdate, 'yyyy-MM-dd') " +
			"       and omr.out_status = '正在叫号'", nativeQuery = true)
	public int checkCall(String roomId);

	/**
	* @Title:checkCallPatient
	* @Description:查询当前正在叫号的患者
	* @param:@param roomId
	* @param:@return
	* @return:OutMedicalRecord
	* @throws
	* @author:Sbaby
	* @Date:2019年8月21日 下午2:09:09
	 */
	@Query(value = "select omr.* from out_medical_records omr left outer join outpatient_registration ore on omr.reg_id = ore.reg_id " +
			"       left outer join reg_emp re on ore.reg_id = re.reg_id " +
			"       left outer join emp_information emp on re.ygxh = emp.ygxh " +
			"       left outer join waiting_room wr on emp.waiting_room_id = wr.waiting_room_id " +
			"       where wr.waiting_room_id = ?1 " +
			"       and to_char(ore.do_date, 'yyyy-MM-dd') = to_char(sysdate, 'yyyy-MM-dd') " +
			"       and omr.out_status = '正在叫号'", nativeQuery = true)
	public OutMedicalRecord checkCallPatient(String roomId);
	
	/**
	* @Title:getOutMedicalRecord
	* @Description:根据就诊卡查询排队记录
	* @param:@param cardNum
	* @param:@param roomId
	* @param:@return
	* @return:OutMedicalRecord
	* @throws
	* @author:Sbaby
	* @Date:2019年8月22日 上午11:46:41
	 */
	@Query(value = "select omr.* from out_medical_records omr left outer join outpatient_registration ore on omr.reg_id = ore.reg_id " +
			"       left outer join reg_emp re on ore.reg_id = re.reg_id " +
			"       left outer join emp_information emp on re.ygxh = emp.ygxh " +
			"       left outer join waiting_room wr on emp.waiting_room_id = wr.waiting_room_id " +
			"       where wr.waiting_room_id = ?2 " +
			"       and to_char(ore.do_date, 'yyyy-MM-dd') = to_char(sysdate, 'yyyy-MM-dd') " + 
			"		and ore.card_id = ?1 order by out_mtime desc", nativeQuery = true)
	public List<OutMedicalRecord> getOutMedicalRecord(String cardNum, String roomId, Pageable pageable);
	
	/**
	* @Title:getDiagnosePatient
	* @Description:查询当前正在就诊的患者信息
	* @param:@param roomId
	* @param:@return
	* @return:OutMedicalRecord
	* @throws
	* @author:Sbaby
	* @Date:2019年8月21日 下午2:10:12
	 */
	@Query(value = "select omr.* from out_medical_records omr left outer join outpatient_registration ore on omr.reg_id = ore.reg_id " +
			"       left outer join reg_emp re on ore.reg_id = re.reg_id " +
			"       left outer join emp_information emp on re.ygxh = emp.ygxh " +
			"       left outer join waiting_room wr on emp.waiting_room_id = wr.waiting_room_id " +
			"       where wr.waiting_room_id = ?1 " +
			"       and to_char(ore.do_date, 'yyyy-MM-dd') = to_char(sysdate, 'yyyy-MM-dd') " +
			"       and omr.out_status = '正在就诊'", nativeQuery = true)
	public OutMedicalRecord getDiagnosePatient(String roomId);

}
