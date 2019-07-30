package com.his.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**  
* @ClassName: EmpInformationService  
* @Description: 员工信息service
* @author crazy_long
* @date 2019年7月30日  下午12:13:13
*    
*/

@Service
@Transactional(rollbackFor=Exception.class)
public class EmpInformationService {

}
