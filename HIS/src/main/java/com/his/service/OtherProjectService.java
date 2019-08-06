package com.his.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IOtherProjectDao;
import com.his.pojo.OtherProject;
import com.sun.xml.xsom.impl.WildcardImpl.Other;

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
		if(otherProject.getProjectId() == null) {
			otherProject.setProjectId(UUID.randomUUID().toString().replaceAll("-", ""));
		}
		otherProject.setProjectTime(new Date());
		otherProjectDao.save(otherProject);
	}
	
	/**
	* @Title:getOtherPeojectById
	* @Description:根据其他收费项id查询其他收费项
	* @param:@param id
	* @param:@return
	* @return:OtherProjectService
	* @throws
	* @author:Sbaby
	* @Date:2019年8月3日 上午9:36:55
	 */
	public OtherProject getOtherPeojectById(String id) {
		return otherProjectDao.findById(id).get();
	}
	
	/**
	 * 
	* @Title:getHosProject
	* @Description:查询住院床位的收费项
	* @param:@return
	* @return:List<OtherProject>
	* @throws
	* @author:Hamster
	* @Date:2019年8月4日 下午9:43:50
	 */
	public List <OtherProject> getHosBedProject(){
		return otherProjectDao.getHosBedProject();
	}
	
	/**
	* @Title:getAll
	* @Description:查询所有其他收费项
	* @param:@return
	* @return:List<OtherProject>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月6日 上午9:51:29
	 */
	public List<OtherProject> getAll() {
		List<OtherProject> list = (List<OtherProject>) otherProjectDao.findAll();
		return list;
	}
	
	/**
	* @Title:getByPage
	* @Description:分页查询其他收费项
	* @param:@param pageNum
	* @param:@param pageSize
	* @param:@return
	* @return:List<OtherProject>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月6日 上午10:26:28
	 */
	public List<OtherProject> getByPage(int pageNum, int pageSize) {
		PageRequest page = PageRequest.of(pageNum - 1, pageSize, Direction.ASC, "projectName");
		return otherProjectDao.getByPage(page);
	}
	
	/**
	* @Title:getCount
	* @Description:查询其他收费项总记录条数
	* @param:@return
	* @return:int
	* @throws
	* @author:Sbaby
	* @Date:2019年8月6日 上午10:37:13
	 */
	public int getCount() {
		return otherProjectDao.getCount();
	}

}
