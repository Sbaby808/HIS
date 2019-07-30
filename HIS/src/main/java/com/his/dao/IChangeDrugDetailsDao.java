package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.ChangeDrugDetail;
import com.his.pojo.ChangeDrugDetailPK;

/**  
* @ClassName: IChangeDrugDetailsDao  
* @Description: 调拨出库明细dao
* @author crazy_long
* @date 2019年7月30日  上午11:05:29
*    
*/
public interface IChangeDrugDetailsDao extends CrudRepository<ChangeDrugDetail,ChangeDrugDetailPK>{

}
