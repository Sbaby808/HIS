package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.ReqDetail;
import com.his.pojo.ReqDetailPK;

/**  
* @ClassName: IReqDetailsDao  
* @Description: 药品申领明细
* @author crazy_long
* @date 2019年7月30日  上午10:43:23
*    
*/
public interface IReqDetailsDao extends CrudRepository<ReqDetail, ReqDetailPK>{

}
