package com.his.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**  
* @ClassName: DrugMinorDefectsService  
* @Description: 药品种类service
* @author crazy_long
* @date 2019年7月30日  下午12:07:54
*    
*/
@Service
@Transactional(rollbackFor=Exception.class)
public class DrugMinorDefectsService {

}
