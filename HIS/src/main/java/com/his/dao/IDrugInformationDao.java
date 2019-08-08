package com.his.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.DrugInformation;

/**  
* @ClassName: IDrugInformationDao  
* @Description: 药品信息dao
* @author crazy_long
* @date 2019年7月30日  上午10:24:32
*    
*/
public interface IDrugInformationDao extends CrudRepository<DrugInformation, String>{

	/**
	* @Title:getNoPrice
	* @Description:查询未划价的药品
	* @param:@return
	* @return:List<DrugInformation>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月7日 上午9:22:56
	 */
	@Query(value="select * from drug_information d where d.medicine_pay_id is null",nativeQuery=true)
	public List<DrugInformation> getNoPrice();
	
	/**
	 * 
	* @Title:getAllDrugInformation
	* @Description:模糊查询
	* @param:@return
	* @return:List<DrugInformation>
	* @throws
	* @author:Hamster
	* @Date:2019年8月7日 下午9:12:41
	 */
	@Query("from DrugInformation d where d.ypName like ?1")
	public List <DrugInformation> getDrugInformation(String ypName);
}
