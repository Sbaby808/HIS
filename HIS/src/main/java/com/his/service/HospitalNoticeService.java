package com.his.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IDepartmentDao;
import com.his.dao.IHosInNoticeDao;
import com.his.pojo.Department;
import com.his.pojo.HospitalNotice;

/**  
* @ClassName: HospitalNoticeService  
* @Description: 入院通知单Service
* @author Sbaby
* @date 2019年8月23日  上午11:10:30
*    
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class HospitalNoticeService {

	@Autowired
	private IHosInNoticeDao hosInNoticeDao;
	@Autowired
	private IDepartmentDao departmentDao;
	
	/**
	* @Title:getAllHosDepartment
	* @Description:查询住院部门的所有科室
	* @param:@return
	* @return:List<Department>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月23日 上午11:12:21
	 */
	public List<Department> getAllHosDepartment() {
		return departmentDao.getKSByOut("inpatient");
	}
	
	/**
	* @Title:addHospitalNotice
	* @Description:添加入院通知
	* @param:@param notice
	* @param:@return
	* @return:HospitalNotice
	* @throws
	* @author:Sbaby
	* @Date:2019年8月23日 上午11:45:04
	 */
	public HospitalNotice addHospitalNotice(HospitalNotice notice) {
		if(notice.getRytzId() == null) {
			notice.setRytzId(UUID.randomUUID().toString().replaceAll("-", ""));
		}
		hosInNoticeDao.save(notice);
		return notice;
	}
	
	/**
	* @Title:getHosNoticeBySolveId
	* @Description:根据医嘱查询入院通知单
	* @param:@param solveId
	* @param:@return
	* @return:HospitalNotice
	* @throws
	* @author:Sbaby
	* @Date:2019年8月23日 下午2:02:10
	 */
	public HospitalNotice getHosNoticeBySolveId(String solveId) {
		return hosInNoticeDao.getHosNoticeBySolveId(solveId);
	}
	
	/**
	* @Title:delHospitalNoticeById
	* @Description:根据入院通知单号删除
	* @param:@param hosId
	* @return:void
	* @throws
	* @author:Sbaby
	* @Date:2019年8月23日 下午2:26:03
	 */
	public void delHospitalNoticeById(String hosId) {
		hosInNoticeDao.deleteById(hosId);
	}
	
}
