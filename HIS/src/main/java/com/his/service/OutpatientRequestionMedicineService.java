package com.his.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**  
* @ClassName: OutpatientRequestionMedicineService  
* @Description: 药品申领service
* @author crazy_long
* @date 2019年7月30日  下午12:15:00
*    
*/

@Service
@Transactional(rollbackFor=Exception.class)
public class OutpatientRequestionMedicineService {

}
