package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.OtherProject;

/**  
* @ClassName: IOtherProjectDao  
* @Description: 其他收费项Dao
* @author Sbaby
* @date 2019年8月3日  上午9:05:22
*    
*/
public interface IOtherProjectDao extends CrudRepository<OtherProject, Long> {

}
