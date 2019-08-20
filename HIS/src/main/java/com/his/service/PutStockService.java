package com.his.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IPutStockDao;
import com.his.pojo.PutStock;


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
	
	@Autowired
	private IPutStockDao putstockdao;
		
	/**
	* @Title:queryAllputTime
	* @Description:获取所有入库信息
	* @param:@return
	* @return:List<PutStock>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月19日 上午10:48:08
	 */
	public List<PutStock> queryAllputTime(){
		return putstockdao.queryAllPutTime();
	}

}
