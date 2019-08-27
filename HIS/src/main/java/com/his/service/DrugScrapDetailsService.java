package com.his.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IDrugScrapDao;
import com.his.dao.IDrugScrapDetailsDao;
import com.his.dao.IDrugWarehouseDao;
import com.his.pojo.DrugScrap;
import com.his.pojo.DrugScrapDetail;
import com.his.pojo.DrugScrapDetailPK;
import com.his.pojo.DrugWarehouse;
import com.his.utils.ServiceException;
import com.his.utils.SimpleTools;

/**  
* @ClassName: DrugScrapDetailsService  
* @Description: 药品报废明细service
* @author crazy_long
* @date 2019年7月30日  下午12:09:53
*    
*/

@Service
@Transactional(rollbackFor=Exception.class)
public class DrugScrapDetailsService {
	
	@Autowired
	private IDrugScrapDao drugScrapDao;
	@Autowired
	private IDrugScrapDetailsDao drugScrapDetailsDao;
	@Autowired
	private IDrugWarehouseDao drugWarehouseDao;
		
	/**
	* @Title:queryScrapDrugBypage
	* @Description:根据条件查找对应报废的药品
	* @param:@param ygxh
	* @param:@param type
	* @param:@param firstDate
	* @param:@param lastDate
	* @param:@param minMoney
	* @param:@param maxMoney
	* @param:@return
	* @return:Map
	* @throws
	* @author:crazy_long
	* @Date:2019年8月23日 上午10:49:30
	 */
	public Map queryScrapDrugBypage(String ygxh,String type,long firstDate,long lastDate,BigDecimal minMoney,BigDecimal maxMoney,int curPage,int pageSize) {
		System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwwwww");
		Map map = new HashMap();
		List<DrugScrapDetail> list = null;
		int total = 0;
		Date firstDate1 = new Date(firstDate);
		Date lastDate1 = new Date(lastDate);
		 try {
			list = drugScrapDetailsDao.queryScrapDrugByPage(ygxh.equals("") ? SimpleTools.addCharForSearch(ygxh) : ygxh,
					type.equals("") ? SimpleTools.addCharForSearch(type) : type, firstDate1, lastDate1, minMoney,maxMoney,
							PageRequest.of(curPage - 1, pageSize));
			total = drugScrapDetailsDao.queryScrapDrugByPageCount(
					ygxh.equals("") ? SimpleTools.addCharForSearch(ygxh) : ygxh,
					type.equals("") ? SimpleTools.addCharForSearch(type) : type, firstDate1, lastDate1, minMoney,maxMoney);
			map.put("list", list);
			map.put("total", total);
			return map;
		 } catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("根据条件查询药品失败");
		}
		
	}
	
	/**
	* @Title:addDrugScrapDetailByBatch
	* @Description:插入报废信息极其明细
	* @param:@param DrugScrapDetail
	* @param:@throws ServiceException
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年8月21日 下午4:32:11
	 */
	public void addDrugScrapDetailByBatch(List<DrugScrapDetail> drugScrapDetail) throws ServiceException{
		//第一个DrugScrapDetail保存的是基本信息
		//先获取过期的基本信息
		DrugScrap drugScrap = drugScrapDetail.get(0).getDrugScrap();
		//维护过期信息表
		String bfId = UUID.randomUUID().toString().replace("-", "");
		drugScrap.setBfId(bfId);
		try {
			//插入过期信息表
			drugScrapDao.save(drugScrap);
			//循化插入明细表  从第二个开始
			for(int i = 1;i<drugScrapDetail.size();i++) {
				System.out.println("-----------------------------------");
				System.out.println("bfId:"+bfId);
				System.out.println("PckcId:"+drugScrapDetail.get(i).getDrugWarehouse().getPckcId());
				System.out.println("bfnumber:"+drugScrapDetail.get(i).getBfNum());
				DrugScrapDetail dsd = drugScrapDetail.get(i);
				//创建联合主键
				DrugScrapDetailPK drugScrapDetailpk = new DrugScrapDetailPK();
				drugScrapDetailpk.setPckcId(drugScrapDetail.get(i).getDrugWarehouse().getPckcId());
				drugScrapDetailpk.setBfId(bfId);
				dsd.setId(drugScrapDetailpk);
				//插入过期明细表
				drugScrapDetailsDao.save(dsd);
				//修改库存表
				DrugWarehouse drugWarehouse = drugWarehouseDao.findById(drugScrapDetail.get(i).getDrugWarehouse().getPckcId()).get(); 
				drugWarehouse.setNowNumber(new BigDecimal(0)); //清空库存
				drugWarehouse.setState("是"); //修改状态
				//drugWarehouseDao.save(drugWarehouse);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("插入过期信息失败");
		}
	}
	
	/**
	* @Title:addDrugScrapDetailByBatch
	* @Description:插入过期信息极其明细
	* @param:@param DrugScrapDetail
	* @param:@throws ServiceException
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年8月21日 下午4:32:11
	 */
	public void addDrugOverdueDetailByBatch(List<DrugScrapDetail> drugScrapDetail) throws ServiceException{
		//第一个DrugScrapDetail保存的是基本信息
		//先获取过期的基本信息
		DrugScrap drugScrap = drugScrapDetail.get(0).getDrugScrap();
		//维护过期信息表
		String bfId = UUID.randomUUID().toString().replace("-", "");
		drugScrap.setBfId(bfId);
		drugScrap.setBfType("过期");
		drugScrap.setBfReason("保质期已过");
		try {
			//插入过期信息表
			drugScrapDao.save(drugScrap);
			//循化插入明细表  从第二个开始
			for(int i = 1;i<drugScrapDetail.size();i++) {
				System.out.println("-----------------------------------");
				System.out.println("bfId:"+bfId);
				System.out.println("PckcId:"+drugScrapDetail.get(i).getDrugWarehouse().getPckcId());
				System.out.println("bfnumber:"+drugScrapDetail.get(i).getBfNum());
				DrugScrapDetail dsd = drugScrapDetail.get(i);
				//创建联合主键
				DrugScrapDetailPK drugScrapDetailpk = new DrugScrapDetailPK();
				drugScrapDetailpk.setPckcId(drugScrapDetail.get(i).getDrugWarehouse().getPckcId());
				drugScrapDetailpk.setBfId(bfId);
				dsd.setId(drugScrapDetailpk);
				//插入过期明细表
				drugScrapDetailsDao.save(dsd);
				//修改库存表
				DrugWarehouse drugWarehouse = drugWarehouseDao.findById(drugScrapDetail.get(i).getDrugWarehouse().getPckcId()).get(); 
				drugWarehouse.setNowNumber(new BigDecimal(0)); //清空库存
				drugWarehouse.setState("是"); //修改状态
				//drugWarehouseDao.save(drugWarehouse);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("插入过期信息失败");
		}
		
		
	}

}
