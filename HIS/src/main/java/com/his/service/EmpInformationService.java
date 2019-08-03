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
import com.his.dao.ITechnicalPostDao;
import com.his.dao.IWaitingRoomDao;
import com.his.pojo.EmpInformation;
import com.his.pojo.TechnicalPost;
import com.his.pojo.WaitingRoom;
import com.his.utils.CreateUUID;
import com.his.utils.MD5Tools;
import com.his.utils.ServiceException;

/**
 * @ClassName: EmpInformationService
 * @Description: 员工信息service
 * @author crazy_long
 * @date 2019年7月30日 下午12:13:13
 * 
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class EmpInformationService {

	@Autowired
	private IEmpInformationDao empInformationDao;
	@Autowired
	private ITechnicalPostDao technicalpostDao;
	@Autowired
	private IWaitingRoomDao waitingroomDao;
	
	/**
	 * @Title:addEmpAllInformation
	 * @Description:添加员工的所有信息
	 * @param:@param empInformation
	 * @return:void
	 * @throws @author:crazy_long
	 * @Date:2019年8月2日 下午10:46:00
	 */
	public void addEmpAllInformation(EmpInformation empInformation) throws ServiceException {
		try {
			// 维护职位id
			String tp_id = empInformation.getTechnicalPost().getTpId();
			if (tp_id.equals("")) {
				TechnicalPost tp = technicalpostDao.findById(tp_id).get();
				empInformation.setTechnicalPost(tp);
			}
			// 维护waitingRoomId
			String wid = empInformation.getWaitingRoomId();
			if (wid.equals("")) {
				WaitingRoom wr = waitingroomDao.findById(wid).get();
				empInformation.setWaitingRoom(wr);
			}
			// 员工id
			empInformation.setYgxh(UUID.randomUUID().toString().replace("-", ""));
			// 初始化密码为111111
			empInformation.setYgPassword("111111");
			// 员工序号
			empInformation.setYgGh(CreateUUID.getUUID_16());
			empInformationDao.save(empInformation);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("添加员工失败");
		}
	}

	/**
	 * @Title:delEmp
	 * @Description:根据员工序号删除员工
	 * @param:@param ygxh
	 * @return:void
	 * @throws @author:crazy_long
	 * @Date:2019年8月3日 上午11:08:40
	 */
	public void delEmp(String ygxh) throws ServiceException {
		try {
			empInformationDao.deleteById(ygxh);
		} catch (Exception e) {
			e.printStackTrace();
			new ServiceException("删除员工失败");
		}
	}

	/**
	 * @Title:updateEmpInformation
	 * @Description:更改员工信息
	 * @param:@param empInformation
	 * @param:@throws ServiceException
	 * @return:void
	 * @throws @author:crazy_long
	 * @Date:2019年8月3日 上午11:48:56
	 */
	public void updateEmpInformation(EmpInformation empInformation) throws ServiceException {
		try {
			// 维护waitingRoomId
			String waitingRoomId = empInformation.getWaitingRoomId();
			if (waitingRoomId.equals("")) {
				empInformation.setWaitingRoom(waitingroomDao.findById(waitingRoomId).get());
			}
			// 维护职位id
			String tp_id = empInformation.getTechnicalPost().getTpId();
			if (tp_id.equals("")) {
				TechnicalPost tp = technicalpostDao.findById(tp_id).get();
				empInformation.setTechnicalPost(tp);
			}
			empInformationDao.save(empInformation);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("更改员工失败");
		}
	}

	/**
	 * @Title:queryEmpByPage
	 * @Description:分页查询
	 * @param:@param curpage
	 * @param:@param pagesize
	 * @param:@return
	 * @return:Map
	 * @throws @author:crazy_long
	 * @Date:2019年8月3日 上午11:55:55
	 */
	public Map queryEmpByPage(int curpage, int pagesize) {
		List<Object[]> list = empInformationDao.queryByPage(PageRequest.of(curpage - 1, pagesize));
		long total = empInformationDao.count();
		Map map = new HashMap();
		map.put("total", total);
		map.put("list", list);

		return map;
	}
	
	/**
	* @Title:queryByGH
	* @Description:根据工号查询员工
	* @param:@return
	* @return:EmpInformation
	* @throws
	* @author:crazy_long
	* @Date:2019年8月3日 下午2:19:10
	 */
	public List<EmpInformation> queryByGH(String yggh) {
		return empInformationDao.findByygGh(yggh);
	}
	
	/**
	* @Title:queryEmpByYgname
	* @Description:根据名字查询员工
	* @param:@param ygName
	* @param:@return
	* @return:List<EmpInformation>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月3日 下午2:40:34
	 */
	public List<EmpInformation> queryEmpByYgname(String ygName){
		return empInformationDao.qureyByygName(ygName);
	}

	/**
	 * 
	 * @Title:addTestEmp
	 * @Description:添加测试员工信息
	 * @param:@param empInformation
	 * @return:void
	 * @throws @author:Sbaby
	 * @Date:2019年7月31日 下午2:02:29
	 */
	public void addTestEmp(EmpInformation empInformation){
		empInformationDao.save(empInformation);
	}

	/**
	 * 
	 * @Title:loginTestEmp
	 * @Description:员工登录验证
	 * @param:@param empInformation
	 * @return:void
	 * @throws @author:Sbaby
	 * @Date:2019年7月31日 下午2:37:29
	 */
	public boolean loginTestEmp(EmpInformation empInformation) {
		EmpInformation emp = empInformationDao.findById(empInformation.getYgxh()).get();
		if (MD5Tools.check(empInformation.getYgPassword(), emp.getYgPassword())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获得员工信息
	 * 
	 * @Title:getEmpInfoById
	 * @Description:TODO
	 * @param:@param empId
	 * @param:@return
	 * @return:EmpInformation
	 * @throws @author:Sbaby
	 * @Date:2019年7月31日 下午3:15:42
	 */
	public EmpInformation getEmpInfoById(String empId) {
		return empInformationDao.findById(empId).get();
	}

}
