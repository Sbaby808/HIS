package com.his.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.AllocationOutbound;

/**  
* @ClassName: IAllocationOutboundDao  
* @Description: 调拨出库表dao 当作回库单使用
* @author crazy_long
* @date 2019年7月30日  上午11:01:18
*    
*/
public interface IAllocationOutboundDao extends CrudRepository<AllocationOutbound, String>{

	/**
	* @Title:getForNoPassTimeSubmit
	* @Description:获取所有已提交的正常回库单
	* @param:@return
	* @return:List<AllocationOutbound>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月13日 下午12:34:13
	 */
	@Query(value="select * from  allocation_outbound a where a.back_type = '正常回库'  and a.allo_status = '回库中' ",nativeQuery = true)
	public List<AllocationOutbound> getForNoPassTimeSubmit();
	
	/**
	* @Title:getForPassTimeSubmit
	* @Description:获取所有已提交的过期回库单
	* @param:@return
	* @return:List<AllocationOutbound>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月13日 下午12:34:49
	 */
	@Query(value="select * from  allocation_outbound a where a.back_type = '过期回库'  and a.allo_status = '回库中' ",nativeQuery = true)
	public List<AllocationOutbound> getForPassTimeSubmit();
	
	/**
	* @Title:getForNoPassTime
	* @Description:获取未完成的正常回库单
	* @param:@return
	* @return:List<AllocationOutbound>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月12日 下午4:47:14
	 */
	@Query(value="select * from  allocation_outbound a where a.back_type != '过期回库' and a.allo_status = '未回库' ",nativeQuery = true)
	public List<AllocationOutbound> getForNoPassTime();
	
	/**
	* @Title:getForPassTime
	* @Description:获取未完成的过期回库单
	* @param:@return
	* @return:List<AllocationOutbound>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月12日 下午4:48:09
	 */
	@Query(value="select * from  allocation_outbound a where a.back_type = '过期回库' and a.allo_status = '未回库' ",nativeQuery = true)
	public List<AllocationOutbound> getForPassTime();
	
	/**
	* @Title:getAllNoDo
	* @Description:查找所有未提交的回库单
	* @param:@return
	* @return:List<AllocationOutbound>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月12日 下午5:07:25
	 */
	@Query(value="select * from  allocation_outbound a where a.allo_status = '未回库' ",nativeQuery = true)
	public List<AllocationOutbound> getAllNoDo();
}
