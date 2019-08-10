package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.HosSurgeryNotice;

/**
 * 
* @ClassName: IHosSurgeryNoticeDao  
* @Description: 住院手术通知单 
* @author Hamster
* @date 2019年8月10日  下午6:59:31
*
 */
public interface IHosSurgeryNoticeDao extends CrudRepository<HosSurgeryNotice, String>{

	
}
