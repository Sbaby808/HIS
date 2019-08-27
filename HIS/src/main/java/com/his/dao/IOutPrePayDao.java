package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.OutPrePay;

/**  
* @ClassName: IOutPrePayDao  
* @Description: 门诊处方缴费记录Dao
* @author Sbaby
* @date 2019年8月26日  上午11:50:39
*    
*/
public interface IOutPrePayDao extends CrudRepository<OutPrePay, String> {

}
