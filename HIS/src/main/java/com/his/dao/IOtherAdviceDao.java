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
	
}
