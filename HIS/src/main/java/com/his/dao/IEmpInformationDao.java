package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.EmpInformation;

/**  
* @ClassName: IEmpInformationDao  
* @Description: 员工信息dao
* @author crazy_long
* @date 2019年7月30日  上午9:52:00
*    
*/
public interface IEmpInformationDao extends CrudRepository<EmpInformation, String>{
	public EmpInformation findEmpInformationByYgGh(String yggh);

}
