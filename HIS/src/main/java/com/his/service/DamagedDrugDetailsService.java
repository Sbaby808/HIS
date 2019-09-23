package com.his.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IDamagedDrugDetailsDao;
import com.his.dao.IMedicineDao;
import com.his.pojo.DamagedDrugDetail;
import com.his.pojo.DamagedDrugDetailPK;
import com.his.pojo.DamagedMedicine;
import com.his.pojo.Medicine;
import com.his.utils.ServiceException;

/**  
* @ClassName: DamagedDrugDetailsService  
* @Description: 药品报损明细service
* @author crazy_long
* @date 2019年7月30日  下午12:04:40
*    
*/

@Service
@Transactional(rollbackFor=Exception.class)
public class DamagedDrugDetailsService {
	
	@Autowired
	private IDamagedDrugDetailsDao damagedDrugDetailsDao;
	@Autowired
	private IMedicineDao medicineDao;
	
	/**
	* @Title:queryDamagedDetailByPage
	* @Description:分页查找报损明细
	* @param:@param damagedId
	* @param:@param curPage
	* @param:@param pageSize
	* @param:@return
	* @return:Map
	* @throws
	* @author:crazy_long
	* @Date:2019年9月22日 下午1:57:01
	 */
	public Map queryDamagedDetailByPage(String damagedId,int curPage,int pageSize){
		Map map = new HashMap();
		List<DamagedDrugDetail> list = damagedDrugDetailsDao.queryDamagedDetailByPage(damagedId,PageRequest.of(curPage - 1, pageSize));
		int total = damagedDrugDetailsDao.isHaveDetail(damagedId);
		map.put("list", list);
		map.put("total", total);
		return map;
	}
	
	/**
	* @Title:isHaveDetail
	* @Description:判断报损单是否拥有明细
	* @param:@param damagedId
	* @param:@return
	* @return:boolean
	* @throws
	* @author:crazy_long
	* @Date:2019年9月22日 下午12:25:14
	 */
	public boolean isHaveDetail(String damagedId) {
		boolean flag = true;
		int count = damagedDrugDetailsDao.isHaveDetail(damagedId);
		if(count == 0) {
			flag = false;
		}
		return flag;
	}
	
	/**
	* @Title:addDamageDetailByBatch
	* @Description:批量加入报损明细
	* @param:@param damagedDrugDetail
	* @param:@throws ServiceException
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年9月6日 下午9:52:24
	 */
	public void addDamageDetailByBatch(List<DamagedDrugDetail> damagedDrugDetail) throws ServiceException {
		//循环加入
		for (DamagedDrugDetail dm : damagedDrugDetail) {
			System.out.println("---------------------");
			System.out.println("DamagedId"+dm.getDamagedMedicine().getDamagedId());
			System.out.println("MedicineId"+dm.getMedicine().getMedicineId());
			System.out.println(dm.getDamNum());
			try {
				//创建主键
				DamagedDrugDetailPK damagedDrugDetailPk = new DamagedDrugDetailPK();
				damagedDrugDetailPk.setDamagedId(dm.getDamagedMedicine().getDamagedId());
				damagedDrugDetailPk.setMedicineId(dm.getMedicine().getMedicineId());
				//先判断是否已经存在
				boolean flag = damagedDrugDetailsDao.existsById(damagedDrugDetailPk);
				if (flag) {
					System.out.println("已经存在");
					DamagedDrugDetail d1 = damagedDrugDetailsDao.findById(damagedDrugDetailPk).get();
					//如果已经存在修改数量
					BigDecimal damNum1 = dm.getDamNum();  //上传的数量
					BigDecimal damNum2 = d1.getDamNum(); //原来的数量
					//做大小比较
					if(damNum1.compareTo(damNum2) > 0) {
						d1.setDamNum(dm.getDamNum());
						//修改库存
						BigDecimal chazhi1 = damNum1.subtract(damNum2);
						Medicine medicine = medicineDao.findById(dm.getMedicine().getMedicineId()).get();
						medicine.setMedicineName(medicine.getMedicineName().subtract(chazhi1));
					}else if(damNum1.compareTo(damNum2) < 0) {
						d1.setDamNum(dm.getDamNum());
						//修改库存
						BigDecimal chazhi2 = damNum2.subtract(damNum1);
						Medicine medicine = medicineDao.findById(dm.getMedicine().getMedicineId()).get();
						medicine.setMedicineName(medicine.getMedicineName().add(chazhi2));
					}else {
						//相等什么也不干 
					}
				} else {
					System.out.println("未存在");
					//不已经直接创建
					DamagedDrugDetail d = new DamagedDrugDetail();
					d.setId(damagedDrugDetailPk);
					d.setDamNum(dm.getDamNum());
					damagedDrugDetailsDao.save(d);
					//修改库存
					Medicine medicine = medicineDao.findById(dm.getMedicine().getMedicineId()).get();
					medicine.setMedicineName(medicine.getMedicineName().subtract(dm.getDamNum()));
				} 
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServiceException("批量加入报损明细失败");
			}
		}
	}
	
	/**
	* @Title:updateDamageDetialNumber
	* @Description:修改一个明细的数量
	* @param:@param damagedId
	* @param:@param medicineId
	* @param:@param updateNumber
	* @param:@throws ServiceException
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年9月22日 下午12:47:34
	 */
	public void updateDamageDetialNumber(String damagedId,String medicineId,BigDecimal updateNumber) throws ServiceException{
		//创建主键
		DamagedDrugDetailPK damagedDrugDetailPk = new DamagedDrugDetailPK();
		damagedDrugDetailPk.setDamagedId(damagedId);
		damagedDrugDetailPk.setMedicineId(medicineId);
		DamagedDrugDetail d = damagedDrugDetailsDao.findById(damagedDrugDetailPk).get();
		Medicine medicine = medicineDao.findById(medicineId).get();
		//改回库存
		BigDecimal number = d.getDamNum();	//原来的数量
		//先判断更改数量的关系
		if(number.compareTo(updateNumber)==1) {
			//原来的数量大
			BigDecimal cha = number.subtract(updateNumber);
			//修改库存 库存增加
			medicine.setMedicineName(medicine.getMedicineName().add(cha));
			//修改明细数量
			d.setDamNum(updateNumber);
		}else {
			//原来的数量小
			BigDecimal cha = updateNumber.subtract(number);
			//修改库存 库存减少
			medicine.setMedicineName(medicine.getMedicineName().subtract(cha));
			//修改明细数量
			d.setDamNum(updateNumber);
		}
	}
	
	/**
	* @Title:delOneDetail
	* @Description:删除一个明细
	* @param:@param damagedId
	* @param:@param medicineId
	* @param:@throws ServiceException
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年9月7日 上午12:42:22
	 */
	public void delOneDetail(String damagedId,String medicineId) throws ServiceException{
		//创建主键
		DamagedDrugDetailPK damagedDrugDetailPk = new DamagedDrugDetailPK();
		damagedDrugDetailPk.setDamagedId(damagedId);
		damagedDrugDetailPk.setMedicineId(medicineId);
		DamagedDrugDetail d = damagedDrugDetailsDao.findById(damagedDrugDetailPk).get();
		//改回库存
		BigDecimal number = d.getDamNum();
		Medicine medicine = medicineDao.findById(medicineId).get();
		medicine.setMedicineName(medicine.getMedicineName().add(number));
		//删除
		damagedDrugDetailsDao.deleteById(damagedDrugDetailPk);
	}
	
	/**
	* @Title:queryDetailByDamagedId
	* @Description:根据报损单id查找对应的明细
	* @param:@param damagedId
	* @param:@return
	* @return:List<DamagedDrugDetail>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月6日 下午11:37:33
	 */
	public List<DamagedDrugDetail> queryDetailByDamagedId(String damagedId){
		return damagedDrugDetailsDao.queryByDamagedId(damagedId);
	}

}
