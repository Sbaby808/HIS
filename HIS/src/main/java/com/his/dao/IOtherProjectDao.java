package com.his.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.OtherProject;

/**  
* @ClassName: IOtherProjectDao  
* @Description: 其他收费项Dao
* @author Sbaby
* @date 2019年8月3日  上午9:05:22
*    
*/
public interface IOtherProjectDao extends CrudRepository<OtherProject, String> {

	/**
	 * 
	* @Title:getHosProject
	* @Description:查询住院床位的收费项
	* @param:@return
	* @return:List<OtherProject>
	* @throws
	* @author:Hamster
	* @Date:2019年8月4日 下午9:42:02
	 */
	@Query("from OtherProject o where o.projectId='a' or o.projectId='b' ")
	public List <OtherProject> getHosBedProject();
}
