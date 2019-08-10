package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.HosCheckNotice;

/**
 * 
* @ClassName: IHosCheckNoticeDao  
* @Description:住院检查通知单 
* @author Hamster
* @date 2019年8月10日  下午6:33:17
*
 */
public interface IHosCheckNoticeDao extends CrudRepository<HosCheckNotice, String>{

	
}
