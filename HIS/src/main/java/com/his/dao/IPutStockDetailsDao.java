package com.his.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.PutStock;
import com.his.pojo.PutStockDetail;
import com.his.pojo.PutStockDetailPK;

/**  
* @ClassName: IPutStockDetailsDao  
* @Description: 药品入库明细dao
* @author crazy_long
* @date 2019年7月30日  上午10:32:39
*    
*/
public interface IPutStockDetailsDao extends CrudRepository<PutStockDetail, PutStockDetailPK>{
	
	/**
	* @Title:queryRecordByPage
	* @Description:查找入库记录
	* @param:@param ygxh
	* @param:@param rkTime
	* @param:@param page
	* @param:@return
	* @return:List<PutStock>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月19日 上午10:07:19
	 */
	@Query(value="select pd.* from put_stock_details pd left outer join put_stock ps on pd.rk_id = ps.rk_id left outer join drug_warehouse dw on pd.pckc_id = dw.pckc_id "
			+ "left outer join drug_information di on dw.yp_id = di.yp_id "
			+ "where ps.ygxh like ?1 "
			+ "and ps.rk_time = ?2 "
			+ "and ps.ps.rk_time >= ?3 "
			+ "and ps.rk_time <= ?4 ",nativeQuery = true)
	public List<PutStockDetail> queryRecordByPage(String ygxh,Date rkTime,Date firstDate,Date lastDate,Pageable page);
		
	/**
	* @Title:queryRecordByPage
	* @Description:查找符合条件入库记录的条数
	* @param:@param ygxh
	* @param:@param rkTime
	* @param:@param page
	* @param:@return
	* @return:List<PutStock>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月19日 上午10:07:19
	 */
	@Query(value="select count(*) from put_stock_details pd left outer join put_stock ps on pd.rk_id = ps.rk_id left outer join drug_warehouse dw on pd.pckc_id = dw.pckc_id "
			+ "left outer join drug_information di on dw.yp_id = di.yp_id "
			+ "where ps.ygxh like ?1 "
			+ "and ps.rk_time = ?2 "
			+ "and ps.ps.rk_time >= ?3 "
			+ "and ps.rk_time <= ?4 ",nativeQuery = true)
	public int queryRecordCount(String ygxh,Date rkTime,Date firstDate,Date lastDate);
	
	/**
	* @Title:queryRecordByPage
	* @Description:当日期为空时查询入库记录
	* @param:@param ygxh
	* @param:@param rkTime
	* @param:@param firstDate
	* @param:@param lastDate
	* @param:@param page
	* @param:@return
	* @return:List<PutStockDetail>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月20日 下午2:22:31
	 */
	@Query(value="select pd.* from put_stock_details pd left outer join put_stock ps on pd.rk_id = ps.rk_id left outer join drug_warehouse dw on pd.pckc_id = dw.pckc_id "
			+ "left outer join drug_information di on dw.yp_id = di.yp_id "
			+ "where ps.ygxh like ?1 "
			+ "and ps.ps.rk_time >= ?2 "
			+ "and ps.rk_time <= ?3 ",nativeQuery = true)
	public List<PutStockDetail> queryRecordDateIsNullByPage(String ygxh,Date firstDate,Date lastDate,Pageable page);
	
	/**
	* @Title:queryRecordCount
	* @Description:当日期为空时查询入库记录条数
	* @param:@param ygxh
	* @param:@param rkTime
	* @param:@param firstDate
	* @param:@param lastDate
	* @param:@return
	* @return:int
	* @throws
	* @author:crazy_long
	* @Date:2019年8月20日 下午2:23:16
	 */
	@Query(value="select count(*) from put_stock_details pd left outer join put_stock ps on pd.rk_id = ps.rk_id left outer join drug_warehouse dw on pd.pckc_id = dw.pckc_id "
			+ "left outer join drug_information di on dw.yp_id = di.yp_id "
			+ "where ps.ygxh like ?1 "
			+ "and ps.ps.rk_time >= ?2 "
			+ "and ps.rk_time <= ?3 ",nativeQuery = true)
	public int queryRecordDateIsNullCount(String ygxh,Date firstDate,Date lastDate);

}
