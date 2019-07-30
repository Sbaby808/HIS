package com.his.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**  
* @ClassName: DrugSubclassService  
* @Description:药品小类service
* @author crazy_long
* @date 2019年7月30日  下午12:10:52
*    
*/

@Service
@Transactional(rollbackFor=Exception.class)
public class DrugSubclassService {

}
