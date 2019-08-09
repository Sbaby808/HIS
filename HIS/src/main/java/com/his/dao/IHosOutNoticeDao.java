package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.HosOutNotice;


/**
 * 
* @ClassName: IHosOutNotice  
* @Description: 出院通知单  
* @author Hamster
* @date 2019年8月3日  上午11:29:53
*
 */
public interface IHosOutNoticeDao extends CrudRepository<HosOutNotice, String>{
	
	/**
	 * 
	* @Title:getAllHosOutNotic
	* @Description:分页查询所有出院通知单
	* @param:@return
	* @return:List<HosOutNotice>
	* @throws
	* @author:Hamster
	* @Date:2019年8月3日 上午11:30:15
	 */
	@Query("from HosOutNotice h")
	public List <HosOutNotice> getHosOutNoticeByPage(Pageable page);
	
	
}
