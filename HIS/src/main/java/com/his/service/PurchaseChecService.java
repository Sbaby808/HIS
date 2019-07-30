package com.his.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**  
* @ClassName: PurchaseChecService  
* @Description: 采购验收service
* @author crazy_long
* @date 2019年7月30日  下午2:12:16
*    
*/

@Service
@Transactional(rollbackFor=Exception.class)
public class PurchaseChecService {

}
