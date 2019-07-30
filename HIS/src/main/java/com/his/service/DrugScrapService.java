package com.his.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**  
* @ClassName: DrugScrapService  
* @Description: 药品报废service
* @author crazy_long
* @date 2019年7月30日  下午12:08:54
*    
*/

@Service
@Transactional(rollbackFor=Exception.class)
public class DrugScrapService {

}
