package com.his.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.TechnicalPost;

/**  
* @ClassName: ITechnicalPostDao  
* @Description: 职称表Dao
* @author crazy_long
* @date 2019年8月2日  下午11:51:49
*    
*/
public interface ITechnicalPostDao extends CrudRepository<TechnicalPost, String>{
	
	@Query("from TechnicalPost t")
	public List<TechnicalPost> queryAll();
	
	/**
	* @Title:getByOut
	* @Description:查询门诊的所有职称
	* @param:@param deptId
	* @param:@return
	* @return:List<TechnicalPost>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月9日 下午5:01:02
	 */
	@Query(value = "select t.* from (((technical_post t left outer join emp_information e on t.tp_id = e.tp_id) "
       + "left outer join user_role ur on e.ygxh = ur.ygxh) "
       + "left outer join role r on ur.role_id = r.role_id) "
       + "left outer join department d on r.ks_id = d.ks_id "
       + "where d.dept_id = ?1", nativeQuery = true)
	public List<TechnicalPost> getByOut(String deptId);

}
