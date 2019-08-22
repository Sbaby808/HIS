package com.his.dao;


import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.EmpInformation;
import com.his.pojo.WktimeEmp;
import com.his.pojo.WktimeEmpPK;
import com.his.pojo.WorkTime;

/**  
* @ClassName: WktimeempDao  
* @Description: TODO(排班员工dao)  
* @author TRC
* @date 2019年8月21日  下午2:30:33
*    
*/
public interface IWktimeempDao extends CrudRepository<WktimeEmp, WktimeEmpPK>{
	@Query(value="select w.empInformation from WktimeEmp w where w.workTime.pbId=?1 and w.wktimeDuty='主任医生'")
	public List<EmpInformation> getbyidzr(String id);
	@Query(value="select w.empInformation from WktimeEmp w where w.workTime.pbId=?1 and w.wktimeDuty='副主任医生'")
	public List<EmpInformation> getbyidfzr(String id);
	@Query(value="select w.empInformation from WktimeEmp w where w.workTime.pbId=?1 and w.wktimeDuty='护士'")
	public List<EmpInformation> getbyidhs(String id);
	@Query(value="select w.workTime  from WktimeEmp w where w.empInformation.ygxh=?1 and w.workTime.pbDate=?2")
	public List<WorkTime> getbytimeandid(String id,Date date);

}
