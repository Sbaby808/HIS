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
	@Query("from HosPrescription h")
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
	@Query("from HosPrescription h")
	public List <HosPrescription> getHosPrescriptionByPage(Pageable page);
}
