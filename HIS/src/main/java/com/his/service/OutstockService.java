package com.his.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IOutstockDao;
import com.his.pojo.Outstock;
import com.his.utils.ServiceException;

/**  
* @ClassName: OutstockService  
* @Description: 药库出库信息service
* @author crazy_long
* @date 2019年7月30日  下午12:16:02
*    
*/
@Service
@Transactional(rollbackFor=Exception.class)
public class OutstockService {
	
	@Autowired
	private IOutstockDao outstockDao;
	
	/**
	* @Title:addOutScotck
	* @Description:添加出库单
	* @param:@param outStock
	* @param:@throws ServiceException
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年9月2日 上午10:35:12
	 */
	public void addOutScotck(Outstock outStock) throws ServiceException{
		String req_id = UUID.randomUUID().toString().replace("-", "");
		outStock.setCkId(req_id);
		try {
			outstockDao.save(outStock);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("添加出库单失败");
		}
	}
	
	/**
	* @Title:isExitsOutstock
	* @Description:判断出库单是否已经存在
	* @param:@param req_id
	* @param:@return
	* @return:boolean
	* @throws
	* @author:crazy_long
	* @Date:2019年9月1日 下午11:19:24
	 */
	public Map isExitsOutstock(String req_id) {
		Map map = new HashMap();
		int count = outstockDao.getOutstockCount(req_id);
		if(count>0) {
			String ckid = outstockDao.getOutstockId(req_id).getCkId();
			map.put("flag", true);
			map.put("ckId", ckid);
		}else {
			map.put("flag", false);
			map.put("ckId", "没有对应的出库单");
		}
		return map;
	}

}
