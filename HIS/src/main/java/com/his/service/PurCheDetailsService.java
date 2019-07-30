package com.his.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**  
* @ClassName: PurCheDetailsService  
* @Description: 采购验收明细service
* @author crazy_long
* @date 2019年7月30日  下午2:15:51
*    
*/

@Service
@Transactional(rollbackFor=Exception.class)
public class PurCheDetailsService {

}
