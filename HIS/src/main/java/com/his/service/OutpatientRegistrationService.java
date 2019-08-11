package com.his.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IDepartmentDao;
import com.his.dao.IEmpInformationDao;
import com.his.dao.IOutpatientRegistrationDao;
import com.his.dao.ITechnicalPostDao;
import com.his.dao.IWorkTimeDao;
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
	@Autowired
	private IWorkTimeDao workTimeDao;
	
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
	public List<WorkTime> getWorktimeByEmpid(String empId) {
		return workTimeDao.getDocById(empId);
	}
	
	/**
	* @Title:getRegistrationCount
	* @Description:根据医生编号查询当日挂号总数
	* @param:@param empId
	* @param:@return
	* @return:int
	* @throws
	* @author:Sbaby
	* @Date:2019年8月10日 下午2:06:22
	 */
	public int getRegistrationCount(String empId) {
		return outpatientRegistrationDao.getRegsitrationCountByEmpId(empId);
	}
	
	/**
	* @Title:getOutpatientDoctorCount
	* @Description:查询门诊所有科室的医生挂号详情
	* @param:@return
	* @return:Map<String,Map<EmpInformation,Integer>>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月10日 下午2:56:27
	 */
	public Map<String, List<Map<String, String>>> getOutpatientDoctorCount() {
		Map<String, List<Map<String, String>>> map = new HashMap<String, List<Map<String, String>>>();
		// 查询所有科室
		List<Department> departments = getKSbyOut();
		for (Department department : departments) {
			// 查询该科室下今日排班的所有医生
			List<Map<String, String>> list = new ArrayList<>();
			List<EmpInformation> doctors = empInformationDao.getDoctorsByWkAndKs(department.getKsId());
			for (EmpInformation empInformation : doctors) {
				// 查询该医生今日已有的挂号数量
//				Object[] objs = {empInformation.getYgName(), 
//						empInformation.getTechnicalPost().getTpId(), 
//						getRegistrationCount(empInformation.getYgxh()),
//						department.getKsId()};
				Map<String, String> mmap = new HashMap<>();
				mmap.put("ygName", empInformation.getYgName());
				mmap.put("tpName", empInformation.getTechnicalPost().getTpName());
				mmap.put("count", getRegistrationCount(empInformation.getYgxh()) + "");
				list.add(mmap);
			}
			map.put(department.getKsName(), list);
		}
		return map;
	}
}
