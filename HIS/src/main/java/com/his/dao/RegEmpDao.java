package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.RegEmp;
import com.his.pojo.RegEmpPK;

/**  
* @ClassName: RegEmpDao  
* @Description: 挂号员工Dao
* @author Sbaby
* @date 2019年8月12日  下午2:05:08
*    
*/
public interface RegEmpDao extends CrudRepository<RegEmp, RegEmpPK> {

}
