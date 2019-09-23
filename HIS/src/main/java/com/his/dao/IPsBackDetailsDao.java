package com.his.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.PsBackDetail;
import com.his.pojo.PsBackDetailPK;

/**
 * @ClassName: IPsBackDetailsDao
 * @Description: 药库退药明细dao
 * @author crazy_long
 * @date 2019年7月30日 上午10:55:23
 * 
 */
public interface IPsBackDetailsDao extends CrudRepository<PsBackDetail, PsBackDetailPK> {
	
	/**
	* @Title:getHasBackByPage
	* @Description:条件查询退药信息
	* @param:@param gysId
	* @param:@param ygxh
	* @param:@param backDate
	* @param:@param page
	* @param:@return
	* @return:List<PsBackDetail>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月20日 下午3:18:20
	 */
	@Query(value="select * from ps_back_details pbd " + 
			"left outer join putstock_back pb on pbd.ps_back_id_ = pb.ps_back_id_ " + 
			"where pb.gys_id like ?1 and pb.ygxh like ?2 and pb.ps_back_time = ?3 ",nativeQuery = true)
	public List<PsBackDetail> getHasBackByPage(String gysId,String ygxh,Date backDate,Pageable page);
	
	/**
	* @Title:getHasBackCount
	* @Description:条件查询退药信息的条数
	* @param:@param gysId
	* @param:@param ygxh
	* @param:@param backDate
	* @param:@return
	* @return:List<PsBackDetail>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月20日 下午3:19:05
	 */
	@Query(value="select count(*) from ps_back_details pbd " + 
			"left outer join putstock_back pb on pbd.ps_back_id_ = pb.ps_back_id_ " + 
			"where pb.gys_id like ?1 and pb.ygxh like ?2 and pb.ps_back_time = ?3 ",nativeQuery = true)
	public int getHasBackCount(String gysId,String ygxh,Date backDate);
	
	/**
	* @Title:getHasBackByPage
	* @Description:条件查询退药信息
	* @param:@param gysId
	* @param:@param ygxh
	* @param:@param backDate
	* @param:@param page
	* @param:@return
	* @return:List<PsBackDetail>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月20日 下午3:19:32
	 */
	@Query(value="select * from ps_back_details pbd " + 
			"left outer join putstock_back pb on pbd.ps_back_id_ = pb.ps_back_id_ " + 
			"where pb.gys_id like ?1 and pb.ygxh like ?2 ",nativeQuery = true)
	public List<PsBackDetail> getHasBackNoTimeByPage(String gysId,String ygxh,Pageable page);
	
	/**
	* @Title:getHasBackNoTimeCount
	* @Description:条件查询退药信息的条数
	* @param:@param gysId
	* @param:@param ygxh
	* @param:@return
	* @return:List<PsBackDetail>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月20日 下午3:20:57
	 */
	@Query(value="select count(*) from ps_back_details pbd " + 
			"left outer join putstock_back pb on pbd.ps_back_id_ = pb.ps_back_id_ " + 
			"where pb.gys_id like ?1 and pb.ygxh like ?2 ",nativeQuery = true)
	public int getHasBackNoTimeCount(String gysId,String ygxh);

}
