package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.Illness;

/**  
* @ClassName: IIllnessDao  
* @Description: 疾病dao
* @author Sbaby
* @date 2019年8月21日  下午3:25:30
*    
*/
public interface IIllnessDao extends CrudRepository<Illness, String> {

	/**
	* @Title:searchByKey
	* @Description:搜索疾病
	* @param:@param searchKey
	* @param:@return
	* @return:List<Illness>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月21日 下午3:40:30
	 */
	@Query("from Illness i where i.illCode like ?1 "
			+ "or i.deputyCode like ?1 "
			+ "or i.illName like ?1 "
			+ "or i.illVocode like ?1 ")
	public List<Illness> searchByKey(String searchKey, Pageable pageable);
	
}
