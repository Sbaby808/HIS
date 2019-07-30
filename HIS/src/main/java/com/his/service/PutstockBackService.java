package com.his.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**  
* @ClassName: PutstockBackService  
* @Description: 药库退药service
* @author crazy_long
* @date 2019年7月30日  下午2:17:00
*    
*/

@Service
@Transactional(rollbackFor=Exception.class)
public class PutstockBackService {

}
