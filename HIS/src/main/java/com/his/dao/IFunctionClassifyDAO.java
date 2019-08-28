package com.his.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.FunctionClassify;

/**  
* @ClassName: IFunctionClassifyDAO  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author jack
* @date 2019年8月19日  上午9:12:21
*    
*/
public interface IFunctionClassifyDAO extends CrudRepository<FunctionClassify, String>{
	@Query("select count(*) from FunctionClassify where funcClassifyName = ?1")
	public long ffindnamecount(String funcClassifyName);
}
