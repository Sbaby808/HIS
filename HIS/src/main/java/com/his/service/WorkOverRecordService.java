package com.his.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**  
* @ClassName: WorkOverRecordService  
* @Description: 加班记录service
* @author crazy_long
* @date 2019年7月30日  下午2:24:16
*    
*/
@Service
@Transactional(rollbackFor=Exception.class)
public class WorkOverRecordService {

}
