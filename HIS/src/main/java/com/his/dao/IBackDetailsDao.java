package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.BackDetail;
import com.his.pojo.BackDetailPK;

/**  
* @ClassName: IBackDetailsDao  
* @Description: 药品退药明细Dao
* @author crazy_long
* @date 2019年7月30日  上午10:44:34
*    
*/
public interface IBackDetailsDao extends CrudRepository<BackDetail, BackDetailPK>{

}
