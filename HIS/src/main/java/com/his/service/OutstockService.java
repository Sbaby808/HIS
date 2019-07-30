package com.his.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**  
* @ClassName: OutstockService  
* @Description: 药库出库信息service
* @author crazy_long
* @date 2019年7月30日  下午12:16:02
*    
*/
@Service
@Transactional(rollbackFor=Exception.class)
public class OutstockService {

}
