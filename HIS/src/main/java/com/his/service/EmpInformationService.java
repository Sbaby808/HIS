package com.his.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IEmpInformationDao;
import com.his.dao.ITechnicalPostDao;
import com.his.dao.IWaitingRoomDao;
import com.his.pojo.EmpInformation;
import com.his.pojo.JsonResult;
import com.his.pojo.TechnicalPost;
import com.his.pojo.WaitingRoom;
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
	@Autowired
	private ITechnicalPostDao technicalpostDao;
	@Autowired
	private IWaitingRoomDao waitingroomDao;
	
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
	* @Title:addEmpAllInformation
	* @Description:添加员工的所有信息
	* @param:@param empInformation
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年8月2日 下午10:46:00
	 */
	public void addEmpAllInformation(EmpInformation empInformation) {
		//维护职位id
		String tp_id = empInformation.getTechnicalPost().getTpId();
		if(tp_id.equals("")) {
			TechnicalPost tp = technicalpostDao.findById(tp_id).get();
			empInformation.setTechnicalPost(tp);
		}
		//维护waitingRoomId
		String wid = empInformation.getWaitingRoomId();
		if(wid.equals("")) {
			WaitingRoom wr = waitingroomDao.findById(wid).get();
			empInformation.setWaitingRoom(wr);
		}
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
