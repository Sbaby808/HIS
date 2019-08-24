package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.OutPreItem;
import com.his.pojo.OutPreItemPK;

/**  
* @ClassName: IOutPreItemDao  
* @Description: 门诊处方明细Dao
* @author Sbaby
* @date 2019年8月22日  上午11:02:27
*    
*/
public interface IOutPreItemDao extends CrudRepository<OutPreItem, OutPreItemPK> {

}
