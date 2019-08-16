package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.HospitalizedPatient;

/**
 * 入院记录
 * @author dell
 *
 */
public interface IHosPatientDao extends CrudRepository<HospitalizedPatient, String>{
	
	/**
	 * 
	* @Title:getAllPatientsByPage
	* @Description:查询所有入院记录
	* @param:@return
	* @return:List<HospitalizedPatient>
	* @throws
	* @author:Hamster
	* @Date:2019年8月2日 下午10:37:25
	 */
	@Query("from HospitalizedPatient h where "
			+ " h.medicalCard.cardName like ?1 "
			+ " and h.department.ksName like ?2 "
			+ " and h.hosBed.wardRoom.ward.wardName like ?3 "
			+ " and h.hosBed.wardRoom.wroomName like ?4 "
			+ " and h.hospState !='已出院' ")
	public List<HospitalizedPatient> getAllPatientsByPage(String hospName,String ksName,String wardName,String roomName,Pageable page);
	
	
	@Query("from HospitalizedPatient h")
	public List <HospitalizedPatient> HospitalizedPatient(Pageable page);
	
	/**
	 * 
	* @Title:getPatientsByWroomId
	* @Description:根据病房id查询登记信息
	* @param:@param id
	* @param:@return
	* @return:List<HospitalizedPatient>
	* @throws
	* @author:Hamster
	* @Date:2019年8月2日 下午10:37:46
	 */
	@Query(value="select * from hospitalized_patients h where h.wroom_id=?1",nativeQuery=true)
	public List <HospitalizedPatient> getPatientsByWroomId(String id);
	
	/**
	 * 
	* @Title:getPatientByBid
	* @Description:根据床位id查询住院患者信息
	* @param:@param bedId
	* @param:@return
	* @return:HospitalizedPatient
	* @throws
	* @author:Hamster
	* @Date:2019年8月3日 上午8:34:59
	 */
	@Query(value="select * from hospitalized_patients h where h.hos_bid=?1",nativeQuery=true)
	public HospitalizedPatient getPatientByBid(String bedId);
}
