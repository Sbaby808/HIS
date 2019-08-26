package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.Ward;

/**
 * 住院病区
 * @author dell
 *
 */
public interface IWardDao extends CrudRepository<Ward, String>{
	/**
	 * 
	* @Title:getAllWardByPage
	* @Description:分页查询所有病区
	* @param:@param page
	* @param:@return
	* @return:List<Ward>
	* @throws
	* @author:Hamster
	* @Date:2019年8月2日 下午9:29:23
	 */
	@Query("from Ward w where "
			+ " w.wardName like ?1"
			+ " and w.department.ksName like ?2 ")
	public List <Ward> getAllWardByPage(String wardName,String ksName,Pageable page); 
	
	/**
	 * 
	* @Title:getAllWard
	* @Description:无分页的查询
	* @param:@return
	* @return:List<Ward>
	* @throws
	* @author:Hamster
	* @Date:2019年8月2日 下午9:29:36
	 */
	@Query("from Ward w")
	public List <Ward> getAllWard();
	
	/**
	 * 
	* @Title:getWardByDid
	* @Description:根据科室id查询病区信息
	* @param:@param ks_id
	* @param:@return
	* @return:List<Ward>
	* @throws
	* @author:Hamster
	* @Date:2019年8月2日 下午9:29:48
	 */
	@Query(value="select * from ward w where w.ks_id=?1",nativeQuery=true)
	public List <Ward> getWardByDid(String ks_id);
	
	/**
	 * 
	* @Title:getWardByWid
	* @Description:根据病区id查询病区信息
	* @param:@param wardId
	* @param:@return
	* @return:Ward
	* @throws
	* @author:Hamster
	* @Date:2019年8月2日 下午9:31:20
	 */
	@Query(value="select * from ward w where w.ward_id=?1",nativeQuery=true)
	public Ward getWardByWid(String wardId);
}
