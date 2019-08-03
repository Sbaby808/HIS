package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.CheckResultDetail;
import com.his.pojo.CheckResultDetailPK;

/**  
* @ClassName: CheckResultDetailDao  
* @Description: TODO(检查结果详情)  
* @author TRC
* @date 2019年7月30日  上午9:52:58
*    
*/
public interface ICheckResultDetailDao extends CrudRepository<CheckResultDetail, CheckResultDetailPK>{

}
