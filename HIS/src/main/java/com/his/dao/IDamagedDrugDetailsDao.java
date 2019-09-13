package com.his.dao;

import java.util.List;

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
