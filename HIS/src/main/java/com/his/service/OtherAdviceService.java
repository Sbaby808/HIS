package com.his.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IOtherAdviceDao;

/**  
* @ClassName: OtherAdviceService  
* @Description: 门诊常规建议Service
* @author Sbaby
* @date 2019年8月22日  下午5:42:50
*    
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class OtherAdviceService {

	@Autowired
	private IOtherAdviceDao otherAdviceDao;
	
	
}
