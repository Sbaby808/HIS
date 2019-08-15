package com.his.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IDrugWarehouseDao;

/**  
* @ClassName: DrugWarehouseService  
* @Description: 药库批次库存service
* @author crazy_long
* @date 2019年7月30日  下午12:12:03
*    
*/

@Service
@Transactional(rollbackFor=Exception.class)
public class DrugWarehouseService {

	@Autowired
	private IDrugWarehouseDao drugWarehouseDao;
	
}
