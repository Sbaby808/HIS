package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.UseDrugRecord;

/**  
* @ClassName: IUseDrugRecordDao  
* @Description: 用药记录Dao
* @author Sbaby
* @date 2019年9月3日  下午4:28:12
*    
*/
public interface IUseDrugRecordDao extends CrudRepository<UseDrugRecord, String> {

}
