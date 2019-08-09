package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
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
	
	@Query("from DrugSubclass d")
	public List<DrugSubclass> qeuryAllByPage(Pageable page);

}
