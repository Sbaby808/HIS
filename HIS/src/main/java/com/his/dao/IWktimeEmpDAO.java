package com.his.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.WktimeEmp;
import com.his.pojo.WktimeEmpPK;
import com.his.pojo.WorkTime;

/**  
* @ClassName: IWktimeEmpDAO  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author jack
* @date 2019年8月26日  上午11:42:25
*    
*/
public interface IWktimeEmpDAO extends CrudRepository<WktimeEmp, WktimeEmpPK>{
	
}
