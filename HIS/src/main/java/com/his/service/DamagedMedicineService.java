package com.his.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**  
* @ClassName: DamagedMedicineService  
* @Description: 药品报损service
* @author crazy_long
* @date 2019年7月30日  下午12:05:40
*    
*/

@Service
@Transactional(rollbackFor=Exception.class)
public class DamagedMedicineService {

}
