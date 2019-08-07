package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.Supplier;

/**  
* @ClassName: ISupplierDao  
* @Description: 药品供应商dao
* @author crazy_long
* @date 2019年8月6日  下午10:35:36
*    
*/
public interface ISupplierDao extends CrudRepository<Supplier, String>{
	
	@Query("from Supplier s")
	public List<Supplier> queryByPage(Pageable page);
	

}
