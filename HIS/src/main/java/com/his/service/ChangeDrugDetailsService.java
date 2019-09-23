package com.his.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IAllocationOutboundDao;
import com.his.dao.IChangeDrugDetailsDao;
import com.his.dao.IMedicineDao;
import com.his.pojo.ChangeDrugDetail;
import com.his.pojo.ChangeDrugDetailPK;
import com.his.utils.ServiceException;

/**  
* @ClassName: ChangeDrugDetailsService  
* @Description: 调拨出库明细service (当作回库用)
* @author crazy_long
* @date 2019年7月30日  下午12:02:21
*    
*/

@Service
@Transactional(rollbackFor=Exception.class)
public class ChangeDrugDetailsService {

	@Autowired
	private IChangeDrugDetailsDao changeDrugDetailsDao;
	@Autowired
	private IMedicineDao medicineDao;
	@Autowired
	private IAllocationOutboundDao allocationOutboundDao;
	
	/**
	* @Title:updateOneDetailNumber
	* @Description:修改回库数量
	* @param:@param alloId
	* @param:@param medicineId
	* @param:@param updateNumber
	* @param:@throws ServiceException
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年9月22日 下午10:12:04
	 */
	public void updateOneDetailNumber(String alloId,String medicineId,BigDecimal updateNumber) throws ServiceException{
		try {
			ChangeDrugDetailPK changeDrugDetailPK = new ChangeDrugDetailPK();
			changeDrugDetailPK.setAlloId(alloId);
			changeDrugDetailPK.setMedicineId(medicineId);
			ChangeDrugDetail c = changeDrugDetailsDao.findById(changeDrugDetailPK).get();
			//修改数量
			c.setChangeDrugNum(updateNumber);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("修改一个回库明细数量失败");
		}
	}
	
	/**
	* @Title:getDetailCount
	* @Description:判断一个回库单是否拥有明细
	* @param:@param alloId
	* @param:@return
	* @return:int
	* @throws
	* @author:crazy_long
	* @Date:2019年9月22日 下午9:21:18
	 */
	public boolean backDetailIsHave(String alloId) {
		boolean flag = true;
		int count = changeDrugDetailsDao.getDetailCount(alloId);
		if(count==0) {
			flag = false;
		}
		return flag;
	}
	
	/**
	* @Title:delOneDetail
	* @Description:删除一个回库明细
	* @param:@param alloId
	* @param:@param medicineId
	* @param:@throws ServiceException
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年9月12日 下午11:31:24
	 */
	public void delOneDetail(String alloId,String medicineId) throws ServiceException{
		try {
			ChangeDrugDetailPK changeDrugDetailPK = new ChangeDrugDetailPK();
			changeDrugDetailPK.setAlloId(alloId);
			changeDrugDetailPK.setMedicineId(medicineId);
			changeDrugDetailsDao.deleteById(changeDrugDetailPK);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("删除一个回库明细失败");
		}
	}
	
	/**
	* @Title:getDetailByAlloId
	* @Description:根据返库id查找明细
	* @param:@param alloId
	* @param:@return
	* @return:List<ChangeDrugDetail>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月12日 下午11:13:49
	 */
	public List<ChangeDrugDetail> getDetailByAlloId(String alloId){
		return changeDrugDetailsDao.getDetailByAlloId(alloId);
	}
	
	/**
	* @Title:addDetailByBatch
	* @Description:批量添加回库明细
	* @param:@param changeDrugDetail
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年9月12日 下午10:32:18
	 */
	public void addDetailByBatch(List<ChangeDrugDetail> changeDrugDetail) throws ServiceException{
		try {
			for (ChangeDrugDetail cdl : changeDrugDetail) {
				ChangeDrugDetailPK changeDrugDetailPK = new ChangeDrugDetailPK();
				changeDrugDetailPK.setAlloId(cdl.getAllocationOutbound().getAlloId());
				changeDrugDetailPK.setMedicineId(cdl.getMedicine().getMedicineId());
				//先判断是否已经存在
				if(changeDrugDetailsDao.existsById(changeDrugDetailPK)) {
					//已经存在改数量
					ChangeDrugDetail c = changeDrugDetailsDao.findById(changeDrugDetailPK).get();
					c.setChangeDrugNum(cdl.getChangeDrugNum());
				}else {
					//不存在创建
					ChangeDrugDetail d = new ChangeDrugDetail();
					d.setId(changeDrugDetailPK);
					d.setChangeDrugNum(cdl.getChangeDrugNum());
					changeDrugDetailsDao.save(d);
				}
			} 
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("添加回库明细失败");
		}
		
	};
}
