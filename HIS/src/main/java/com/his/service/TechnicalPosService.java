package com.his.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.ITechnicalPostDao;
import com.his.pojo.TechnicalPost;
import com.his.utils.CreateUUID;

/**  
* @ClassName: TechnicalPosService  
* @Description: 职称表 service
* @author crazy_long
* @date 2019年8月3日  上午12:22:00
*    
*/
@Service
@Transactional(rollbackFor=Exception.class)
public class TechnicalPosService {
	
	@Autowired
	private ITechnicalPostDao technicalPosdao;
	
	/**
	 * 
	* @Title:queryAllTechnicalPost
	* @Description:查找所有的职称信息
	* @param:@return
	* @return:List<TechnicalPost>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月3日 上午12:30:51
	 */
	public List<TechnicalPost> queryAllTechnicalPost(){
		return (List<TechnicalPost>) technicalPosdao.findAll();
	}
	
	/**
	 * 
	* @Title:addTechnicalPost
	* @Description:添加职位
	* @param:@param technicalPost
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年8月3日 上午12:56:37
	 */
	public void addTechnicalPost(TechnicalPost technicalPost) {
		technicalPost.setTpId(CreateUUID.getUUID_32());
		technicalPosdao.save(technicalPost);
	}

}
