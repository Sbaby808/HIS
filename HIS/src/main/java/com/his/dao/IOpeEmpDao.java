package com.his.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.bean.Opeempbean;
import com.his.bean.OperationRecordbean;
import com.his.pojo.OpeEmp;
import com.his.pojo.OpeEmpPK;

/**  
* @ClassName: IOpeEmpDao  
* @Description: TODO(手术员工dao)  
* @author TRC
* @date 2019年7月30日  上午9:06:27
*    
*/
public interface IOpeEmpDao extends CrudRepository<OpeEmp, OpeEmpPK>{
	/**
	 * 
	* @Title:getOpeEmps
	* @Description:TODO根据手术记录id查员工
	* @param:@param ygxh
	* @param:@return
	* @return:List<Opeempbean>
	* @throws
	* @author:TRC
	* @Date:2019年8月6日 下午4:32:27
	 */
	@Query(value="select new com.his.bean.Opeempbean(o.empInformation.ygName,o.duty,o.empInformation.ygGh) from OpeEmp o where o.operationRecord.opeId=?1")
	public List<Opeempbean> getOpeEmps(String opeid);
	
}
