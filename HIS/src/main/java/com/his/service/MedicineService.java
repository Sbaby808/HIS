package com.his.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**  
* @ClassName: MedicineService  
* @Description: 药房药品信息service
* @author crazy_long
* @date 2019年7月30日  下午12:14:07
*    
*/

@Service
@Transactional(rollbackFor=Exception.class)
public class MedicineService {

}
