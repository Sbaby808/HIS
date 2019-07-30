package com.his.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**  
* @ClassName: DamagedDrugDetailsService  
* @Description: 药品报损明细service
* @author crazy_long
* @date 2019年7月30日  下午12:04:40
*    
*/

@Service
@Transactional(rollbackFor=Exception.class)
public class DamagedDrugDetailsService {

}
