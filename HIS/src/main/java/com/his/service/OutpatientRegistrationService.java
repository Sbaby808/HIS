package com.his.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IDepartmentDao;
import com.his.dao.IEmpInformationDao;
import com.his.dao.IOutpatientRegistrationDao;
import com.his.dao.ITechnicalPostDao;
import com.his.pojo.Department;
import com.his.pojo.EmpInformation;
import com.his.pojo.TechnicalPost;
import com.his.pojo.WorkTime;

/**  
* @ClassName: OutpatientRegistrationService  
* @Description: 门诊挂号Service
* @author Sbaby
* @date 2019年8月9日  下午4:24:16
*    
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class OutpatientRegistrationService {

	@Autowired
	private IOutpatientRegistrationDao outpatientRegistrationDao;
	@Autowired
	private IDepartmentDao departmentDao;
	@Autowired
	private ITechnicalPostDao technicalPostDao;
	@Autowired
	private IEmpInformationDao empInformationDao;
	
	/**
	* @Title:getKSbyOut
	* @Description:查询门诊的所有科室
	* @param:@return
	* @return:List<Department>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月9日 下午4:28:45
	 */
	public List<Department> getKSbyOut() {
		return departmentDao.getKSByOut("outpatient");
	}
	
	/**
	* @Title:getTpbyOut
	* @Description:查询门诊的所有职称
	* @param:@return
	* @return:List<TechnicalPost>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月9日 下午4:57:49
	 */
	public List<TechnicalPost> getTpbyOut() {
		return technicalPostDao.getByOut("outpatient");
	}
	
	/**
	* @Title:getDocByKsAndTp
	* @Description:根据科室和职称查询门诊医生
	* @param:@param ks
	* @param:@param tp
	* @param:@return
	* @return:List<EmpInformation>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月10日 上午8:50:39
	 */
	public List<EmpInformation> getDocByKsAndTp(String ks, String tp) {
		return empInformationDao.getDocByKsAndTp(tp, ks);
	}
	
	/**
	* @Title:getWorktimeByEmpid
	* @Description:根据医生编号查询排班时间（当天与第二天的）
	* @param:@param empId
	* @param:@return
	* @return:List<WorkTime>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月10日 上午9:25:32
	 */
//	public List<WorkTime> getWorktimeByEmpid(String empId) {
//		
//	}
}
