package com.his.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**  
* @ClassName: PsBackDetailsService  
* @Description: 药库退药明细service
* @author crazy_long
* @date 2019年7月30日  下午12:19:12
*    
*/

@Service
@Transactional(rollbackFor=Exception.class)
public class PsBackDetailsService {

}
