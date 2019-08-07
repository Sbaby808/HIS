package com.his.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IRoleDao;
import com.his.pojo.Role;
import com.his.utils.ServiceException;

/**  
* @ClassName: RoleService  
* @Description: 职位service
* @author crazy_long
* @date 2019年7月30日  下午2:21:10
*    
*/
@Service
@Transactional(rollbackFor=Exception.class)
public class RoleService {
	
	@Autowired
	private IRoleDao roledao;
	
	/**
	* @Title:addRole
	* @Description:添加role测试
	* @param:@param role
	* @param:@throws ServiceException
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年8月6日 上午11:48:31
	 */
	public void addRole(Role role)throws ServiceException  {
		try {
			roledao.save(role);
		} catch (Exception e) {
			new ServiceException("插入role失败");
		}
	}

}
