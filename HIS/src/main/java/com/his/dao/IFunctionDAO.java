package com.his.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.Function;

/**  
* @ClassName: IFunctionDAO  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author jack
* @date 2019年8月17日  上午8:42:40
*    
*/
public interface IFunctionDAO extends CrudRepository<Function, String>{
	@Query("select count(*) from Function where functionName = ?1")
	public long findcountquanxian(String functionName);
}
