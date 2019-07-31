package com.his.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IEmpInformationDao;
import com.his.pojo.EmpInformation;
import com.his.pojo.JsonResult;
import com.his.utils.MD5Tools;

/**  
* @ClassName: EmpInformationService  
* @Description: 员工信息service
* @author crazy_long
* @date 2019年7月30日  下午12:13:13
*    
*/

@Service
@Transactional(rollbackFor=Exception.class)
public class EmpInformationService {
	
	@Autowired
	private IEmpInformationDao empInformationDao;
	
	/**
	 * 
	* @Title:addTestEmp
	* @Description:添加测试员工信息
	* @param:@param empInformation
	* @return:void
	* @throws
	* @author:Sbaby
	* @Date:2019年7月31日 下午2:02:29
	 */
	public void addTestEmp(EmpInformation empInformation) {
		empInformationDao.save(empInformation);
	}
	
	/**
	 * 
	* @Title:loginTestEmp
	* @Description:员工登录验证
	* @param:@param empInformation
	* @return:void
	* @throws
	* @author:Sbaby
	* @Date:2019年7月31日 下午2:37:29
	 */
	public boolean loginTestEmp(EmpInformation empInformation) {
		EmpInformation emp = empInformationDao.findById(empInformation.getYgxh()).get();
		if(MD5Tools.check(empInformation.getYgPassword(), emp.getYgPassword())) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 获得员工信息
	* @Title:getEmpInfoById
	* @Description:TODO
	* @param:@param empId
	* @param:@return
	* @return:EmpInformation
	* @throws
	* @author:Sbaby
	* @Date:2019年7月31日 下午3:15:42
	 */
	public EmpInformation getEmpInfoById(String empId) {
		return empInformationDao.findById(empId).get();
	}

}
