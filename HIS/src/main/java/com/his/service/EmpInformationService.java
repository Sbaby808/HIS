package com.his.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.DeptDAO;
import com.his.dao.IDepartmentDao;
import com.his.dao.IEmpInformationDao;
import com.his.dao.IRoleDao;
import com.his.dao.ITechnicalPostDao;
import com.his.dao.IUserRoleDao;
import com.his.dao.IWaitingRoomDao;
import com.his.pojo.Department;
import com.his.pojo.Dept;
import com.his.pojo.EmpInformation;
import com.his.pojo.Role;
import com.his.pojo.UserRole;
import com.his.pojo.UserRolePK;
import com.his.pojo.WaitingRoom;
import com.his.utils.CreateUUID;
import com.his.utils.MD5Tools;
import com.his.utils.ServiceException;
import java.util.Date;

import com.his.dao.IWktimeEmpDAO;

import com.his.pojo.WktimeEmp;


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
	@Autowired
	private IUserRoleDao userroledao;
	@Autowired
	private IWktimeEmpDAO iWktimeEmpDAO;
	@Autowired
	private DeptDAO deptdao;
	@Autowired
	private IDepartmentDao iDepartmentDao;
	@Autowired
	private IRoleDao iRoleDao;
	@Autowired
	private IUserRoleDao iUserRoleDao;
	
	/**
	* @Title:getDeptByYgxh
	* @Description:根据员工序号获取门诊管理员或急诊管理员的部门信息
	* @param:@param ygxh
	* @param:@return
	* @return:List<Dept>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月5日 下午2:38:40
	 */
	public Dept getDeptByYgxh(String ygxh){
		List<Object[]> list = empInformationDao.getDetpByYgxh(ygxh);
		Dept dept = new Dept();
		//循环判断
		for (Object[] o : list) {
			if("急诊药房管理员".equals(o[2].toString())||"门诊药房管理员".equals(o[2].toString())) {
				System.out.println("-----------------------in to-----------------");
				System.out.println("id:"+o[0].toString());
				System.out.println("name:"+o[1].toString());
				dept.setDeptId(o[0].toString());
				dept.setDeptName(o[1].toString());
			}
		}
		return dept;
	}
	
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
			// 维护职称id
			String tp_id = empInformation.getTechnicalPost().getTpId();
			if (tp_id.equals("")) {
				empInformation.setTechnicalPost(null);
				System.out.println("into add tp_id");
			}
			// 维护waitingRoomId
			String wid = empInformation.getWaitingRoomId();
			if (wid.equals("")) {
				empInformation.setWaitingRoom(null);
				System.out.println("into add wid");
			}
			String roleid=empInformation.getYgPassword();
			System.out.println(roleid);
			// 员工id
			String ygxh=UUID.randomUUID().toString().replace("-", "");
			empInformation.setYgxh(ygxh);
			// 初始化密码为123456
			empInformation.setYgPassword(MD5Tools.KL(MD5Tools.Md5("123456")));
			// 员工序号
			empInformation.setYgGh(CreateUUID.getUUID_16());
			empInformationDao.save(empInformation);
			UserRole userRole=new UserRole();
			userRole.setHireDate(new Date());
			UserRolePK pk=new UserRolePK();
			pk.setRoleId(roleid);
			pk.setYgxh(ygxh);
			userRole.setId(pk);
			iUserRoleDao.save(userRole);
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("添加员工失败");
		}
	}

	/**
	 * @Title:delEmp
	 * @Description:根据员工序号删除没有外键的员工
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
	
	public void edittest() {
		
		WaitingRoom w = waitingroomDao.findById("8b21c198cd1e4996892e5c2f73353007").get();
		System.out.println("--------------------waitingroomID:"+w.getWaitingRoomId());
		System.out.println("--------------------waitingroomNmae:"+w.getWaitingRoomName());
		EmpInformation e = empInformationDao.findById("55e6b68b7d04465e98a3d699acd395fd").get();
		System.out.println("---------------------empname:"+e.getYgName());
		e.setWaitingRoomId(null);
		w.setEmpInformation(e);
		waitingroomDao.save(w);
	
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
			System.err.println(waitingRoomId);
			System.out.println("-----------------------waitingRoomId为"+waitingRoomId);
			if (waitingRoomId.equals("")||waitingRoomId.equals("0")) {
                  empInformation.setWaitingRoomId(null);				
			}
			// 维护职称id
			String tp_id = empInformation.getTechnicalPost().getTpId();
			System.out.println("-----------------------tp_id为"+tp_id);
			if (tp_id.equals("")||tp_id.equals("0")) {
				System.out.println("into TechnicalPost");
				empInformation.setTechnicalPost(null);
			}
			EmpInformation emp=empInformationDao.findById(empInformation.getYgxh()).get();
			empInformation.setYgPassword(emp.getYgPassword());
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
		System.out.println("分页进来没啊");
		List<EmpInformation> list = new ArrayList<EmpInformation>();
		list = empInformationDao.queryByPage(PageRequest.of(curpage - 1, pagesize));
		//把员工角色循环加入到list
		for (EmpInformation empInformation : list) {
			List<UserRole> urlist = this.getUserRoleByYgxh(empInformation.getYgxh());
			empInformation.setUserRoles(urlist);
		}
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
	public Map queryByGH(String yggh) {
		List<EmpInformation> list = empInformationDao.findByygGh(yggh);
		int total = list.size();
		Map map = new HashMap();
		map.put("list",list);
		map.put("total",total);
		return map;
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
	public Map queryEmpByYgname(String ygName){
		 List<EmpInformation> list = empInformationDao.qureyByygName(ygName);
		 int total = list.size();
		 Map map = new HashMap();
		 map.put("list", list);
		 map.put("total", total);
		return map;
	}
	
	/**
	* @Title:getUserRoleByYgxh
	* @Description:根据员工序号查找员工的所有角色
	* @param:@param ygxh
	* @param:@return
	* @return:List<UserRole>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月6日 下午4:53:52
	 */
	public List<UserRole> getUserRoleByYgxh(String ygxh){
		return userroledao.queryAllRoleByYgxh(ygxh);
	}
	
	/**
	* @Title:queryEmpforNameAndXH
	* @Description:获取员工的姓名和员工序号
	* @param:@return
	* @return:List<Object[]>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月6日 上午10:14:59
	 */
	public List<Object[]> queryEmpforNameAndXH(){
		return empInformationDao.queryAllForNameAndXH();
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
	public boolean loginTestEmp(EmpInformation empInformation) throws Exception {
		EmpInformation emp = empInformationDao.getEmpByGh(empInformation.getYgGh()).get(0);
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
	
	/**
	 * 
	* @Title:getDoctorsByWkAndKs
	* @Description:根据科室查询当日排班的医生
	* @param:@param ksId
	* @param:@return
	* @return:List<EmpInformation>
	* @throws
	* @author:Hamster
	* @Date:2019年8月14日 上午9:20:12
	 */
	public List<EmpInformation> getDoctorsByWkAndKs(String ksId){
		return empInformationDao.getDoctorsByWkAndKs(ksId);
	}
	/**
	 * 
	* @Title:getdept获得所有部门对象
	* @Description:TODO
	* @param:@return
	* @return:List<Dept>
	* @throws
	* @author:TRC
	* @Date:2019年9月13日 下午12:00:32
	 */
	public List<Dept> getdept(){
		return (List<Dept>) deptdao.findAll();
	}
	/**
	 * 
	* @Title:getDepartments
	* @Description:TODO获得所有科室
	* @param:@param deptname
	* @param:@return
	* @return:List<Department>
	* @throws
	* @author:TRC
	* @Date:2019年9月13日 下午1:52:44
	 */
	public List<Department> getDepartments(String deptid){
		List<Department> list=iDepartmentDao.getDepartments(deptid);
		return list;
	}
	/**
	 * 
	* @Title:getrole
	* @Description:TODO获得该科室的所有职位
	* @param:@param ksid
	* @param:@return
	* @return:List<Role>
	* @throws
	* @author:TRC
	* @Date:2019年9月13日 下午2:44:35
	 */
	public List<Role> getrole(String ksid){
		List<Role> list=iRoleDao.getrole(ksid);
		return list;
	}
	/**
	 * 
	* @Title:getWaitingRooms
	* @Description:TODO根据科室id获得
	* @param:@param ksid
	* @param:@return
	* @return:List<WaitingRoom>
	* @throws
	* @author:TRC
	* @Date:2019年9月15日 上午12:14:02
	 */
	public List<WaitingRoom> getWaitingRooms(String ksid){
		List<WaitingRoom> list=waitingroomDao.getRooms(ksid);
		return list;
	}
	/**
	 * 
	* @Title:gaipassword
	* @Description:TODO修改密码
	* @param:@param ygxh
	* @param:@param old
	* @param:@param xin
	* @param:@return
	* @return:String
	* @throws
	* @author:TRC
	* @Date:2019年9月15日 上午2:46:17
	 */
	public String gaipassword(String ygxh,String old,String xin) {
		EmpInformation emp=empInformationDao.findById(ygxh).get();
		if(MD5Tools.check(old, emp.getYgPassword())) {
			emp.setYgPassword(MD5Tools.KL(MD5Tools.Md5(xin)));
			empInformationDao.save(emp);
			return "修改密码成功！";
		}
		else {
			return "修改密码失败，请输入正确的密码！";
		}
	}
	
	/**
	* @Title:checkLoginGrant
	* @Description:检查员工是否有登录权限
	* @param:@param ygxh
	* @param:@return
	* @return:boolean
	* @throws
	* @author:Sbaby
	* @Date:2019年9月13日 下午12:41:26
	 */
	public boolean checkLoginGrant(String ygGh) throws Exception {
		EmpInformation empInformation  =empInformationDao.getEmpByGh(ygGh).get(0);
		int result = userroledao.checkLoginGrant(empInformation.getYgxh());
		return result > 0 ? true : false;
	}

}
