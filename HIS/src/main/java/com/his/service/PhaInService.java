package com.his.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**  
* @ClassName: PhaInService  
* @Description: 药房入库service
* @author crazy_long
* @date 2019年7月30日  下午12:18:03
*    
*/

@Service
@Transactional(rollbackFor=Exception.class)
public class PhaInService {

}
