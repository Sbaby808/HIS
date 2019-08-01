package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.BedTransRecord;

/**
 * 
* @ClassName: ITransBed  
* @Description: TODO住院转床记录  
* @author Hamster
* @date 2019年7月31日  下午8:54:44
*
 */
public interface ITransBedDao extends CrudRepository<BedTransRecord,String>{

}
