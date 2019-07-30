package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.DrugScrap;

/**  
* @ClassName: IDrugScrapDao  
* @Description: 药品报废表dao
* @author crazy_long
* @date 2019年7月30日  上午10:40:07
*    
*/
public interface IDrugScrapDao extends CrudRepository<DrugScrap, String>{

}
