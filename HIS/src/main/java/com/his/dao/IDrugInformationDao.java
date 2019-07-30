package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.DrugInformation;

/**  
* @ClassName: IDrugInformationDao  
* @Description: 药品信息dao
* @author crazy_long
* @date 2019年7月30日  上午10:24:32
*    
*/
public interface IDrugInformationDao extends CrudRepository<DrugInformation, String>{

}
