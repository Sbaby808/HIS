package com.his.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.OutpatientPay;

/**  
* @ClassName: IOutpatientPayDao
* @Description: 门诊其他缴费记录Dao
* @author Sbaby
* @date 2019年8月3日  上午10:03:56
*    
*/
public interface IOutpatientPayDao extends CrudRepository<OutpatientPay, String> {

	/**
	* @Title:checkRegPay
	* @Description:查询此挂号单的缴费信息
	* @param:@param regId
	* @param:@return
	* @return:int
	* @throws
	* @author:Sbaby
	* @Date:2019年8月13日 下午4:06:30
	 */
	@Query("from OutpatientPay op where op.outpatientRegistration.regId = ?1")
	public OutpatientPay checkRegPay(String regId);
	
}
