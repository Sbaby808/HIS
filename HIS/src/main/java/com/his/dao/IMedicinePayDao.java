package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.MedicinePay;

/**  
* @ClassName: IMedicinePayDao  
* @Description: 药品划价项Dao
* @author Sbaby
* @date 2019年8月6日  下午5:01:48
*    
*/
public interface IMedicinePayDao extends CrudRepository<MedicinePay, String> {

	/**
	* @Title:getByPage
	* @Description:分页查询药品收费项
	* @param:@param pageable
	* @param:@return
	* @return:List<MedicinePay>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月6日 下午5:18:50
	 */
	@Query("from MedicinePay m")
	public List<MedicinePay> getByPage(Pageable pageable);
	
	/**
	* @Title:getAllCount
	* @Description:查询药品划价项总记录条数
	* @param:@return
	* @return:int
	* @throws
	* @author:Sbaby
	* @Date:2019年8月6日 下午5:35:19
	 */
	@Query("select count(*) from MedicinePay m")
	public int getAllCount();
	
}
