package com.his.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.DeptDAO;
import com.his.dao.IOutpatientRequestionMedicineDao;
import com.his.pojo.Dept;
import com.his.pojo.OutpatientRequestionMedicine;
import com.his.utils.ServiceException;

/**  
* @ClassName: OutpatientRequestionMedicineService  
* @Description: 药品申领service
* @author crazy_long
* @date 2019年7月30日  下午12:15:00
*    
*/

@Service
@Transactional(rollbackFor=Exception.class)
public class OutpatientRequestionMedicineService {
	
	@Autowired
	private IOutpatientRequestionMedicineDao outpatientRequestionMedicineDao;
	@Autowired
	private DeptDAO deptdao;
	
	/**
	* @Title:updateRequestState
	* @Description:改变申领单的状态
	* @param:@param reqId
	* @param:@throws ServiceException
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年9月3日 下午3:30:36
	 */
	public void updateRequestState(String reqId) throws ServiceException{
		try {
			OutpatientRequestionMedicine orm = outpatientRequestionMedicineDao.findById(reqId).get();
			orm.setReqStatus("已出库");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("修改申领单状态失败");
		}
	}
	
	/**
	* @Title:getRequestByDeptName
	* @Description:根据部门名称查询对应的申领单
	* @param:@param deptName
	* @param:@return
	* @return:List<OutpatientRequestionMedicine>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月2日 上午9:13:22
	 */
	public List<OutpatientRequestionMedicine> getRequestByDeptName(String deptName){
		return outpatientRequestionMedicineDao.queryRequestBydeptName(deptName);
	}
	
	/**
	* @Title:getAllDeptRequestCount
	* @Description:查询各个部门的申领条数
	* @param:@return
	* @return:Map
	* @throws
	* @author:crazy_long
	* @Date:2019年9月2日 上午12:02:24
	 */
	public Map getAllDeptRequestCount() {
		Map map = new HashMap();
		List<Dept> dlist = (List<Dept>) deptdao.findAll();
		//分别查询每一个部门的申领条数
		for (Dept dept : dlist) {
			int requestCount = outpatientRequestionMedicineDao.queryRequestCountBydeptId(dept.getDeptId());
			map.put(dept.getDeptName(), requestCount);
		}
		return map;
	}
	
	/**
	* @Title:queryRequestCountByDetpId
	* @Description:根据部门id查询对应申领药品的条数
	* @param:@param deptId
	* @param:@return
	* @return:int
	* @throws
	* @author:crazy_long
	* @Date:2019年8月29日 下午11:52:45
	 */
	public int queryRequestCountByDetpId(String deptId) {
		return outpatientRequestionMedicineDao.queryRequestCountBydeptId(deptId);
	}
	
	/**
	* @Title:queryNoRequest
	* @Description:分页查询未申领药品
	* @param:@param curpage
	* @param:@param pageSize
	* @param:@return
	* @return:Map
	* @throws
	* @author:crazy_long
	* @Date:2019年8月29日 上午11:20:09
	 */
	public Map queryNoRequest(int curpage,int pageSize) {
		Map map = new HashMap();
		List<OutpatientRequestionMedicine> list = null;
		int total = 0;
		try {
			list = outpatientRequestionMedicineDao.queryRequestForNo(PageRequest.of(curpage - 1, pageSize));
			total = outpatientRequestionMedicineDao.queryRequestInNowCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("list", list);
		map.put("total", total);
		
		return map;
	}
	
	/**
	* @Title:queryNoRequest
	* @Description:分页查询已申领药品
	* @param:@param curpage
	* @param:@param pageSize
	* @param:@return
	* @return:Map
	* @throws
	* @author:crazy_long
	* @Date:2019年8月29日 上午11:20:09
	 */
	public Map queryYesRequest(int curpage,int pageSize) {
		Map map = new HashMap();
		List<OutpatientRequestionMedicine> list = null;
		int total = 0;
		try {
			list = outpatientRequestionMedicineDao.queryRequestForYes(PageRequest.of(curpage - 1, pageSize));
			total = outpatientRequestionMedicineDao.queryRequestForYesCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("list", list);
		map.put("total", total);
		
		return map;
	}
	
	/**
	* @Title:queryNoRequestCount
	* @Description:查找未申领的条数
	* @param:@return
	* @return:int
	* @throws
	* @author:crazy_long
	* @Date:2019年8月28日 下午11:56:16
	 */
	public int queryNoRequestCount() {
		return outpatientRequestionMedicineDao.queryRequestInNowCount();
	}

}
