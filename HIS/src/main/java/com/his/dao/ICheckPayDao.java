package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
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
	@Query(value="from CheckPay c where c.checkPayName like?1")
	public List<CheckPay> getcheckbysou(String sou,Pageable page);
	@Query(value="select count(1) from CheckPay c where c.checkPayName like?1")
	public long getcount(String sou);
	
	/**
	* @Title:searchCheck
	* @Description:模糊查询检查项
	* @param:@param key
	* @param:@return
	* @return:List<CheckPay>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月23日 下午9:30:23
	 */
	@Query("from CheckPay c where c.checkPayName like ?1 or c.checkPayDesc like ?1")
	public List<CheckPay> searchCheck(String key, Pageable pageable);
}
