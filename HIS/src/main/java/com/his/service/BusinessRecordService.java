package com.his.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**  
* @ClassName: BusinessRecordService  
* @Description: 出差记录service
* @author crazy_long
* @date 2019年7月30日  下午12:01:07
*    
*/
@Service
@Transactional(rollbackFor=Exception.class)
public class BusinessRecordService {

}
