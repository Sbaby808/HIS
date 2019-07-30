package com.his.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**  
* @ClassName: AllocationOutboundService  
* @Description: 药品调拨出库service
* @author crazy_long
* @date 2019年7月30日  上午11:52:07
*    
*/

@Service
@Transactional(rollbackFor=Exception.class)
public class AllocationOutboundService {
	

}
