package com.his.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IEmpInformationDao;
import com.his.dao.IOutpatientRequestionMedicineDao;
import com.his.dao.IPhaInDao;
import com.his.pojo.EmpInformation;
import com.his.pojo.OutpatientRequestionMedicine;
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
