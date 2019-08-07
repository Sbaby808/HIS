package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
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

	@Query("from EmpInformation e left outer join e.technicalPost t")
	public List<EmpInformation> queryByPage(Pageable page);
			
	public List<EmpInformation> findByygGh(String ygGh);
	
	@Query("from EmpInformation e where e.ygName = ?1")
	public List<EmpInformation> qureyByygName(String ygName);
	
	@Query("select e.ygxh,e.ygName from EmpInformation e")
	public List<Object[]> queryAllForNameAndXH();
}
