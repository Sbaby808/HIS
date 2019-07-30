package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.PsInvDetail;
import com.his.pojo.PsInvDetailPK;

/**  
* @ClassName: IPsInvDetailsDao  
* @Description: 药库盘存明细dao
* @author crazy_long
* @date 2019年7月30日  上午10:52:58
*    
*/
public interface IPsInvDetailsDao extends CrudRepository<PsInvDetail, PsInvDetailPK>{

}
