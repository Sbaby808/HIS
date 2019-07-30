package com.his.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**  
* @ClassName: PurchaseService  
* @Description: 药品采购service
* @author crazy_long
* @date 2019年7月30日  下午2:13:29
*    
*/

@Service
@Transactional(rollbackFor=Exception.class)
public class PurchaseService {

}
