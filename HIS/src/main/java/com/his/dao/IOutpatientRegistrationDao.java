package com.his.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.OutpatientRegistration;

/**  
* @ClassName: IOutpatientRegistrationDao  
* @Description: 门诊挂号Dao
* @author Sbaby
* @date 2019年8月9日  下午4:25:13
*    
*/
public interface IOutpatientRegistrationDao extends CrudRepository<OutpatientRegistration, String> {

	/**
	* @Title:getRegsitrationCountByEmpId
	* @Description:根据医生编号查询当日挂号数量
	* @param:@param empId
	* @param:@return
	* @return:int
	* @throws
	* @author:Sbaby
	* @Date:2019年8月10日 下午2:33:06
	 */
	@Query(value = "select count(*) from reg_emp re left outer join outpatient_registration r on re.reg_id = r.reg_id "
       + " where to_char(r.do_date,'yyyy-mm-dd') = to_char(sysdate, 'yyyy-mm-dd') and re.ygxh = ?1 and r.reg_status != '退号'", nativeQuery = true)
	public int getRegsitrationCountByEmpId(String empId);
	
	
	
}
