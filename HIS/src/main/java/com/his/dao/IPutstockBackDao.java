package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.PutstockBack;

/**  
* @ClassName: IPutstockBackDao  
* @Description: 药库退药信息dao
* @author crazy_long
* @date 2019年7月30日  上午10:54:17
*    
*/
public interface IPutstockBackDao extends CrudRepository<PutstockBack, String>{

}
