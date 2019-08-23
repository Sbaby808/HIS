package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.OtherAdvice;

/**  
* @ClassName: OtherAdviceDao  
* @Description: 门诊医嘱其他建议Dao
* @author Sbaby
* @date 2019年8月22日  下午5:42:02
*    
*/
public interface IOtherAdviceDao extends CrudRepository<OtherAdvice, String> {

}
