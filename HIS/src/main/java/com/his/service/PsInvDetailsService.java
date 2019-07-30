package com.his.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**  
* @ClassName: PsInvDetailsService  
* @Description: 库存盘存明细
* @author crazy_long
* @date 2019年7月30日  下午12:20:26
*    
*/

@Service
@Transactional(rollbackFor=Exception.class)
public class PsInvDetailsService {

}
