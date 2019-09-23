package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.DamagedDrugDetail;
import com.his.pojo.DamagedDrugDetailPK;
import com.his.pojo.DamagedMedicine;

/**  
* @ClassName: IDamagedDrugDetailsDao  
* @Description: 药品报损明细dao
* @author crazy_long
* @date 2019年7月30日  上午10:41:39
*    
*/
public interface IDamagedDrugDetailsDao extends CrudRepository<DamagedDrugDetail, DamagedDrugDetailPK>{

	/**
	* @Title:isHaveDetail
	* @Description:查看报损单明细的数量
	* @param:@param damagedId
	* @param:@return
	* @return:int
	* @throws
	* @author:crazy_long
	* @Date:2019年9月22日 下午12:23:28
	 */
	@Query("select count(*) from DamagedDrugDetail d where d.damagedMedicine.damagedId = ?1 ")
	public int isHaveDetail(String damagedId);
	
	/**
	* @Title:queryDamagedDetailByPage
	* @Description:分页查询明细
	* @param:@param damagedId
	* @param:@param page
	* @param:@return
	* @return:List<DamagedDrugDetail>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月22日 下午1:53:39
	 */
	@Query("from DamagedDrugDetail d where d.damagedMedicine.damagedId = ?1 ")
	public List<DamagedDrugDetail> queryDamagedDetailByPage(String damagedId,Pageable page);
	
	/**
	* @Title:getDamagedMedicineForNo
	* @Description:根据报损单id查找对应的明细
	* @param:@param damagedId
	* @param:@return
	* @return:List<DamagedDrugDetail>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月6日 下午11:34:56
	 */
	@Query("from DamagedDrugDetail d where d.damagedMedicine.damagedId = ?1 ")
	public List<DamagedDrugDetail> queryByDamagedId(String damagedId);
}
