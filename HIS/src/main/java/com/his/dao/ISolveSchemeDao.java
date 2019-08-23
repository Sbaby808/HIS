package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.SolveScheme;

/**  
* @ClassName: ISolveSchemeDao  
* @Description: 医嘱Dao
* @author Sbaby
* @date 2019年8月23日  上午9:14:49
*    
*/
public interface ISolveSchemeDao extends CrudRepository<SolveScheme, String> {

}
