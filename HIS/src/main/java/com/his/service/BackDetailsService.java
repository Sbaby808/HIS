package com.his.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**  
* @ClassName: BackDetailsService  
* @Description:药品退药明细service
* @author crazy_long
* @date 2019年7月30日  上午11:58:39
*    
*/

@Service
@Transactional(rollbackFor=Exception.class)
public class BackDetailsService {

}
