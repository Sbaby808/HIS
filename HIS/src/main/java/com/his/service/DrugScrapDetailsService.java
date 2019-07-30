package com.his.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**  
* @ClassName: DrugScrapDetailsService  
* @Description: 药品报废明细service
* @author crazy_long
* @date 2019年7月30日  下午12:09:53
*    
*/

@Service
@Transactional(rollbackFor=Exception.class)
public class DrugScrapDetailsService {

}
