package com.his.dao;

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

}
