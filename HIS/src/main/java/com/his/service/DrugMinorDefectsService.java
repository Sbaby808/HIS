package com.his.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IDrugMinorDefectsDao;
import com.his.pojo.DrugMinorDefect;
import com.his.utils.ServiceException;

/**  
* @ClassName: DrugMinorDefectsService  
* @Description: 药品种类service
* @author crazy_long
* @date 2019年7月30日  下午12:07:54
*    
*/
@Service
@Transactional(rollbackFor=Exception.class)
public class DrugMinorDefectsService {
	
	@Autowired
	private IDrugMinorDefectsDao drugMinorDefectDao;
	
	/**
	* @Title:addDrugMinorDefects
	* @Description:添加药品中类
	* @param:@param drugMinorDefect
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年8月8日 上午8:52:50
	 */
	public void addDrugMinorDefects(DrugMinorDefect drugMinorDefect) throws ServiceException{
		try {
			drugMinorDefect.setMinorDefectsId(UUID.randomUUID().toString().replace("-", ""));
			drugMinorDefectDao.save(drugMinorDefect);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("添加药品失败");
		}
	}
	
	/**
	* @Title:delDrugMinorDefect
	* @Description:根据id删除中类药品
	* @param:@param minorDefectsId
	* @param:@throws ServiceException
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年8月8日 上午9:38:21
	 */
	public void delDrugMinorDefect(String minorDefectsId) throws ServiceException{
		try {
			drugMinorDefectDao.deleteById(minorDefectsId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("删除中类药品失败");
		}
	}
	
	/**
	* @Title:updataDrugMinorDefect
	* @Description:修改中类药品信息
	* @param:@param drugMinorDefect
	* @param:@throws ServiceException
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年8月8日 上午9:41:54
	 */
	public void updataDrugMinorDefect(DrugMinorDefect drugMinorDefect) throws ServiceException{
		try {
			drugMinorDefectDao.save(drugMinorDefect);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("修改中类药品信息失败");
		}
	}
	
	/**
	* @Title:queryAllDrugMinorDefect
	* @Description:查询所有的中类药品信息
	* @param:@return
	* @return:List<DrugMinorDefect>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月8日 上午9:43:09
	 */
	public List<DrugMinorDefect> queryAllDrugMinorDefect(){
		return (List<DrugMinorDefect>) drugMinorDefectDao.findAll();
	}
	
	/**
	* @Title:queryDrugMinorDefectById
	* @Description:根据主键查找中类信息
	* @param:@param minorDefectsId
	* @param:@return
	* @return:DrugMinorDefect
	* @throws
	* @author:crazy_long
	* @Date:2019年8月8日 上午9:50:15
	 */
	public DrugMinorDefect queryDrugMinorDefectById(String minorDefectsId) {
		return drugMinorDefectDao.findById(minorDefectsId).get();
	}

}
