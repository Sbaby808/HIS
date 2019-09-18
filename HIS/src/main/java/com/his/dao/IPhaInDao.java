package com.his.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.PhaIn;

/**
 * @ClassName: IPhaInDao
 * @Description: 药房入库单dao
 * @author crazy_long
 * @date 2019年7月30日 上午10:56:59
 * 
 */
public interface IPhaInDao extends CrudRepository<PhaIn, String> {
	
	/**
	* @Title:pahInIsExits
	* @Description:判断入库单是否存在
	* @param:@param reqId
	* @param:@return
	* @return:int
	* @throws
	* @author:crazy_long
	* @Date:2019年9月5日 下午8:36:19
	 */
	@Query(value="select * from pha_in p where p.req_id = ?1 ",nativeQuery=true)
	public PhaIn pahInIsExits(String reqId);

}
