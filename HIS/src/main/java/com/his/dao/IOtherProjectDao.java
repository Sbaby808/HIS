package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
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
	
	/**
	* @Title:getByPage
	* @Description:分页查询其他收费项
	* @param:@param page
	* @param:@return
	* @return:List<OtherProject>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月6日 上午10:31:12
	 */
	@Query("from OtherProject o ")
	public List<OtherProject> getByPage(Pageable page);
	
	/**
	* @Title:getCount
	* @Description:查询其他收费项总记录条数
	* @param:@return
	* @return:int
	* @throws
	* @author:Sbaby
	* @Date:2019年8月6日 上午10:38:16
	 */
	@Query("select count(*) from OtherProject o")
	public int getCount();
}
