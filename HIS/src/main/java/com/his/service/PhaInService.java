package com.his.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IEmpInformationDao;
import com.his.dao.IOutpatientRequestionMedicineDao;
import com.his.dao.IOutstockDetailsDao;
import com.his.dao.IPhaInDao;
import com.his.pojo.EmpInformation;
import com.his.pojo.OutstockDetail;
import com.his.pojo.PhaIn;
import com.his.utils.ServiceException;

/**  
* @ClassName: PhaInService  
* @Description: 药房入库service
* @author crazy_long
* @date 2019年7月30日  下午12:18:03
*    
*/

@Service
@Transactional(rollbackFor=Exception.class)
public class PhaInService {
	
	@Autowired
	private IPhaInDao phainDao;
	@Autowired
	private IOutpatientRequestionMedicineDao outpatientRequestionMedicineDao ;
	@Autowired
	private IEmpInformationDao empInformationDao;
	@Autowired
	private IOutstockDetailsDao outstockDetailsDao;
	
	/**
	* @Title:getOutstockDetailForReqIdByPage
	* @Description:分页获取入库（出库明细）
	* @param:@param reqId
	* @param:@param curPage
	* @param:@param pageSize
	* @param:@return
	* @return:Map
	* @throws
	* @author:crazy_long
	* @Date:2019年9月22日 下午4:50:12
	 */
	public Map getOutstockDetailForReqIdByPage(String reqId,int curPage,int pageSize) {
		Map map = new HashMap();
		List<OutstockDetail> list = outstockDetailsDao.getOutstockDetailForReqIdByPage(reqId, PageRequest.of(curPage-1, pageSize));
		int total = outstockDetailsDao.getOutstockDetailForReqIdCount(reqId);
		map.put("list", list);
		map.put("total", total);
		return map;
	}
	
	/**
	* @Title:pahInIsExits
	* @Description:分页查找某一部门的入库单
	* @param:@param reqId
	* @param:@return
	* @return:Map
	* @throws
	* @author:crazy_long
	* @Date:2019年9月22日 下午4:11:33
	 */
	public Map getDeptAllPhaInByPage(String deptId,int curPage,int pageSize) {
		Map map = new HashMap();
		List<PhaIn> list = phainDao.getDeptAllPhaInByPage(deptId,PageRequest.of(curPage - 1, pageSize));
		int total = phainDao.getDeptAllPhaInCount(deptId);
		map.put("list", list);
		map.put("total", total);
		return map;
	}
	
	/**
	* @Title:addPhaIn
	* @Description:插入一个药房入库单
	* @param:@param phaIn
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年9月5日 下午8:11:26
	 */
	public void addPhaIn(PhaIn phaIn) throws ServiceException{
		try {
			PhaIn pha = new PhaIn();
			String phaInId = UUID.randomUUID().toString().replace("-","");
			String ygxh = phaIn.getEmpInformation().getYgxh();
			EmpInformation emp = empInformationDao.findById(ygxh).get();
			pha.setPhaInId(phaInId);
			pha.setReqId(phaIn.getReqId());
			pha.setPhaInDate(phaIn.getPhaInDate());
			pha.setEmpInformation(emp);
			phainDao.save(pha);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("创建药房入库单失败");
		}
	}
	
	/**
	* @Title:pahInIsExits
	* @Description:判断入库单是否存在
	* @param:@param phaInId
	* @param:@return
	* @return:boolean
	* @throws
	* @author:crazy_long
	* @Date:2019年9月5日 下午8:04:59
	 */
	public Map pahInIsExits(String reqId) {
		Map map = new HashMap();
		PhaIn list = phainDao.pahInIsExits(reqId);
		boolean flag = false;
		if(list!=null) {
			flag = true;
		}
		map.put("list", list);
		map.put("flag", flag);
		return map;
	}
	

}
