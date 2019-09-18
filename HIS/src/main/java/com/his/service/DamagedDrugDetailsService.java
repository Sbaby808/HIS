package com.his.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IDamagedDrugDetailsDao;
import com.his.dao.IMedicineDao;
import com.his.pojo.DamagedDrugDetail;
import com.his.pojo.DamagedDrugDetailPK;
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
