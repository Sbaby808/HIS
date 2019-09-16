package com.his.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IUserRoleDao;
import com.his.pojo.EmpInformation;
import com.his.pojo.UserRole;

/**  
* @ClassName: UserRoleService  
* @Description: 职位_员工service
* @author crazy_long
* @date 2019年7月30日  下午2:23:05
*    
*/
@Service
@Transactional(rollbackFor=Exception.class)
public class UserRoleService {
	
	@Autowired
	private IUserRoleDao userroledao;
	
	/**
	* @Title:getYKManager
	* @Description:获取药库管理员
	* @param:@return
	* @return:List<UserRole>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月15日 下午3:49:54
	 */
	public List<EmpInformation> getYKManager(){
		List<EmpInformation> empList = new ArrayList<EmpInformation>();
		List<UserRole> list = userroledao.getYKManager();
		System.out.println("---------------------");
		System.out.println("共有"+list.size()+"个药房管理员");
		if(list.size()!=0) {
			for (UserRole userRole : list) {
				EmpInformation emp = userRole.getEmpInformation();
				empList.add(emp);
			}
		}
		return empList;
	}
	
	/**
	* @Title:queryAllRoleByYgxh
	* @Description:根据员工序号查询该员工对应的所有角色
	* @param:@param ygxh
	* @param:@return
	* @return:List<UserRole>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月6日 下午4:48:41
	 */
	public List<UserRole> queryAllRoleByYgxh(String ygxh){
		return userroledao.queryAllRoleByYgxh(ygxh);
	}

}
