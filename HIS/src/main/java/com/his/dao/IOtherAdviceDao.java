package com.his.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.OtherAdvice;

/**  
* @ClassName: OtherAdviceDao  
* @Description: 门诊医嘱其他建议Dao
* @author Sbaby
* @date 2019年8月22日  下午5:42:02
*    
*/
public interface IOtherAdviceDao extends CrudRepository<OtherAdvice, String> {

	/**
	* @Title:findAllOtherAdvice
	* @Description:根据医嘱查询其他建议
	* @param:@param solveSchemeId
	* @param:@return
	* @return:List<OtherAdvice>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月23日 上午10:32:01
	 */
	@Query("from OtherAdvice o where o.solveScheme.scheId = ?1")
	public List<OtherAdvice> findAllOtherAdvice(String solveSchemeId);
	
	/**
	* @Title:findOtherAdivceByHistoryId
	* @Description:根据诊断记录编号查询常规医嘱
	* @param:@param historyId
	* @param:@return
	* @return:List<OtherAdvice>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月25日 下午1:03:26
	 */
	@Query("from OtherAdvice o where o.solveScheme.history.historyId = ?1")
	public List<OtherAdvice> findOtherAdivceByHistoryId(String historyId);
}
