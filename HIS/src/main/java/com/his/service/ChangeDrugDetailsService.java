package com.his.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**  
* @ClassName: ChangeDrugDetailsService  
* @Description: 调拨出库明细service
* @author crazy_long
* @date 2019年7月30日  下午12:02:21
*    
*/

@Service
@Transactional(rollbackFor=Exception.class)
public class ChangeDrugDetailsService {

}
