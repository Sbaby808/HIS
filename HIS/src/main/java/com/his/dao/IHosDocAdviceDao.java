package com.his.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.HosDoctorAdvice;

/**
 * 
* @ClassName: IHosDocAdviceDao  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author Hamster
* @date 2019年8月7日  上午8:56:59
*
 */
public interface IHosDocAdviceDao extends CrudRepository<HosDoctorAdvice, String>{

	/**
	 * 
	* @Title:getHosDocAdvice
	* @Description:查询所有住院医嘱
	* @param:@return
	* @return:List<HosDoctorAdvice>
	* @throws
	* @author:Hamster
	* @Date:2019年8月7日 上午8:58:18
	 */
	@Query("from HosDoctorAdvice h")
	public List <HosDoctorAdvice> getHosDocAdvice();
	
	/**
	 *
	* @Title:getHosDocAdviceByDid
	* @Description:根据诊断记录id查询医嘱
	* @param:@param diagId
	* @param:@return
	* @return:HosDoctorAdvice
	* @throws
	* @author:Hamster
	* @Date:2019年8月7日 上午9:32:25
	 */
	@Query(value="select * from hos_doctor_advice h where h.hos_diag_id=?1",nativeQuery=true)
	public HosDoctorAdvice getHosDocAdviceByDid(String diagId);
	
	/**
	 * 
	* @Title:getDocAdviceByDocId
	* @Description:根据医嘱id查询医嘱
	* @param:@param docId
	* @param:@return
	* @return:HosDoctorAdvice
	* @throws
	* @author:Hamster
	* @Date:2019年8月7日 上午11:45:23
	 */
	@Query("from HosDoctorAdvice h where h.hosDcoId=?1")
	public HosDoctorAdvice getDocAdviceByDocId(String docId);
	
}