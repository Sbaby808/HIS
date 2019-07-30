package com.his.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**  
* @ClassName: PutStockService  
* @Description: 药品入库信息service 
* @author crazy_long
* @date 2019年7月30日  下午2:18:10
*    
*/

@Service
@Transactional(rollbackFor=Exception.class)
public class PutStockService {

}
