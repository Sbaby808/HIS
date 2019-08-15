package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.HosPrescription;

/**
 * 
* @ClassName: IHosPrescriptionDao  
* @Description: 住院处方
* @author Hamster
* @date 2019年8月5日  下午7:19:20
*
 */
public interface IHosPrescriptionDao extends CrudRepository<HosPrescription, String>{

	/**
	 * 
	* @Title:getAllHosPrescription
	* @Description:无分页查询所有处方
	* @param:@return
	* @return:List<HosPrescription>
	* @throws
	* @author:Hamster
	* @Date:2019年8月5日 下午7:20:50
	 */
	@Query("from HosPrescription h where h.hosDiagnosticRecord.medicalRecord.medOutTime is null")
	public List <HosPrescription> getAllHosPrescription();
	
	/**
	 * 
	* @Title:getHosPrescriptionByPage
	* @Description:分页查询所有处方
	* @param:@param page
	* @param:@return
	* @return:List<HosPrescription>
	* @throws
	* @author:Hamster
	* @Date:2019年8月5日 下午7:21:05
	 */
	@Query("from HosPrescription h where h.hosDiagnosticRecord.medicalRecord.medOutTime is null")
	public List <HosPrescription> getHosPrescriptionByPage(Pageable page);
	
	/**
	 * 
	* @Title:getHosPresByDiagId
	* @Description:根据诊断记录id查询处方
	* @param:@param diagId
	* @param:@return
	* @return:HosPrescription
	* @throws
	* @author:Hamster
	* @Date:2019年8月7日 下午9:37:32
	 */
	@Query(value="select * from hos_prescription h where h.hos_diag_id=?1",nativeQuery=true)
	public HosPrescription getHosPresByDiagId(String diagId);
}
