package com.his.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**  
* @ClassName: RoleService  
* @Description: 职位service
* @author crazy_long
* @date 2019年7月30日  下午2:21:10
*    
*/
@Service
@Transactional(rollbackFor=Exception.class)
public class RoleService {

}
