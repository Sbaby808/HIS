package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.PurchaseDetail;
import com.his.pojo.PurchaseDetailPK;

/**  
* @ClassName: IPurchaseDetailsDao  
* @Description: 药品采购明细 dao
* @author crazy_long
* @date 2019年7月30日  上午10:45:45
*    
*/
public interface IPurchaseDetailsDao extends CrudRepository<PurchaseDetail, PurchaseDetailPK>{

}
