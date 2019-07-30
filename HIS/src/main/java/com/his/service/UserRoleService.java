package com.his.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**  
* @ClassName: UserRoleService  
* @Description: 职位_员工service
* @author crazy_long
* @date 2019年7月30日  下午2:23:05
*    
*/
@Service
@Transactional(rollbackFor=Exception.class)
public class UserRoleService {

}
