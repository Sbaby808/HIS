package com.his.dao;

import java.util.List;

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
