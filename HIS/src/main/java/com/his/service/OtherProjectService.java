package com.his.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IOtherProjectDao;
import com.his.pojo.OtherProject;

/**  
* @ClassName: OtherProjectService  
* @Description: 其他收费项Service
* @author Sbaby
* @date 2019年8月3日  上午9:06:25
*    
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class OtherProjectService {
	
	@Autowired
	private IOtherProjectDao otherProjectDao;
	
	/**
	* @Title:addOtherProject
	* @Description:添加其他收费项
	* @param:@param otherProject
	* @return:void
	* @throws
	* @author:Sbaby
	* @Date:2019年8月3日 上午9:08:09
	 */
	public void addOtherProject(OtherProject otherProject) {
		otherProject.setProjectId(UUID.randomUUID().toString().replaceAll("-", ""));
		otherProject.setProjectTime(new Date());
		otherProjectDao.save(otherProject);
	}

}
