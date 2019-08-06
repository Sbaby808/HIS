package com.his.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
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
	
	@Query("from TechnicalPost t")
	public List<TechnicalPost> queryAll();

}
