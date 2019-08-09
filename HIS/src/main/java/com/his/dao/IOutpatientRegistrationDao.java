package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.OutpatientRegistration;

/**  
* @ClassName: IOutpatientRegistrationDao  
* @Description: 门诊挂号Dao
* @author Sbaby
* @date 2019年8月9日  下午4:25:13
*    
*/
public interface IOutpatientRegistrationDao extends CrudRepository<OutpatientRegistration, String> {

}
