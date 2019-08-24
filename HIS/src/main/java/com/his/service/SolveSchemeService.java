package com.his.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.ISolveSchemeDao;

/**  
* @ClassName: SolveSchemeService  
* @Description: 门诊医嘱Service
* @author Sbaby
* @date 2019年8月23日  上午10:05:12
*    
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class SolveSchemeService {

	@Autowired
	private ISolveSchemeDao solveSchemeDao;
	
}
