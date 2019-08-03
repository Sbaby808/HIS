package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.OpeDrugDetail;
import com.his.pojo.OpeDrugDetailPK;

/**  
* @ClassName: IOpeDrugDetailDao  
* @Description: TODO(手术用药详情)  
* @author TRC
* @date 2019年7月30日  上午9:10:49
*    
*/

public interface IOpeDrugDetailDao extends CrudRepository<OpeDrugDetail, OpeDrugDetailPK>{

}
