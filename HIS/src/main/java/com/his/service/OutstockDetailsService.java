package com.his.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**  
* @ClassName: OutstockDetailsService  
* @Description: 药库出库明细
* @author crazy_long
* @date 2019年7月30日  下午12:17:04
*    
*/

@Service
@Transactional(rollbackFor=Exception.class)
public class OutstockDetailsService {

}
