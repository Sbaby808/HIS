package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.DamagedMedicine;

/**  
* @ClassName: IDamagedMedicine  
* @Description: 药品报损dao 
* @author crazy_long
* @date 2019年7月30日  上午10:18:58
*    
*/
public interface IDamagedMedicineDao extends CrudRepository<DamagedMedicine, String>{
	
	/**
	* @Title:getDamagedMedicineForYes
	* @Description:分页获取所有‘已完成’的申领单
	* @param:@param page
	* @param:@return
	* @return:List<DamagedMedicine>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月22日 下午1:16:08
	 */
	@Query("from DamagedMedicine d where d.state = '已完成'  order by d.damagedTime desc ")
	public List<DamagedMedicine> getDamagedMedicineForYes(Pageable page);
	
	/**
	* @Title:getDamagedMedicineForYes
	* @Description:分页获取所有‘已完成’的申领单的条数
	* @param:@param page
	* @param:@return
	* @return:List<DamagedMedicine>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月22日 下午1:17:05
	 */
	@Query("select count(*) from DamagedMedicine d where d.state = '已完成' ")
	public int getDamagedMedicineForYesCount();
	
	/**
	* @Title:getDamagedMedicineForNo
	* @Description:获取所有未完成的申领单
	* @param:@return
	* @return:List<DamagedMedicine>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月6日 下午4:51:25
	 */
	@Query("from DamagedMedicine d where d.state = '未完成' ")
	public List<DamagedMedicine> getDamagedMedicineForNo();

}
