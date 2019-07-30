package com.his.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**  
* @ClassName: WorkTimeService  
* @Description: 排班时间安排service
* @author crazy_long
* @date 2019年7月30日  下午2:25:41
*    
*/

@Service
@Transactional(rollbackFor=Exception.class)
public class WorkTimeService {

}
