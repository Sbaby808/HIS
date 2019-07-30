package com.his.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**  
* @ClassName: BackMedicineService  
* @Description: 退药service
* @author crazy_long
* @date 2019年7月30日  下午12:00:03
*    
*/
@Service
@Transactional(rollbackFor=Exception.class)
public class BackMedicineService {

}
