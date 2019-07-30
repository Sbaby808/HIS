package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.DrugSubclass;

/**  
* @ClassName: IDrugSubclassDao  
* @Description: 药品小类dao
* @author crazy_long
* @date 2019年7月30日  上午10:36:47
*    
*/
public interface IDrugSubclassDao extends CrudRepository<DrugSubclass, String>{

}
