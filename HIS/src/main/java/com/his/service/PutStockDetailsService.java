package com.his.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**  
* @ClassName: PutStockDetailsService  
* @Description: 药品入库明细
* @author crazy_long
* @date 2019年7月30日  下午2:19:05
*    
*/

@Service
@Transactional(rollbackFor=Exception.class)
public class PutStockDetailsService {

}
