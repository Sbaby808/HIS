package com.his.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.DeptDAO;
import com.his.dao.IEmpInformationDao;
import com.his.dao.IOutpatientRequestionMedicineDao;
import com.his.pojo.Dept;
import com.his.pojo.EmpInformation;
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
	@Autowired
	private IEmpInformationDao empInformationDao;
	
	/**
	* @Title:getAllRequestionForrStatusByPage
	* @Description:分页查询某一个部门的“某一状态”的申领单
	* @param:@param deptId
	* @param:@param state
	* @param:@param curpage
	* @param:@param pageSize
	* @param:@return
	* @return:Map
	* @throws
	* @author:crazy_long
	* @Date:2019年9月21日 下午11:38:24
	 */
	public Map getAllRequestionForrStatusByPage(String deptId,String state,int curpage,int pageSize) {
		Map map = new HashMap();
		List<OutpatientRequestionMedicine> list = null;
		int total = 0;
		try {
			list = outpatientRequestionMedicineDao.getAllRequestionForrStatusByPage(deptId,state,PageRequest.of(curpage - 1, pageSize));
			total = outpatientRequestionMedicineDao.getAllRequestionForrStatusCount(deptId,state);
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("list", list);
		map.put("total", total);	
		return map;
	}
	
	/**
	* @Title:createOutpatientReq
	* @Description:创建药房申领单
	* @param:@param createDate
	* @param:@param ygxh
	* @param:@throws ServiceException
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年9月14日 上午10:45:42
	 */
	public void createOutpatientReq(long createDate,String ygxh,String deptId) throws ServiceException{
		try {
			Date cdate = new Date(createDate);
			EmpInformation emp = empInformationDao.findById(ygxh).get();
			Dept dept = deptdao.findById(deptId).get();
			String reqId = UUID.randomUUID().toString().replace("-", "");
			OutpatientRequestionMedicine orm = new OutpatientRequestionMedicine();
			orm.setReqId(reqId);
			orm.setEmpInformation(emp);
			orm.setDept(dept);
			orm.setReqTime(cdate);
			orm.setReqStatus("未提交");
			outpatientRequestionMedicineDao.save(orm);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("创建申领单失败");
		}
		
	}
	
	/**
	* @Title:getNewCreateById
	* @Description:查看对应部门刚创建未提交的申领单
	* @param:@param deptId
	* @param:@return
	* @return:List<OutpatientRequestionMedicine>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月14日 上午10:52:27
	 */
	public List<OutpatientRequestionMedicine> getNewCreateById(String deptId){
		return outpatientRequestionMedicineDao.getNewCreateById(deptId);
	}
	
	/**
	* @Title:updateStateToSubmit
	* @Description:提交新建的申领单  改变状态为‘未申领’
	* @param:@param reqId
	* @param:@throws ServiceException
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年9月14日 下午3:57:51
	 */
	public void updateStateToSubmit(String reqId) throws ServiceException{
		try {
			OutpatientRequestionMedicine outpatientRequestionMedicine = outpatientRequestionMedicineDao.findById(reqId).get();
			outpatientRequestionMedicine.setReqStatus("未申领");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("修改申领单状态失败");
		}
	}
	
	/**
	* @Title:updateOPRMstate
	* @Description:修改申领单的状态为已入库
	* @param:@param reqId
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年9月6日 上午10:16:24
	 */
	public void updateOPRMstate(String reqId) throws ServiceException{
		try {
			OutpatientRequestionMedicine outpatientRequestionMedicine = outpatientRequestionMedicineDao.findById(reqId).get();
			outpatientRequestionMedicine.setReqStatus("已入库");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("修改申领单状态失败");
		}
	}
	
	/**
	* @Title:getAlreadyOutStockByDeptId
	* @Description:查找对应部门已出库状态的申领单
	* @param:@param deptId
	* @param:@return
	* @return:List<OutpatientRequestionMedicine>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月5日 下午5:27:27
	 */
	public List<OutpatientRequestionMedicine> getAlreadyOutStockByDeptId(String deptId) {
		return outpatientRequestionMedicineDao.getAlreadyOutStockByDeptId(deptId);
	}
	
	/**
	* @Title:getAlreadyOutStockCount
	* @Description:查找已入库的申领单条数
	* @param:@param deptId
	* @param:@return
	* @return:int
	* @throws
	* @author:crazy_long
	* @Date:2019年9月14日 下午4:12:57
	 */
	public int getHavePutStockCount(String deptId) {
		return outpatientRequestionMedicineDao.getHavePutStockCount(deptId);
	}
	
	/**
	* @Title:getAlreadyOutStockByDeptId
	* @Description:查找对应部门已出库状态的申领单条数
	* @param:@param deptId
	* @param:@return
	* @return:int
	* @throws
	* @author:crazy_long
	* @Date:2019年9月5日 下午5:05:20
	 */
	public int getAlreadyOutStockCount(String deptId) {
		return outpatientRequestionMedicineDao.getAlreadyOutStockByDeptIdCount(deptId);
	}
	
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
