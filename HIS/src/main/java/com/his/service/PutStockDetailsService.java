package com.his.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IPutStockDetailsDao;
import com.his.pojo.PutStock;
import com.his.pojo.PutStockDetail;
import com.his.utils.ServiceException;
import com.his.utils.SimpleTools;

/**  
* @ClassName: PutStockDetailsService  
* @Description: 药品入库明细service
* @author crazy_long
* @date 2019年7月30日  下午2:19:05
*    
*/

@Service
@Transactional(rollbackFor=Exception.class)
public class PutStockDetailsService {
	
	@Autowired
	private IPutStockDetailsDao putStockDetailsDao;
	
	/**
	* @Title:queryPutstockRecordByPage
	* @Description:分页查找符合条件的入库记录
	* @param:@param ygxh
	* @param:@param rkTime
	* @param:@param curPage
	* @param:@param pageSize
	* @param:@return
	* @return:Map
	* @throws
	* @author:crazy_long
	* @Date:2019年8月19日 上午10:16:50
	 */
	public Map queryPutstockRecordByPage(String ygxh,long rkTime,long fisrtTime,long lastTime,int curPage,int pageSize) throws ServiceException{
		Map map = new HashMap();
		Date rkTime1 = new Date(rkTime);
		Date fisrtTime1 = new Date(fisrtTime);
		Date lastTime1 = new Date(lastTime);
		List<PutStockDetail> list;
		int total;
		//如果日期为空
		if(rkTime==0) {
			try {
				list = putStockDetailsDao.queryRecordDateIsNullByPage(ygxh="".equals(ygxh)?"%"+ygxh+"%":ygxh,
						fisrtTime1,lastTime1,PageRequest.of(curPage-1, pageSize));
				total = putStockDetailsDao.queryRecordDateIsNullCount(ygxh,fisrtTime1,lastTime1);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServiceException("查询入库信息失败");
			}
		}else {
			try {
				list = putStockDetailsDao.queryRecordByPage(ygxh="".equals(ygxh)?"%"+ygxh+"%":ygxh,
						rkTime1,fisrtTime1,lastTime1,PageRequest.of(curPage-1, pageSize));
				total = putStockDetailsDao.queryRecordCount(ygxh,rkTime1,fisrtTime1,lastTime1);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServiceException("查询入库信息失败");
			}
		}
		map.put("list", list);
		map.put("total", total);
		return map;
	}
	
}
