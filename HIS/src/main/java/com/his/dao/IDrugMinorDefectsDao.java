package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.DrugMinorDefect;

/**  
* @ClassName: IDrugMinorDefectsDao  
* @Description: 药品中类dao
* @author crazy_long
* @date 2019年7月30日  上午10:22:22
*    
*/
public interface IDrugMinorDefectsDao extends CrudRepository<DrugMinorDefect, String>{

}
