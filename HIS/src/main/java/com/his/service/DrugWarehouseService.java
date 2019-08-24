package com.his.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ServiceConfigurationError;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IDrugInformationDao;
import com.his.dao.IDrugWarehouseDao;
import com.his.dao.IPutStockDao;
import com.his.dao.IPutStockDetailsDao;
import com.his.pojo.DrugInformation;
import com.his.pojo.DrugWarehouse;
import com.his.pojo.EmpInformation;
import com.his.pojo.PutStock;
import com.his.pojo.PutStockDetail;
import com.his.pojo.PutStockDetailPK;
import com.his.pojo.PutStockInformation;
import com.his.utils.ServiceException;
import com.his.utils.SimpleTools;

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
	@Autowired
	private IPutStockDao PutStockDao;
	@Autowired
	private IPutStockDetailsDao putStockDetailsDao;
	@Autowired
	private IDrugInformationDao drugInformationDao;
	
	/**
	* @Title:queryDrugForback
	* @Description:根据药品名和供应商id查找要退还的药品
	* @param:@param ypName
	* @param:@param supplierid
	* @param:@return
	* @return:Map
	* @throws
	* @author:crazy_long
	* @Date:2019年8月24日 上午9:49:43
	 */
	public Map queryDrugForback(String ypNameOrVocode,String supplierId,int curPage,int pageSize) {
		Map map = new HashMap();
		List<DrugWarehouse> list = null;
		int total = 0;
		try {
			list = drugWarehouseDao.queryBackDrug(ypNameOrVocode.equals("")?SimpleTools.addCharForSearch(ypNameOrVocode):ypNameOrVocode,
					supplierId.equals("")?SimpleTools.addCharForSearch(supplierId):supplierId,
					PageRequest.of(curPage-1, pageSize));
			total = drugWarehouseDao.queryBackDrugCount(ypNameOrVocode.equals("")?SimpleTools.addCharForSearch(ypNameOrVocode):ypNameOrVocode, 
					supplierId.equals("")?SimpleTools.addCharForSearch(supplierId):supplierId);
			map.put("list", list);
			map.put("total", total);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("查找退还药品失败");
		}
	}
	
	/**
	* @Title:queryDrugforScrapByPage
	* @Description:根据药品名(音码)查找或批次分页查找药品
	* @param:@return
	* @return:Map
	* @throws
	* @author:crazy_long
	* @Date:2019年8月22日 下午2:11:40
	 */
	public Map queryDrugforScrapByPage(String ypNameOrvocode,long prodeceDate,int curPage,int pageSize){
		Map map = new HashMap();
		List<DrugWarehouse> list = null;
		int total = 0;
		if(prodeceDate==0){
			System.out.println("------------------时间为空");
			list = drugWarehouseDao.getDrugByName(ypNameOrvocode.equals("")?SimpleTools.addCharForSearch(ypNameOrvocode):ypNameOrvocode, 
					PageRequest.of(curPage-1, pageSize));
			total = drugWarehouseDao.getDrugByNameCount(ypNameOrvocode.equals("")?SimpleTools.addCharForSearch(ypNameOrvocode):ypNameOrvocode);
		}else {
			System.out.println("------------------时间不为空");
			Date prodeceDate1 = new Date(prodeceDate);
			list = drugWarehouseDao.getDrugByNameOrproduceDate(
					ypNameOrvocode.equals("")?SimpleTools.addCharForSearch(ypNameOrvocode):ypNameOrvocode, prodeceDate1, 
							PageRequest.of(curPage-1, pageSize));
			total = drugWarehouseDao.getDrugByNameOrproduceDateCount(
					ypNameOrvocode.equals("")?SimpleTools.addCharForSearch(ypNameOrvocode):ypNameOrvocode, prodeceDate1);
		}
		map.put("list", list);
		map.put("total", total);
		return map;
	}
	
	/**
	* @Title:getAllOverdueDrug
	* @Description:查找所有过期的药品
	* @param:@return
	* @return:List<DrugWarehouse>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月20日 下午5:38:14
	 */
	public List<DrugWarehouse> getAllOverdueDrug() {
		Date todate = new Date();
		List<DrugWarehouse> list;
		try {
			list = drugWarehouseDao.getAllOverdueDrug(todate);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("查找所有过期药品失败");
		}
		return list;
	}
	
	/**
	* @Title:getAllOverdueDrug
	* @Description:分页查找所有过期的药品
	* @param:@param todate
	* @param:@param curPage
	* @param:@param pageSize
	* @param:@return
	* @return:Map
	* @throws
	* @author:crazy_long
	* @Date:2019年8月20日 下午4:23:14
	 */
	public Map getAllOverdueDrugByPage(int curPage,int pageSize) {
		Map map = new HashMap();
		List<DrugWarehouse> list;
		int total;
		Date todate = new Date();
		try {
			list = drugWarehouseDao.getAllOverdueDrugByPage(todate, PageRequest.of(curPage - 1, pageSize));
			total = drugWarehouseDao.getAllOverdueDrugCount(todate);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("查找所有过期药品失败");
		}
		map.put("list", list);
		map.put("total", total);
		return map;
	}
	
	/**
	* @Title:DrugPutStockBybatch
	* @Description:药品入库
	* @param:@param psinfo
	* @param:@throws ServiceException
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年8月17日 下午3:22:14
	 */
	public void DrugPutStockBybatch(List<PutStockInformation> psinfo) throws ServiceException{
		try {
			//先插入药库信息表
			PutStock putstock = new PutStock();
			EmpInformation emp = new EmpInformation();
			String rkid = UUID.randomUUID().toString().replace("-", "");
			putstock.setRkId(rkid);
			putstock.setRkTime(psinfo.get(0).getPutStockDate());
			emp.setYgxh(psinfo.get(0).getYgxh());
			putstock.setEmpInformation(emp);
			//插入入库信息表
			PutStockDao.save(putstock);
			
			//循环插入库存表
			for (PutStockInformation p : psinfo) {
				//先判断每一个药在库存中是否已经存在
				System.out.println("本次药品id："+p.getYpId()+";"+"本次药品生产日期："+p.getYpProduceDate());
				 List<DrugWarehouse> list = drugWarehouseDao.batchIsExist(p.getYpId(), p.getYpProduceDate());
				if(list.size()==0) {
					System.out.println("批次不存在，创建批次");
					//该批次未存在  创建该批次
					DrugWarehouse drugWarehouse = new DrugWarehouse();
					//创建主键
					String PckcId = UUID.randomUUID().toString().replace("-", "");
					drugWarehouse.setPckcId(PckcId);	
					drugWarehouse.setPutNumber(p.getPutNumber());
					drugWarehouse.setNowNumber(p.getPutNumber());
					//药品生产日期时间
					drugWarehouse.setProduceDate(p.getYpProduceDate());
					DrugInformation drugInformation = drugInformationDao.findById(p.getYpId()).get();
					drugWarehouse.setDrugInformation(drugInformation);
					//获取药品的保质期
					String ypShelflife = drugInformation.getYpShelflife();
					Integer month = Integer.parseInt(ypShelflife);
					//计算药品的到期时间
					Calendar calendar = new GregorianCalendar();
					Date ypProduceDate = p.getYpProduceDate();
					calendar.setTime(ypProduceDate);
					calendar.add(Calendar.MONTH, month);
					ypProduceDate = calendar.getTime();
					drugWarehouse.setExpireDate(ypProduceDate);
					drugWarehouseDao.save(drugWarehouse);
					
					//把数据插入明细表
					this.addOnePutStockDetail(PckcId, rkid, p.getPutNumber());	
				}else {
					System.out.println("批次已经存在，修改库存");
					//该批次已经存在  改掉库存表在库数量和入库数量
					DrugWarehouse drugWarehouse = list.get(0);
					drugWarehouse.setPutNumber(drugWarehouse.getPutNumber().add(p.getPutNumber()));
					drugWarehouse.setNowNumber(drugWarehouse.getNowNumber().add(p.getPutNumber()));
					//把数据插入明细表
					this.addOnePutStockDetail(drugWarehouse.getPckcId(), rkid, p.getPutNumber());
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("入库操作失败");
		}
	}
	
	/**
	* @Title:addOnePutStockDetail
	* @Description:添加一个入库明细
	* @param:@param pckcId
	* @param:@param rkId
	* @param:@param putNumber
	* @param:@throws ServiceException
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年8月17日 下午4:52:38
	 */
	public void addOnePutStockDetail(String pckcId,String rkId,BigDecimal putNumber) throws ServiceException{
		try {
			PutStockDetail putStockDetail = new PutStockDetail();
			PutStockDetailPK putStockDetailPK = new PutStockDetailPK();
			putStockDetailPK.setRkId(rkId);
			putStockDetailPK.setPckcId(pckcId);
			putStockDetail.setId(putStockDetailPK);
			putStockDetail.setPutNum(putNumber);
			putStockDetailsDao.save(putStockDetail);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("添加明细失败");
		}
	}
	
	/**
	* @Title:addTestDrugWarehouse
	* @Description:添加测试库存
	* @param:@param drugWarehouse
	* @param:@throws ServiceConfigurationError
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年8月17日 下午3:36:39
	 */
	public void addTestDrugWarehouse(DrugWarehouse drugWarehouse) throws ServiceConfigurationError{
		try {
			drugWarehouseDao.save(drugWarehouse);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("add DrugWarehouse erroe");
		}
	}
	
	
}
