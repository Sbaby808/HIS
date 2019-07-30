package com.his.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**  
* @ClassName: ReqDetailsService  
* @Description: 药品申领明细service
* @author crazy_long
* @date 2019年7月30日  下午2:20:10
*    
*/
@Service
@Transactional(rollbackFor=Exception.class)
public class ReqDetailsService {

}
