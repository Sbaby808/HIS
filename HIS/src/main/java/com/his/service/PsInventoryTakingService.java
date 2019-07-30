package com.his.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**  
* @ClassName: PsInventoryTakingService  
* @Description: 药库库存信息service
* @author crazy_long
* @date 2019年7月30日  下午2:11:12
*    
*/
@Service
@Transactional(rollbackFor=Exception.class)
public class PsInventoryTakingService {

}
