package com.his.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.SolveScheme;

/**  
* @ClassName: ISolveSchemeDao  
* @Description: 医嘱Dao
* @author Sbaby
* @date 2019年8月23日  上午9:14:49
*    
*/
public interface ISolveSchemeDao extends CrudRepository<SolveScheme, String> {

	/**
	* @Title:getByHistoryId
	* @Description:根据诊断记录编号查询医嘱
	* @param:@param historyId
	* @param:@return
	* @return:SolveScheme
	* @throws
	* @author:Sbaby
	* @Date:2019年8月26日 上午9:50:04
	 */
	@Query("from SolveScheme s where s.history.historyId = ?1")
	public SolveScheme getByHistoryId(String historyId);
	
}
