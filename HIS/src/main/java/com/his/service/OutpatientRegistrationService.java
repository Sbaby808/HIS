package com.his.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IDepartmentDao;
import com.his.dao.IOutpatientRegistrationDao;
import com.his.dao.ITechnicalPostDao;
import com.his.pojo.Department;
import com.his.pojo.TechnicalPost;

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
}
