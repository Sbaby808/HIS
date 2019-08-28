package com.his.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.AbuEmp;
import com.his.pojo.AbuEmpPK;

/**  
* @ClassName: IAbuEmpDAO  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author jack
* @date 2019年8月26日  下午5:28:42
*    
*/
public interface IAbuEmpDAO extends CrudRepository<AbuEmp, AbuEmpPK>{
	//找到该员工序号对应的救护车状态，看是不是在执行任务
	@Query("select ae.ambulanceRecord.status from AbuEmp ae where ae.empInformation.ygxh=?1")
	public String findjiuhuche(String ygxh);
}
