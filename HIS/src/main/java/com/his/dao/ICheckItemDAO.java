package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.CheckItem;

/**  
* @ClassName: ICheckItemDAO  
* @Description: TODO(检查小项dao)  
* @author TRC
* @date 2019年7月30日  上午9:36:30
*    
*/
public interface ICheckItemDAO extends CrudRepository<CheckItem, String>{
	@Query(value="from CheckItem c where c.itemName like ?1")
	public List<CheckItem> getallcheck(String sou,Pageable page);
	@Query(value="select count(1) from CheckItem c where c.itemName like ?1")
	public long getcount(String sou);

}
