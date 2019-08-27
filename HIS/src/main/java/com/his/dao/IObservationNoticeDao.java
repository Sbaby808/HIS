package com.his.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.ObservationNotice;

/**  
* @ClassName: IObservationNoticeDao  
* @Description: 留观通知单Dao
* @author Sbaby
* @date 2019年8月23日  下午2:40:58
*    
*/
public interface IObservationNoticeDao extends CrudRepository<ObservationNotice, String> {

	/**
	* @Title:getBySolveId
	* @Description:根据医嘱编号查询留观通知单
	* @param:@param id
	* @param:@return
	* @return:ObservationNotice
	* @throws
	* @author:Sbaby
	* @Date:2019年8月23日 下午5:10:30
	 */
	@Query("from ObservationNotice o where o.solveScheme.scheId = ?1")
	public ObservationNotice getBySolveId(String id);
	
	/**
	* @Title:getByHistoryId
	* @Description:根据诊断记录编号查询留观通知单
	* @param:@param historyId
	* @param:@return
	* @return:ObservationNotice
	* @throws
	* @author:Sbaby
	* @Date:2019年8月25日 下午1:37:04
	 */
	@Query("from ObservationNotice o where o.solveScheme.history.historyId = ?1")
	public ObservationNotice getByHistoryId(String historyId);
}
