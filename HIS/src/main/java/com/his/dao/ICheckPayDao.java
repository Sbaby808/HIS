package com.his.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.CheckPay;

/**  
* @ClassName: ICheckPayDao  
* @Description: TODO(检查划价dao)  
* @author TRC
* @date 2019年7月30日  上午9:41:05
*    
*/
public interface ICheckPayDao extends CrudRepository<CheckPay, String>{

	/**
	 * 
	* @Title:getAllCheckPay
	* @Description:查询所有检查收费项
	* @param:@return
	* @return:List<CheckPay>
	* @throws
	* @author:Hamster
	* @Date:2019年8月10日 上午11:02:08
	 */
	@Query("from CheckPay c")
	public List <CheckPay> getAllCheckPay();
}
