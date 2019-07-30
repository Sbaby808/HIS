package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.PurCheDetail;
import com.his.pojo.PurCheDetailPK;

/**  
* @ClassName: IPurCheDetailsDao  
* @Description: 采购验收明细dao
* @author crazy_long
* @date 2019年7月30日  上午11:09:44
*    
*/
public interface IPurCheDetailsDao extends CrudRepository<PurCheDetail, PurCheDetailPK>{

}
