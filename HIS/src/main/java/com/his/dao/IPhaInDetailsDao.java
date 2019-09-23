package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.PhaInDetail;
import com.his.pojo.PhaInDetailPK;

/**
 * @ClassName: PhaInDetailsDao
 * @Description: 药房入库单明细dao
 * @author crazy_long
 * @date 2019年7月30日 上午10:57:58
 * 
 */
public interface IPhaInDetailsDao extends CrudRepository<PhaInDetail, PhaInDetailPK> {
	
	/**
	* @Title:getOneDrugAllPutStockByPage
	* @Description:获取某一个药品的所有入库记录
	* @param:@param searchContent
	* @param:@param deptId
	* @param:@param page
	* @param:@return
	* @return:List<PhaInDetail>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月22日 下午5:37:30
	 */
	@Query(value="select * from pha_in_details pid " + 
			"left outer join medicine md on pid.medicine_id = md.medicine_id " + 
			"left outer join drug_warehouse dw on md.pckc_id = dw.pckc_id " + 
			"left outer join drug_information di on dw.yp_id = di.yp_id " + 
			"where di.yp_name like ?1 or di.vocode like ?1 and md.dept_id = ?2 ",nativeQuery = true)
	public List<PhaInDetail> getOneDrugAllPutStockByPage(String searchContent,String deptId,Pageable page);
	
	/**
	* @Title:getOneDrugAllPutStockCount
	* @Description:获取某一个药品的所有入库记录条数
	* @param:@param searchContent
	* @param:@param deptId
	* @param:@return
	* @return:int
	* @throws
	* @author:crazy_long
	* @Date:2019年9月22日 下午5:44:52
	 */
	@Query(value="select count(*) from pha_in_details pid " + 
			"left outer join medicine md on pid.medicine_id = md.medicine_id " + 
			"left outer join drug_warehouse dw on md.pckc_id = dw.pckc_id " + 
			"left outer join drug_information di on dw.yp_id = di.yp_id " + 
			"where di.yp_name like ?1 or di.vocode like ?1 and md.dept_id = ?2 ",nativeQuery = true)
	public int getOneDrugAllPutStockCount(String searchContent,String deptId);

}
