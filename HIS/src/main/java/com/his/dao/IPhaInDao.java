package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.PhaIn;

/**
 * @ClassName: IPhaInDao
 * @Description: 药房入库单dao
 * @author crazy_long
 * @date 2019年7月30日 上午10:56:59
 * 
 */
public interface IPhaInDao extends CrudRepository<PhaIn, String> {
	
	/**
	* @Title:getDeptAllPhaInByPage
	* @Description:分页查找某一部门的入库单
	* @param:@param deptId
	* @param:@param page
	* @param:@return
	* @return:List<PhaIn>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月22日 下午4:10:24
	 */
	@Query(value="select * from pha_in pi left outer join outpatient_requestion_medicine orm on pi.req_id = orm.req_id " + 
			"where orm.req_status = '已入库' and orm.dept_id = ?1  order by pi.pha_in_date desc ",nativeQuery=true)
	public List<PhaIn> getDeptAllPhaInByPage(String deptId,Pageable page);
	
	/**
	* @Title:getDeptAllPhaInCount
	* @Description:分页查找某一部门的入库单的条数
	* @param:@param deptId
	* @param:@return
	* @return:List<PhaIn>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月22日 下午4:11:10
	 */
	@Query(value="select count(*) from pha_in pi left outer join outpatient_requestion_medicine orm on pi.req_id = orm.req_id " + 
			"where orm.req_status = '已入库' and orm.dept_id = ?1  order by pi.pha_in_date desc ",nativeQuery=true)
	public int getDeptAllPhaInCount(String deptId);
	
	/**
	* @Title:pahInIsExits
	* @Description:判断入库单是否存在
	* @param:@param reqId
	* @param:@return
	* @return:int
	* @throws
	* @author:crazy_long
	* @Date:2019年9月5日 下午8:36:19
	 */
	@Query(value="select * from pha_in p where p.req_id = ?1 ",nativeQuery=true)
	public PhaIn pahInIsExits(String reqId);

}
