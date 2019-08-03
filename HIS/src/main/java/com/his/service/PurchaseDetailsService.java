package com.his.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**  
* @ClassName: PurchaseDetailsService  
* @Description:药品采购明细
* @author crazy_long
* @date 2019年7月30日  下午2:14:29
*    
*/

@Service
@Transactional(rollbackFor=Exception.class)
public class PurchaseDetailsService {
	
	public void addPurchaseMX() {
		
	}

}
