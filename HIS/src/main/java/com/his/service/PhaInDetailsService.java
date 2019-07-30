package com.his.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**  
* @ClassName: PhaInDetailsService  
* @Description: 药房入库明细service
* @author crazy_long
* @date 2019年7月30日  下午2:27:38
*    
*/
@Service
@Transactional(rollbackFor=Exception.class)
public class PhaInDetailsService {

}
