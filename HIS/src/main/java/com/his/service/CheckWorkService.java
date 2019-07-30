package com.his.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**  
* @ClassName: CheckWorkService  
* @Description: 考勤记录service
* @author crazy_long
* @date 2019年7月30日  下午12:03:36
*    
*/

@Service
@Transactional(rollbackFor=Exception.class)
public class CheckWorkService {

}
