package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.TechnicalPost;

/**  
* @ClassName: ITechnicalPostDao  
* @Description: 职称表Dao
* @author crazy_long
* @date 2019年8月2日  下午11:51:49
*    
*/
public interface ITechnicalPostDao extends CrudRepository<TechnicalPost, String>{

}
