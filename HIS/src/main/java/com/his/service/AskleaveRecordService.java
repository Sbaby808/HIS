package com.his.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**  
* @ClassName: AskleaveRecordService  
* @Description: 请假记录service
* @author crazy_long
* @date 2019年7月30日  上午11:54:38
*    
*/

@Service
@Transactional(rollbackFor=Exception.class)
public class AskleaveRecordService {

}
